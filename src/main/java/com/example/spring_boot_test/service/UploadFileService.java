package com.example.spring_boot_test.service;

public interface UploadFileService {

    void directoryCreateInHardDrive();

    void directoryCreateInProject();

    String convertFileName(String fileName);
}
