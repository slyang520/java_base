package com.slyang.test.test;

public class TestClass {

    private String fiName;
    private String secName;

    public TestClass(String fiName, String secName) {
        this.fiName = fiName;
        this.secName = secName;
    }

    public String getFiName() {
        return fiName;
    }

    public void setFiName(String fiName) {
        this.fiName = fiName;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "fiName='" + fiName + '\'' +
                ", secName='" + secName + '\'' +
                '}';
    }
}
