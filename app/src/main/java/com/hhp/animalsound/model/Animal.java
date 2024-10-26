package com.hhp.animalsound.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.hhp.animalsound.App;

import java.io.IOException;
import java.util.Objects;

public class Animal {
    private final String name;
    private final String type;
    private final String imagePath;
    private final String soundPath;

    private Bitmap image;

    public Animal(String name,String type, String imagePath, String soundPath) {

        this.name = name.substring(0,1).toUpperCase() + name.substring(1);
        this.type = type;
        this.imagePath = "photos/" + type + "/"  + imagePath;
        this.soundPath = "sounds/" + type + "/" + soundPath;
        try {
            image = BitmapFactory.decodeStream(App.getInstance().getAssets().open(this.imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getImage() {
        return image;
    }

    public String getSoundPath() {
        return soundPath;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return name.equals(animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
