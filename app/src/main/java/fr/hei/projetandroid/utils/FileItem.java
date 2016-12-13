package fr.hei.projetandroid.utils;

import java.io.File;

/**
 * Created by julien on 09/12/16.
 */
public class FileItem {

    String fileName;
    Long taille;
    boolean isDir;

    public FileItem(String fileNameExt, Long taille, boolean isDir){
        this.fileName = fileNameExt;
        this.taille = taille;
        this.isDir = isDir;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getTaille() {
        return taille;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean dir) {
        isDir = dir;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setTaille(Long taille) {
        this.taille = taille;
    }
}
