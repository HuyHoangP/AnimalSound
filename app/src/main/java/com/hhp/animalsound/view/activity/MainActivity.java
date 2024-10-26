package com.hhp.animalsound.view.activity;

import android.content.DialogInterface;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;

import com.hhp.animalsound.App;
import com.hhp.animalsound.databinding.ActivityMainBinding;
import com.hhp.animalsound.model.Animal;
import com.hhp.animalsound.view.base.BaseActivity;
import com.hhp.animalsound.view.fragment.SplashFragment;
import com.hhp.animalsound.viewmodel.MainActivityVM;

import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityVM> {


    public static final String TYPE_ALL = "ALL ANIMAL";
    public static final String TYPE_BIRD = "bird";
    public static final String TYPE_FISH = "fish";
    public static final String TYPE_MAMMAL = "mammal";


    @Override
    public void setCurrentList(String currentList) {
        if (currentList.contains(TYPE_ALL)) {
            viewModel.setCurrentList(App.getInstance().getStorage().listAnimal);
        } else if (currentList.contains(TYPE_BIRD)) {
            viewModel.setCurrentList(App.getInstance().getStorage().listBird);
        } else if (currentList.contains(TYPE_FISH)) {
            viewModel.setCurrentList(App.getInstance().getStorage().listFish);
        } else if (currentList.contains(TYPE_MAMMAL)) {
            viewModel.setCurrentList(App.getInstance().getStorage().listMammal);
        }
    }

    @Override
    public List<Animal> currentList() {
        return viewModel.getCurrentList();
    }

    @Override
    public void initView() {
        handleBackPress();
        showFragment(SplashFragment.TAG, null, false);
    }

    private void handleBackPress() {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (isEnabled() && getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    alertCloseDialog();
                    return;
                }
                setEnabled(false);
                getOnBackPressedDispatcher().onBackPressed();
                setEnabled(true);
            }
        });
    }

    private void alertCloseDialog() {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("Alert");
        alert.setMessage("Close app?");
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "Close", (dialogInterface, i) -> finish());
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "Don't", (dialogInterface, i) -> {
        });
        alert.show();
    }

    @Override
    public Class<MainActivityVM> initViewModel() {
        return MainActivityVM.class;
    }

    @Override
    public ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

}