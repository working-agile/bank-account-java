package com.workingagile.acsd.addition;

public class Calculator {

    private int currentValue;

    public void add(Integer addend1, Integer addend2) {
        this.currentValue = addend1 + addend2;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }
}
