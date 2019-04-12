package com.android.compraencasa.model;

public class Galeria {
    private String name;
    private int imageSource;

    public Galeria (int imageSource, String name) {
        this.name = name;
        this.imageSource = imageSource;
    }

    public String getName() {
        return name;
    }

    public int getImageSource() {
        return imageSource;
    }
}
