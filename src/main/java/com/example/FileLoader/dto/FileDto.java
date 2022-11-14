package com.example.FileLoader.dto;

public class FileDto {

    private String name;
    private byte[] data;

    public FileDto(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    public FileDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
