package com.hhp.animalsound.viewmodel;

import android.speech.tts.TextToSpeech;

import androidx.lifecycle.ViewModel;

import com.hhp.animalsound.App;
import com.hhp.animalsound.model.Animal;
import com.hhp.animalsound.view.activity.MainActivity;

import java.io.IOException;
import java.util.ArrayList;

public class MainFragmentVM extends ViewModel {
    private TextToSpeech tts;
    private String currentType = MainActivity.TYPE_ALL;
    public TextToSpeech getTts() {
        return tts;
    }
    public void setTts(TextToSpeech tts) {
        this.tts = tts;
    }

    public String getCurrentType() {
        return currentType;
    }

    public void setCurrentType(String currentType) {
        this.currentType = currentType;
    }
}
