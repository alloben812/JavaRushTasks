package com.javarush.task.task31.task3110;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by NazarenkoDS on 16.05.2017.
 */
public class FileManager {
    public FileManager(Path rootPath) throws IOException {
        this.rootPath = rootPath;
    }
    private Path rootPath;
    private List<Path> fileList = new ArrayList<>();

    public List<Path> getFileList() {
        return fileList;
    }

    public void collectFileList(Path path) throws IOException{

    }
}
