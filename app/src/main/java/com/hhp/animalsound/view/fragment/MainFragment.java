package com.hhp.animalsound.view.fragment;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhp.animalsound.R;
import com.hhp.animalsound.databinding.FragmentMainBinding;
import com.hhp.animalsound.view.activity.MainActivity;
import com.hhp.animalsound.view.adapter.CategoryAdapter;
import com.hhp.animalsound.view.base.BaseFragment;
import com.hhp.animalsound.view.transformer.DepthPageTransformer;
import com.hhp.animalsound.viewmodel.MainFragmentVM;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainFragment extends BaseFragment<FragmentMainBinding, MainFragmentVM> {
    public static final String TAG = MainFragment.class.getName();

    @Override
    public void initView() {
        initCategory();
        callback.setCurrentList(viewModel.getCurrentType());
        initAnimalView();
//        initTextToSpeech();

        binding.menu.trGames.setOnClickListener(view -> callback.showFragment(GamesFragment.TAG, null, true));
        binding.ivDragHere.startAnimation(AnimationUtils.loadAnimation(context, R.anim.scale_in_out));
    }

    private void initCategory() {
        binding.menu.allAnimal.trSpecies.setOnClickListener(view -> {
            binding.drawer.closeDrawers();
            viewModel.setCurrentType(MainActivity.TYPE_ALL);
            callback.setCurrentList(MainActivity.TYPE_ALL);
            initAnimalView();
        });
        try{
            List<Integer> listBgPhoto = new ArrayList<>();
            listBgPhoto.add(R.mipmap.ic_owl);
            listBgPhoto.add(R.mipmap.ic_crocodile);
            listBgPhoto.add(R.mipmap.ic_bear);
            String[] listPhotoPath = context.getAssets().list("photos/");
            for(int i = 0; i < listPhotoPath.length; i++){
                View itemView = LayoutInflater.from(context).inflate(R.layout.item_species, null);
                TextView tvName = itemView.findViewById(R.id.tvName);
                tvName.setText(listPhotoPath[i].toUpperCase());
                ImageView ivName = itemView.findViewById(R.id.ivName);
                ivName.setImageResource(listBgPhoto.get(i));
                int finalI = i;
                itemView.setOnClickListener(view -> {
                    binding.drawer.closeDrawers();
                    viewModel.setCurrentType(listPhotoPath[finalI]);
                    callback.setCurrentList(listPhotoPath[finalI]);
                    initAnimalView();
                });
                binding.menu.lnListSpecies.addView(itemView);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void initAnimalView() {
        binding.tvType.setText(viewModel.getCurrentType().toUpperCase());
        binding.vp9Animal.setAdapter(new CategoryAdapter(context, callback.currentList(), this::clickView));
        binding.vp9Animal.setPageTransformer(new DepthPageTransformer());
    }

    private void initTextToSpeech() {
        viewModel.setTts(new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    viewModel.getTts().setLanguage(Locale.UK);
                }
            }
        }));
    }

    @Override
    protected void clickView(View view) {
        if (view.getId() == R.id.ivAnimal) {
            callback.showFragment(DetailFragment.TAG, view.getTag(), true);
        }
    }

    @Override
    public Class<MainFragmentVM> initViewModel() {
        return MainFragmentVM.class;
    }

    @Override
    public FragmentMainBinding initViewBinding() {
        return FragmentMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onDestroy() {
        if (viewModel.getTts() != null) {
            viewModel.getTts().stop();
            viewModel.getTts().shutdown();
        }
        super.onDestroy();
    }
}
