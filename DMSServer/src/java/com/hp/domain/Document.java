package com.hp.domain;

import java.io.File;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;
import org.hibernate.validator.Valid;

public class Document {

    private File file;
    private String fileFileName;
    private String fileContentType;
    private String fileSize;
    private Float number;

    public Float getNumber() {
        return number;
    }

    public void setNumber(Float number) {
        this.number = number;
    }
    

    @NotNull(message="Không được để trống!")
    @Valid
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }
    
    @NotNull(message="Không được để trống!")
        @Pattern(regex="[A-Za-z0-9\\.]+",message="Tên tài khoản chỉ chứa a-z, A-Z, 0-9, .")

    @Valid
    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
}
