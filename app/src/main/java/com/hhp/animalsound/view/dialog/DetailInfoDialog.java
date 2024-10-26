package com.hhp.animalsound.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.hhp.animalsound.R;
import com.hhp.animalsound.databinding.DialogDetailInfoBinding;
import com.hhp.animalsound.model.Animal;
import com.hhp.animalsound.view.base.BaseDialog;
import com.hhp.animalsound.viewmodel.DetailFragmentVM;
import com.hhp.animalsound.viewmodel.DetailInfoDialogVM;
import com.hhp.animalsound.viewmodel.Game1DialogVM;

public class DetailInfoDialog extends BaseDialog<DialogDetailInfoBinding, DetailInfoDialogVM> implements View.OnClickListener {
    private final Animal animal;
    public static final String TAG = DetailInfoDialog.class.getName();

    public DetailInfoDialog(Animal animal) {
        super(null, null);
        this.animal = animal;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    public void initView() {
        viewModel.setAnimal(animal);
        setCancelable(false);
        binding.ivBack.setOnClickListener(view -> dismiss());
        binding.tvTitle.setText(viewModel.getAnimal().getName());
        binding.wvInfo.getSettings().setJavaScriptEnabled(true);
        binding.wvInfo.getSettings().setAllowContentAccess(true);
        binding.wvInfo.getSettings().setBuiltInZoomControls(true);
        binding.wvInfo.getSettings().setAllowFileAccess(true);
        binding.wvInfo.getSettings().setDomStorageEnabled(true);
        binding.wvInfo.getSettings().setSupportZoom(true);
        binding.wvInfo.setWebChromeClient(new WebChromeClient());
        binding.wvInfo.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        binding.wvInfo.loadUrl("https://www.google.com/search?hl=en&q=" + viewModel.getAnimal().getName());
    }

    @Override
    public Class<DetailInfoDialogVM> initViewModel() {
        return DetailInfoDialogVM.class;
    }

    @Override
    public DialogDetailInfoBinding initViewBinding() {
        return DialogDetailInfoBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void dismiss() {
        if(!binding.wvInfo.canGoBack()){
            super.dismiss();
            return;
        }
        binding.wvInfo.goBack();
    }


}
