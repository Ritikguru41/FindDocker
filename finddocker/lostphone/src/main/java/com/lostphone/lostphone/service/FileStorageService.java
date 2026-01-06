package com.lostphone.lostphone.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

      private final String UPLOAD_DIR = "C:/uploads/lostphone/";

    public String saveFile(MultipartFile file) throws IOException {

        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File saveFile = new File(UPLOAD_DIR + fileName);

        file.transferTo(saveFile);

        // VERY IMPORTANT
        return "/uploads/" + fileName;
    }
}
