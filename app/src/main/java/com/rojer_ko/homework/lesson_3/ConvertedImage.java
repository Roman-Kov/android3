package com.rojer_ko.homework.lesson_3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.Single;

public class ConvertedImage {

    private Uri originalImageUri;

    ConvertedImage(Uri uri) {
        this.originalImageUri = uri;
    }

    Single<Uri> getUri(Context context){
        return Single.create(emitter -> {
            Bitmap bitmap = null;
            Uri pngImageUri;
            if(originalImageUri!= null) {
                try {
                    InputStream inputStream = context.getContentResolver().openInputStream(originalImageUri);
                    bitmap = BitmapFactory.decodeStream(inputStream);
                } catch (FileNotFoundException e) {
                    emitter.onError(e);
                }
                try {
                    FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/image.png");
                    if (bitmap != null) {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    }
                    fos.flush();
                    fos.close();
                    pngImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/image.png"));
                    if (!emitter.isDisposed()) {
                        emitter.onSuccess(pngImageUri);
                    }
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }
}
