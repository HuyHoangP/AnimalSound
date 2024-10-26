package com.hhp.animalsound.viewmodel;

import androidx.lifecycle.ViewModel;

import com.hhp.animalsound.model.Animal;

import java.util.List;

public class MainActivityVM extends ViewModel {
    private List<Animal> currentList;

    public List<Animal> getCurrentList() {
        return currentList;
    }

    public void setCurrentList(List<Animal> currentList) {
        this.currentList = currentList;
    }
}
