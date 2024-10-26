package com.hhp.animalsound.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.hhp.animalsound.R;
import com.hhp.animalsound.databinding.DialogDetailInfoBinding;
import com.hhp.animalsound.view.OnMainCallback;
import com.hhp.animalsound.viewmodel.DetailFragmentVM;

public abstract class BaseDialog<T extends ViewBinding,V extends ViewModel> extends DialogFragment implements IView<T,V>{
    protected T binding;
    protected V viewModel;
    protected OnMainCallback callBack;

    public BaseDialog(V viewModel) {
        this(null, viewModel);
    }

    public BaseDialog(OnMainCallback callBack, V viewModel) {
        this.callBack = callBack;
        this.viewModel = viewModel;
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Dialog_FullScreen);
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = initViewBinding();
        if(viewModel == null){
            this.viewModel = new ViewModelProvider(this).get(initViewModel());
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
}
