package com.hhp.animalsound.view.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.hhp.animalsound.R;
import com.hhp.animalsound.view.OnMainCallback;

import java.lang.reflect.Constructor;

public abstract class BaseActivity<T extends ViewBinding, V extends ViewModel> extends FragmentActivity implements IView<T, V>, OnMainCallback {
    protected T binding;
    protected V viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        viewModel = new ViewModelProvider(this).get(initViewModel());
        setContentView(binding.getRoot());
        initView();
    }

    @Override
    public void showFragment(String tag, Object data, Boolean isBack) {
        try {
            Class<?> clazz = Class.forName(tag);
            Constructor<?> constructor = clazz.getConstructor();
            BaseFragment<?, ?> fragment = (BaseFragment<?, ?>) constructor.newInstance();

            fragment.setCallback(this);
            fragment.setData(data);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fr_main, fragment, tag);
            if (isBack) fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
