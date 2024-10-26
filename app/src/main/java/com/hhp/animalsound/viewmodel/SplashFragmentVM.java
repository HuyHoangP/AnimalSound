package com.hhp.animalsound.viewmodel;

import androidx.lifecycle.ViewModel;

import com.hhp.animalsound.App;
import com.hhp.animalsound.model.Animal;
import com.hhp.animalsound.view.activity.MainActivity;

import java.io.IOException;
import java.util.ArrayList;

public class SplashFragmentVM extends ViewModel {
    public void initData(){
        App.getInstance().getStorage().listAnimal = new ArrayList<>();
        App.getInstance().getStorage().listMammal = new ArrayList<>();
        App.getInstance().getStorage().listBird = new ArrayList<>();
        App.getInstance().getStorage().listFish = new ArrayList<>();
        try {
            String[] listCategory = App.getInstance().getAssets().list("photos/");
            for(String category: listCategory){
                String[] listPhotoPath = App.getInstance().getAssets().list("photos/" + category + "/" );
                for(String photoPath: listPhotoPath){
                    String name = photoPath.substring(3).replace(".png", "");
                    Animal animal = new Animal(name, category, photoPath,name +".mp3");
                    App.getInstance().getStorage().listAnimal.add(animal);
                    addToListCategory(category, animal);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addToListCategory(String category, Animal animal) {
        if(category.contains(MainActivity.TYPE_BIRD)){
            App.getInstance().getStorage().listBird.add(animal);
        } else if(category.contains(MainActivity.TYPE_FISH)){
            App.getInstance().getStorage().listFish.add(animal);
        } else if(category.contains(MainActivity.TYPE_MAMMAL)){
            App.getInstance().getStorage().listMammal.add(animal);
        }
    }
}
