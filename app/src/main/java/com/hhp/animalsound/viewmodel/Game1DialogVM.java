package com.hhp.animalsound.viewmodel;

import android.graphics.Paint;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.hhp.animalsound.model.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game1DialogVM extends ViewModel {
    private String right , wrong;
    private int score, index;
    private Animal animal;
    private List<Animal> listAnimal;

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public List<Animal> getListAnimal() {
        return listAnimal;
    }

    public void setListAnimal(List<Animal> listAnimal) {
        this.listAnimal = listAnimal;
    }

    public int getIndex() {
        return index;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getRight() {
        return right;
    }

    public String getWrong() {
        return wrong;
    }

    public void initQuestion() {
        Collections.shuffle(listAnimal);
        List<Animal> tmpListAnimal = new ArrayList<>(listAnimal);
        setAnimal(tmpListAnimal.get(index));
        tmpListAnimal.remove(animal);
        Collections.shuffle(tmpListAnimal);
        right = animal.getName();
        wrong = tmpListAnimal.get(0).getName();
    }

    public boolean checkAnswer(String answer) {
        if(answer.substring(3).equals(animal.getName())){
           setScore(score + 1);
           setIndex(index >= listAnimal.size() - 1 ? 0: index + 1);
           return true;
        }
        return false;
    }
}
