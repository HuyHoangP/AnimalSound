package com.hhp.animalsound.view.fragment;

import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.viewpager2.widget.ViewPager2;

import com.hhp.animalsound.ATask;
import com.hhp.animalsound.App;
import com.hhp.animalsound.R;
import com.hhp.animalsound.databinding.FragmentDetailBinding;
import com.hhp.animalsound.model.Animal;
import com.hhp.animalsound.view.adapter.DetailAdapter;
import com.hhp.animalsound.view.base.BaseFragment;
import com.hhp.animalsound.view.dialog.DetailInfoDialog;
import com.hhp.animalsound.view.transformer.ZoomOutPageTransformer;
import com.hhp.animalsound.viewmodel.DetailFragmentVM;

import java.io.FileOutputStream;
import java.io.InputStream;

public class DetailFragment extends BaseFragment<FragmentDetailBinding, DetailFragmentVM> {
    public static final String TAG = DetailFragment.class.getName();

    @Override
    public void initView() {
        setViewPager2();
    }


    private void setViewPager2() {
        binding.vpDetailAnimal.setAdapter(new DetailAdapter(context, callback.currentList(), this::clickView));
        binding.vpDetailAnimal.setPageTransformer(new ZoomOutPageTransformer());
        binding.vpDetailAnimal.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                Animal animal = callback.currentList().get(position);
                binding.tvAnimal.setText(animal.getName());
            }
        });
        binding.vpDetailAnimal.setCurrentItem(callback.currentList().indexOf((Animal) data), false);
    }

    @Override
    public Class<DetailFragmentVM> initViewModel() {
        return DetailFragmentVM.class;
    }

    @Override
    public FragmentDetailBinding initViewBinding() {
        return FragmentDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void clickView(View view) {
        if (view.getId() == R.id.ivPlay) {
            playSound((Animal) view.getTag());
        } else if (view.getId() == R.id.ivSearch) {
            openDialog((Animal) view.getTag());
        } else if (view.getId() == R.id.ivDownload) {
            alertDownload(view);
        }
    }

    private void alertDownload(View view) {
        AlertDialog alert = new AlertDialog.Builder(context).create();
        alert.setTitle("Alert");
        alert.setMessage("Save this image to storage?");
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "YES", (dialogInterface, i) -> saveToStorage((Animal) view.getTag()));
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "NO", (dialogInterface, i) -> {
        });
        alert.show();
    }

    public void saveToStorage(Animal animal) {
//        if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
//            return;
//        }
        new ATask("KEY_SAVE_TO_STORAGE", new ATask.OnATaskCallback() {
            @Override
            public Object execTask(String key, Object param, ATask task) {
                try {
//                String savePath = App.getInstance().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath();
                    String savePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    FileOutputStream out = new FileOutputStream(savePath + "/" + animal.getName() + ".png");

                    InputStream in = context.getAssets().open(animal.getImagePath());
                    byte[] buff = new byte[1024];
                    int length = in.read(buff);
                    while (length > 0) {
                        out.write(buff, 0, length);
                        length = in.read(buff);
                    }
                    out.close();
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                return true;
            }

            @Override
            public void completeTask(String key, Object result) {
                Toast.makeText(context, result == null ? "Please try again" : "Photo is saved", Toast.LENGTH_SHORT).show();
            }
        }).startAsync(null);
    }

    private void openDialog(Animal animal) {
        DetailInfoDialog dialog = new DetailInfoDialog(animal);
        dialog.setCancelable(true);
        dialog.show(getChildFragmentManager(), DetailInfoDialog.TAG);
    }
//
//    private void searchAnimal(Animal animal) {
//        String animalName = animal.getName();
//        String word = URLEncoder.encode(animalName, StandardCharsets.UTF_8);
//        Uri uri = Uri.parse("https://www.google.com/search?hl=en&q=" + word);
//        startActivity(new Intent(Intent.ACTION_VIEW, uri));
//    }

    private void playSound(Animal animal) {
        try {
            AssetFileDescriptor afd = App.getInstance().getAssets().openFd(animal.getSoundPath());
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.prepare();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
