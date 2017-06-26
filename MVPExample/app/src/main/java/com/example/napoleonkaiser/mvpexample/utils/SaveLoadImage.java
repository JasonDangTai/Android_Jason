package com.example.napoleonkaiser.mvpexample.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.napoleonkaiser.mvpexample.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by napoleonkaiser on 25/06/2017.
 */

public class SaveLoadImage extends AppCompatActivity{
    public void getBitmapFromURL(final Context context , String src, final int position) {

        final BasicImageDownloader downloader = new BasicImageDownloader(new BasicImageDownloader.OnImageLoaderListener() {
            @Override
            public void onError(BasicImageDownloader.ImageError error) {
                Toast.makeText(context, "Error code " + error.getErrorCode() + ": " +
                        error.getMessage(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }

            @Override
            public void onProgressChange(int percent) {

            }

            @Override
            public void onComplete(Bitmap result) {
                final Bitmap.CompressFormat mFormat = Bitmap.CompressFormat.JPEG;
                final File myImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                        File.separator + "image_test" + File.separator + "jason" + position + "." + mFormat.name().toLowerCase());
                BasicImageDownloader.writeToDisk(myImageFile, result, new BasicImageDownloader.OnBitmapSaveListener() {
                    @Override
                    public void onBitmapSaved() {
                        Toast.makeText(context, "Image saved as: " + myImageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBitmapSaveError(BasicImageDownloader.ImageError error) {
                        Toast.makeText(context, "Error code " + error.getErrorCode() + ": " +
                                error.getMessage(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }, mFormat, false);
            }
        });
        downloader.download(src, true);
    }
    public Bitmap loadImageFromStorage(File path, String name)
    {

        try {
            File f=new File(path, name);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            return b;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }

    }
}
