package com.example.FileLoader.service;

import com.example.FileLoader.dto.FileDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    private final List<String> ports = List.of("8080", "8081", "8082", "8083", "8084");
    private final List<String> ips = new ArrayList<>(List.of("one", "two", "three", "four", "five"));
    private final Path FOLDER_PATH = Path.of("files");

    @PostConstruct
    public void init() throws IOException {
        Files.createDirectories(FOLDER_PATH);
    }

    public void saveFile(MultipartFile file) throws IOException {
        FileDto dto = new FileDto(file.getOriginalFilename(), file.getBytes()); //Формируем ДТО с данными файла, что бы раскидать его по другим серверам
        RestTemplate template = new RestTemplate();
        for (String port : ports) {
            try {
                template.postForObject("http://" + ips.remove(0) + ":" + port + "/file", dto, Void.class); //Раскидываем ДТО по серверам. IP - имя контейнера
            } catch (Exception e) {}
        }
    }

    public void saveFile(FileDto dto) throws IOException {
        Files.write(FOLDER_PATH.resolve(dto.getName()), dto.getData());
    }

    public List<String> getFileList() throws IOException {
        return Files.list(FOLDER_PATH) //Берём все файлы из папки, формируем из них список с их названиями
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());
    }

    public File getFileToDownload(String name) {
        File file = FOLDER_PATH.resolve(name).toFile();
        if (!file.exists())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File not found!");
        return file;
    }

}
