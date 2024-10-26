package com.hhp.animalsound.view.fragment;

import android.view.View;

import com.hhp.animalsound.databinding.FragmentGamesBinding;
import com.hhp.animalsound.view.base.BaseFragment;
import com.hhp.animalsound.view.dialog.Game1Dialog;
import com.hhp.animalsound.viewmodel.GamesFragmentVM;

public class GamesFragment extends BaseFragment<FragmentGamesBinding, GamesFragmentVM> {
    public static final String TAG = GamesFragment.class.getName();
    @Override
    public void initView() {
        binding.game.trGame.setOnClickListener(view -> {
            Game1Dialog game1Dialog = new Game1Dialog(callback);
            game1Dialog.show(getChildFragmentManager(), Game1Dialog.TAG);
        });
    }

    @Override
    public Class<GamesFragmentVM> initViewModel() {
        return GamesFragmentVM.class;
    }

    @Override
    public FragmentGamesBinding initViewBinding() {
        return FragmentGamesBinding.inflate(getLayoutInflater());
    }
}
