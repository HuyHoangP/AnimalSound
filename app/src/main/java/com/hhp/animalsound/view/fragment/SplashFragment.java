package com.hhp.animalsound.view.fragment;

import android.os.Handler;
import android.view.animation.AnimationUtils;

import com.hhp.animalsound.R;
import com.hhp.animalsound.databinding.FragmentSplashBinding;
import com.hhp.animalsound.view.base.BaseFragment;
import com.hhp.animalsound.viewmodel.SplashFragmentVM;

public class SplashFragment extends BaseFragment<FragmentSplashBinding, SplashFragmentVM> {
    public static final String TAG = SplashFragment.class.getName();

    @Override
    public void initView() {
        binding.ivSquare.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate_square));
        viewModel.initData();
        new Handler().postDelayed(() -> callback.showFragment(MainFragment.TAG, null, false), 4000);
    }

    @Override
    public Class<SplashFragmentVM> initViewModel() {
        return SplashFragmentVM.class;
    }

    @Override
    public FragmentSplashBinding initViewBinding() {
        return FragmentSplashBinding.inflate(getLayoutInflater());
    }
}
