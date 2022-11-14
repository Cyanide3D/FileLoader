package com.example.FileLoader.controller;

import com.example.FileLoader.dto.FileDto;
import com.example.FileLoader.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService service;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void accept(@RequestBody FileDto dto) throws IOException { //Эндпоинт для сохранения файла
        service.saveFile(dto);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Resource> download(@PathVariable String name) throws IOException { //Эндпоинт для скачивания файла
        File file = service.getFileToDownload(name);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));  //Получаем поток из файла

        HttpHeaders header = new HttpHeaders(); //Формируем необходимые хэдэры
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        return ResponseEntity.ok() //Формируем ответ для пользователя
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
