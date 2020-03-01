package com.rojer_ko.homework.lesson_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.rojer_ko.homework.R;
import com.squareup.picasso.Picasso;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Lesson3MainActivity extends AppCompatActivity {

    @BindView(R.id.choseJpgImageView)
    ImageView choseJpgImageView;

    @BindView(R.id.pngImageView)
    ImageView pngImageView;

    private static final int PERMISSION_REQUEST_CODE = 10;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3_main);
        ButterKnife.bind(this);
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
        if(uri!= null) {
            Bitmap bitmap = null;
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                    FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/image.png");
                if (bitmap != null) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                }
                fos.flush();
                fos.close();
                Uri pngImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/image.png"));
                Picasso.get().load(pngImageUri).into(pngImageView);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    Log.e("Error", e.toString());
                }
            }
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
