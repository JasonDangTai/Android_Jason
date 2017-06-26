package com.example.napoleonkaiser.mvpexample.login;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.napoleonkaiser.mvpexample.Data.Username;
import com.example.napoleonkaiser.mvpexample.MainActivity;
import com.example.napoleonkaiser.mvpexample.R;
import com.example.napoleonkaiser.mvpexample.utils.MoveCopyDeleteFile;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;

/**
 * Created by napoleonkaiser on 20/06/2017.
 */

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {

    private static final int REQUEST_ID_READ_PERMISSION = 100;
    private static final int REQUEST_ID_WRITE_PERMISSION = 200;

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private CheckBox checkBox;
    private ImageView ivPhoto;
    private String perfName, perfName2;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.chksaveacount);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);

        perfName = "jason.vinova.sg";
        perfName2 = "password.jason.vinova.sg";
        findViewById(R.id.button).setOnClickListener(this);
        presenter = new LoginPresenterImpl(this);
        askPermissionAndReadFile();


        File sdCard = Environment.getExternalStorageDirectory();

        File directory = new File (sdCard.getAbsolutePath() + "/Jason's Folder");

        File file = new File(directory, "profile.png");
        try {
            FileInputStream fileIn = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(fileIn);
            ivPhoto.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inPreferredConfig = Bitmap.Config.ARGB_8888;


//        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Username user1 = realm.createObject(Username.class);
                user1.setName("Test Realm");
            }
        });


        Username username = realm.where(Username.class).equalTo("name", "Test Realm").findFirst();
        Toast.makeText(this, "  " + username.getName(), Toast.LENGTH_SHORT).show();

        //  Xoa file tren Internal storage bang deleteFile.
        /*deleteFile(perfName2);*/

        //  Xoa file bang goi .delete
        /*
        File dir = getFilesDir();
        File file = new File(dir, perfName);
        boolean deleted = file.delete();*/

    }


    @Override
    protected void onResume() {
        super.onResume();
        readFileExternal();
    }



    private void askPermissionAndWriteFile() {
        boolean canWrite = this.askPermission(REQUEST_ID_WRITE_PERMISSION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //
        if (canWrite) {
            this.writeFileExternal();
        }
    }

    private void askPermissionAndReadFile() {
        boolean canRead = this.askPermission(REQUEST_ID_READ_PERMISSION,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        //
        if (canRead) {
            this.readFileExternal();
        }
    }


    private boolean askPermission(int requestId, String permissionName) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {

            int permission = ActivityCompat.checkSelfPermission(this, permissionName);


            if (permission != PackageManager.PERMISSION_GRANTED) {


                this.requestPermissions(
                        new String[]{permissionName},
                        requestId
                );
                return false;
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (grantResults.length > 0) {
            switch (requestCode) {
                case REQUEST_ID_READ_PERMISSION: {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        readFileExternal();
                    }
                }
                case REQUEST_ID_WRITE_PERMISSION: {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        writeFileExternal();
                    }
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "Permission Cancelled!", Toast.LENGTH_SHORT).show();
        }
    }


    private void writeFileExternal() {

        File extStorage = Environment.getExternalStorageDirectory();
        String path = extStorage.getAbsolutePath() + "/" + perfName;

        File myFile = new File(path);
        try {
            myFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(myFile);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

            outputStreamWriter.append(username.getText().toString());
            outputStreamWriter.close();
            fileOutputStream.close();
            Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readFileExternal() {
        File extStorage = Environment.getExternalStorageDirectory();
        String path = extStorage.getAbsolutePath() + "/" + perfName;

        File myFile = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(myFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String s = "";
            String contentFile = "";


            if ((s = bufferedReader.readLine()) != null) {
                contentFile = s + "\n";
            }

            bufferedReader.close();
            this.username.setText(contentFile);


            Toast.makeText(this, contentFile, Toast.LENGTH_SHORT).show();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteFile() {
        File extStore = Environment.getExternalStorageDirectory();
        String path = extStore.getAbsolutePath() + "/" + perfName;
        File file = new File(path);

        Boolean deleted = file.delete();
        if (deleted) Toast.makeText(this, "delete success", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "delete false", Toast.LENGTH_SHORT).show();
    }


    private void saveData() {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        try {
            FileOutputStream out = this.openFileOutput(perfName, MODE_PRIVATE);
            FileOutputStream out2 = this.openFileOutput(perfName2, MODE_PRIVATE);
            out.write(user.getBytes());
            out2.write(pass.getBytes());
            out.close();
            out2.close();
            Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData() {
        try {
            FileInputStream in = this.openFileInput(perfName);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            this.username.setText(sb.toString());
            Toast.makeText(this, "username     " + sb.toString(), Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void savingPreferences() {
        SharedPreferences preferences = getSharedPreferences(perfName, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String user = username.getText().toString();
        String pass = password.getText().toString();
        Boolean check = checkBox.isChecked();
        if (!check) {
            editor.clear();
        } else {
            editor.putString("user", user);
            editor.putString("pass", pass);
            editor.putBoolean("check", check);
        }
        editor.commit();
    }

    public void restoringPrefferences() {
        SharedPreferences preferences = getSharedPreferences(perfName, MODE_PRIVATE);
        boolean check = preferences.getBoolean("check", false);
        if (check) {
            username.setText(preferences.getString("user", ""));
            password.setText(preferences.getString("pass", ""));
        }
        checkBox.setChecked(check);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void setUsernameError() {
        username.setError("Username cannot be empty");
    }

    @Override
    public void setPasswordError() {
        username.setError("Password cannot be empty");
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        presenter.validate(username.getText().toString(), password.getText().toString());
        askPermissionAndWriteFile();
    }
}
