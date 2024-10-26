package com.hhp.animalsound.viewmodel;

import androidx.lifecycle.ViewModel;

import com.hhp.animalsound.model.Animal;

public class DetailInfoDialogVM extends ViewModel {
    private Animal animal;

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
