package com.metaxiii.fr.lib;

public enum ConstantRequest {
    SHOWDATABASES("SHOW DATABASES;"),
    SELECTALL("SELECT * FROM"),
    DESCRIBE("DESCRIBE");

    private String request;

    ConstantRequest(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }
}
