package com.simactivation.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public class StorageService {

    void init(){};

    public void store(MultipartFile file){

    };

    public Stream<Path> loadAll(){return null;};

    Path load(String filename){return null;};

    public Resource loadAsResource(String filename){return null;};

    void deleteAll(){};

}