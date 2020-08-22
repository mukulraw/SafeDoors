package com.technobrix.tbx.safedoors.SplashLogin;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.LoginPOJO.LoginBean;
import com.technobrix.tbx.safedoors.MainActivity;
import com.technobrix.tbx.safedoors.NewGatekeeper.GateHome;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Splash extends AppCompatActivity {

    Timer time;

    SharedPreferences pref;

    String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA};
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(Splash.this));

        pref  = getSharedPreferences("pref" , Context.MODE_PRIVATE);

        bar = (ProgressBar) findViewById(R.id.progress);




        if(hasPermissions(this , PERMISSIONS))
        {
            startApp();
        }
        else
        {
            ActivityCompat.requestPermissions(this , PERMISSIONS , REQUEST_CODE_ASK_PERMISSIONS);
        }




    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS)
        {
            if (ActivityCompat.checkSelfPermission(getApplicationContext() , Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            {

                startApp();

            }
            else
            {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    Toast.makeText(getApplicationContext() , "Permissions are required for this app" , Toast.LENGTH_SHORT).show();
                    finish();

                }
                //permission is denied (and never ask again is  checked)
                //shouldShowRequestPermissionRationale will return false
                else {
                    Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG)
                            .show();
                    finish();
                    //                            //proceed with logic by disabling the related features or quit the app.
                }
            }

        }


    }


    public void startApp()
    {
        String email = pref.getString("email" , "");
        String pass = pref.getString("pass" , "");



        if (email.length()>0 && pass.length()>0)
        {

            bar.setVisibility(View.VISIBLE);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);
            Call<LoginBean> call = cr.login( email , pass);
            call.enqueue(new Callback<LoginBean>() {
                @Override
                public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {


                    if (Objects.equals(response.body().getMessage(), "Login success")){


                        if (Objects.equals(response.body().getType(), "member"))
                        {

                            bean b = (bean)getApplicationContext();

                            b.userId = response.body().getUserid();

                            b.name = response.body().getUsername();
                            b.flat = response.body().getHouseNo();

                            b.socity = response.body().getSocityId();

                            b.house_id = response.body().getHouseId();

                            b.member_id = response.body().getUserid();




                            Toast.makeText(Splash.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Splash.this, MainActivity.class);
                            bar.setVisibility(View.GONE);
                            startActivity(i);
                            finish();

                        }
                        else if (Objects.equals(response.body().getType(), "gatekeeper"))
                        {
                            bean b = (bean)getApplicationContext();
                            b.userId = response.body().getUserid();
                            b.flat = response.body().getHouseNo();
                            b.name = response.body().getUsername();
                            b.socity = response.body().getSocityId();
                            Toast.makeText(Splash.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Splash.this, GateHome.class);
                            bar.setVisibility(View.GONE);
                            startActivity(i);
                            finish();

                        }

                    }
                    else {
                        Toast.makeText(Splash.this, "Login detail invalid", Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onFailure(Call<LoginBean> call, Throwable t) {
                    bar.setVisibility(View.GONE);

                }
            });

        }
        else {

            time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {

                    Intent i = new Intent(Splash.this , GetStarted.class);
                    startActivity(i);
                    finish();

                }
            } , 1500);

        }
    }

}
