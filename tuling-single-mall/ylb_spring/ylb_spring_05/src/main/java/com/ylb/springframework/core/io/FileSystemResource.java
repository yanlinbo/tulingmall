package com.ylb.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemResource implements Resource {

    private final File file;

    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();  //todo
    }



    public FileSystemResource(String path) {
        this.file = new File(path);  //todo
        this.path = path;
    }

    public String getPath() {
        return path;
    }


    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }
}
