package de.latlon.xplanbox.api.validator.v1.model;

public class UploadReport {

    private String id;

    private String fileName;

    public UploadReport( String id, String fileName ) {
        this.id = id;
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
