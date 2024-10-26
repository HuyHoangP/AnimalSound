package com.hhp.animalsound.view.dialog;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.hhp.animalsound.R;
import com.hhp.animalsound.databinding.DialogGame1Binding;
import com.hhp.animalsound.model.Animal;
import com.hhp.animalsound.view.OnMainCallback;
import com.hhp.animalsound.view.base.BaseDialog;
import com.hhp.animalsound.viewmodel.Game1DialogVM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game1Dialog extends BaseDialog<DialogGame1Binding, Game1DialogVM> implements View.OnClickListener {

    public static final String TAG = Game1Dialog.class.getName();

    public Game1Dialog(OnMainCallback callback) {
        super(callback, null);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    public void initView() {
        setCancelable(false);
        initListAnimal();
        initQuestion();
        binding.trBack.setOnClickListener(this);
        binding.frCard.setOnClickListener(this);
        binding.tvKeyA.setOnClickListener(this);
        binding.tvKeyB.setOnClickListener(this);
    }

    @Override
    public Class<Game1DialogVM> initViewModel() {
        return Game1DialogVM.class;
    }

    @Override
    public DialogGame1Binding initViewBinding() {
        return DialogGame1Binding.inflate(getLayoutInflater());
    }

    private void initListAnimal() {
        viewModel.setListAnimal(callBack.currentList());
    }


    private void initQuestion() {
        viewModel.initQuestion();
        boolean randomBool = new Random().nextBoolean();
        binding.tvKeyA.setText(randomBool?"A. " + viewModel.getRight(): "A. " + viewModel.getWrong());
        binding.tvKeyB.setText(!randomBool?"B. " +  viewModel.getRight():"B. " +  viewModel.getWrong());

        Paint paint = new Paint();
        paint.setTextSize(20);
        float keyAWidth = paint.measureText(binding.tvKeyA.getText().toString());
        float keyBWidth = paint.measureText(binding.tvKeyB.getText().toString());

        binding.tvKeyA.setWidth((int) (keyAWidth + 250));
        binding.tvKeyB.setWidth((int) (keyBWidth + 250));
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        binding.tvKeyA.setMaxWidth(screenWidth);
        binding.tvKeyB.setMaxWidth(screenWidth);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.trBack) dismiss();
        if(view.getId() == R.id.frCard) showCardAnimal();
        if(view.getId() == R.id.tvKeyA || view.getId() == R.id.tvKeyB) checkAnswer(((TextView)view).getText().toString());
    }

    private void checkAnswer(String answer) {
        if(viewModel.checkAnswer(answer)){
            binding.tvScore.setText("Score: " + viewModel.getScore());
            initQuestion();
        } else {
            Toast.makeText(requireContext(), "WRONG", Toast.LENGTH_SHORT).show();
        }
    }


    private void showCardAnimal() {
        Toast toast = new Toast(requireContext());
        ImageView ivAnimal = new ImageView(requireContext());
        ivAnimal.setImageBitmap(viewModel.getAnimal().getImage());
//        ivAnimal.setScaleX(0.5F);
//        ivAnimal.setScaleY(0.5F);
        toast.setView(ivAnimal);

        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, -600);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }


}
