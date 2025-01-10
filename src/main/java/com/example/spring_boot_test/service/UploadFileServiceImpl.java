package com.example.spring_boot_test.service;

import com.example.spring_boot_test.common.UploadFileConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class UploadFileServiceImpl implements UploadFileService {

    public void directoryCreateInHardDrive() {
        File dir = new File(UploadFileConstant.SRC_IMAGE_HARD_DRIVE);
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Directory already exists");
                System.out.println("Failed to create directory!");
            }
        }
    }

    @Override
    public void directoryCreateInProject() {
        File dir = new File("src/main/resources/static/images/");
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Directory already exists");
                System.out.println("Failed to create directory!");
            }
        }
    }

    public String convertFileName(String fileName) {
        if (null == fileName) return "";
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
        return sdf.format(new Date()) + fileExtension;
    }
}
