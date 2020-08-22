package com.technobrix.tbx.safedoors.SplashLogin;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.ForgotPOJO.ForgotBean;
import com.technobrix.tbx.safedoors.LoginPOJO.LoginBean;
import com.technobrix.tbx.safedoors.MainActivity;
import com.technobrix.tbx.safedoors.NewGatekeeper.GateHome;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Login extends AppCompatActivity {

    EditText mail,pass;
    TextView create,forget,signin;
    ProgressBar bar3;
    SharedPreferences pref;
    SharedPreferences.Editor edit;
    int RC_SIGN_IN = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("pref" , Context.MODE_PRIVATE);
        edit = pref.edit();

        mail = (EditText) findViewById(R.id.mail);

        pass  = (EditText) findViewById(R.id.pass);
        create  = (TextView) findViewById(R.id.create);
        signin  = (TextView) findViewById(R.id.signin);
        forget  = (TextView) findViewById(R.id.forget);

        bar3 = (ProgressBar) findViewById(R.id.progress3);





        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(Login.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog);
                dialog.setCancelable(true);


                final EditText em = (EditText)dialog.findViewById(R.id.edit);
                Button submit = (Button)dialog.findViewById(R.id.submit);
                dialog.show();
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String email = em.getText().toString();

                        if (email.length()>0)
                        {

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://safedoors.in")
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllApiInterface cr = retrofit.create(AllApiInterface.class);
                            Call<ForgotBean> call = cr.forgot(em.getText().toString());
                            call.enqueue(new Callback<ForgotBean>() {
                                @Override
                                public void onResponse(Call<ForgotBean> call, Response<ForgotBean> response) {


                                    if (Objects.equals(response.body().getMessage(), "Please check your email")){


                                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                                        dialog.dismiss();


                                    }
                                    else {
                                        Toast.makeText(Login.this, "Email id not correct", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }


                                }

                                @Override
                                public void onFailure(Call<ForgotBean> call, Throwable t) {

                                    dialog.dismiss();

                                }
                            });

                        }
                        else
                        {
                            em.setError("Invalid Details");
                            Toast.makeText(Login.this , "Invalid Email" , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final String e = mail.getText().toString();
                final String u = pass.getText().toString();

                if (e.length()>0)
                {

                    if (u.length()>0)
                    {

                        bar3.setVisibility(View.VISIBLE);

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://safedoors.in")
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllApiInterface cr = retrofit.create(AllApiInterface.class);
                        Call<LoginBean> call = cr.login( e , u);
                        call.enqueue(new Callback<LoginBean>() {
                            @Override
                            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {


                                if (Objects.equals(response.body().getMessage(), "Login success")){


                                    if (Objects.equals(response.body().getType(), "member"))
                                    {

                                        bean b = (bean)getApplicationContext();

                                        b.userId = response.body().getUserid();

                                        b.name = response.body().getUsername();

                                        b.socity = response.body().getSocityId();

                                        b.flat = response.body().getHouseNo();

                                        b.house_id = response.body().getHouseId();

                                        b.member_id = response.body().getUserid();

                                        edit.putString("email" , e);
                                        edit.putString("pass" , u);
                                        edit.apply();


                                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Login.this, MainActivity.class);
                                        bar3.setVisibility(View.GONE);
                                        startActivity(i);
                                        finish();

                                    }
                                    else if (Objects.equals(response.body().getType(), "gatekeeper"))
                                    {
                                        bean b = (bean)getApplicationContext();
                                        b.userId = response.body().getUserid();
                                        b.name = response.body().getUsername();
                                        b.flat = response.body().getHouseNo();
                                        b.socity = response.body().getSocityId();

                                        edit.putString("email" , e);
                                        edit.putString("pass" , u);
                                        edit.apply();


                                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Login.this, GateHome.class);
                                        bar3.setVisibility(View.GONE);
                                        startActivity(i);
                                        finish();

                                    }




                                }
                                else {
                                    Toast.makeText(Login.this, "Login detail invalid", Toast.LENGTH_SHORT).show();
                                    bar3.setVisibility(View.GONE);
                                }


                            }

                            @Override
                            public void onFailure(Call<LoginBean> call, Throwable t) {
                                bar3.setVisibility(View.GONE);

                                Log.d("dfhnld" ,t.toString());

                            }
                        });

                    }
                    else
                    {
                        Toast.makeText(Login.this , "Invalid Password" , Toast.LENGTH_SHORT).show();
                        pass.setError("Invalid Details");
                    }

                }
                else
                {
                    Toast.makeText(Login.this , "Invalid Email" , Toast.LENGTH_SHORT).show();
                    mail.setError("Invalid Details");
                }


            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login . this , Register.class);
                startActivity(i);
            }
        });

    }



    private boolean isValidMail(String email) {

        boolean check;
        Pattern p;
        Matcher m;

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);

        m = p.matcher(email);
        check = m.matches();
        return check;
    }


    private boolean isValidMobile(String phone) {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            if(phone.length() < 6 || phone.length() > 13) {
                // if(phone.length() != 10) {
                check = false;

            } else {
                check = true;
            }
        } else {
            check=false;
        }
        return check;
    }

}

