package com.rojer_ko.homework.lesson_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.rojer_ko.homework.R;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Lesson3MainActivity extends AppCompatActivity {

    @BindView(R.id.choseJpgImageView)
    ImageView choseJpgImageView;

    @BindView(R.id.pngImageView)
    ImageView pngImageView;

    private static final int PERMISSION_REQUEST_CODE = 10;
    private Uri uri;
    private Disposable disposable;
    private LruCache lruCache;
    private Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3_main);
        ButterKnife.bind(this);
        initPicasso();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    void initPicasso()
    {
        lruCache = new LruCache(getApplicationContext());
        picasso = new Picasso.Builder(getApplicationContext()).memoryCache(lruCache).build();
    }

    @OnClick(R.id.choseJpgImageView)
    void getPicture(){
        if(checkPermissions()) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            try {
                startActivityForResult(
                        Intent.createChooser(intent, getApplicationContext().getString(R.string.selectFileToUploadText)),
                        0);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "Please install a File Manager", Toast.LENGTH_SHORT).show();
            }
        }
        else setPermissions();
    }

    @OnClick(R.id.convertBtn)
    void convert() {
        ConvertedImage convertedImage= new ConvertedImage(uri);

        convertedImage.getUri(getApplicationContext())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Uri>(){
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(Uri uri) {
                        lruCache.clear();
                        picasso.load(uri).into(pngImageView);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            uri = data.getData();
            Picasso.get().load(uri).into(choseJpgImageView);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean checkPermissions() {
        return (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private void setPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },
                PERMISSION_REQUEST_CODE);
    }
}
