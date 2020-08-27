package com.pavlov.model;

public class Result {

    private String cName;
    private String cAdd;

    public Result(String cName, String cAdd) {
        this.cName = cName;
        this.cAdd = cAdd;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAdd() {
        return cAdd;
    }

    public void setcAdd(String cAdd) {
        this.cAdd = cAdd;
    }
}
