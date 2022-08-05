package com.cns.blogger.blog.services.impl;

import com.cns.blogger.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        name = UUID.randomUUID().toString().concat(name.substring(name.lastIndexOf(".")));
        path = path+ File.separator+name;

        Files.copy(file.getInputStream(), Paths.get(path));
        return name;
    }

    @Override
    public InputStream getResource(String path, String filename) throws FileNotFoundException {
        return new FileInputStream(path+File.separator+filename);
    }
}
