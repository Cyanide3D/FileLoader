package com.example.FileLoader.controller;

import com.example.FileLoader.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class IndexController {

    @Autowired
    private FileService service;

    @GetMapping
    public String index(Model model) throws IOException { //Эндпоинт для отображения главной страницы
        model.addAttribute("files", service.getFileList());
        return "index";
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file) throws IOException { //Эндпоинт, который срабатывает, через форму кто то отправляет файл
        service.saveFile(file);
        return "redirect:/";
    }

}
