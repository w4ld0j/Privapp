package com.example.privapp;

import android.graphics.drawable.Drawable;

public class Modelo {
    private String name;
    private Drawable image;

    public Modelo(String name, Drawable image) {
        this.name = name;
        this.image = image;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
