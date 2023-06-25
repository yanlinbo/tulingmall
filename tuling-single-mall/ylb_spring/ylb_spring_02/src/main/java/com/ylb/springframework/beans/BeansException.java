package com.ylb.springframework.beans;

public class BeansException extends Exception {
    public BeansException(String des, IllegalAccessException e) {
    }

    public BeansException(String des) {
    }
}
