package com.technobrix.tbx.safedoors;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.technobrix.tbx.safedoors.AddFamilyPOJO.AddFamilyBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Add extends AppCompatActivity {

    EditText name , age , gender , relation , user , pass;

    Button submit;

    ProgressBar bar;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = (EditText) findViewById(R.id.name);

        age = (EditText) findViewById(R.id.age);

        bar = (ProgressBar) findViewById(R.id.bar);

        gender = (EditText) findViewById(R.id.gender);

        relation = (EditText) findViewById(R.id.relation);

        user = (EditText) findViewById(R.id.user);

        pass = (EditText) findViewById(R.id.pass);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();


            }

        });


        toolbar.setTitle("Member");

        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bar.setVisibility(View.VISIBLE);

                Log.d("hmm" , "response");

                //String u = user.getText().toString();
                String n = name.getText().toString();
                String g = gender.getText().toString();
                String a = age.getText().toString();
                String r = relation.getText().toString();
                bar.setVisibility(View.VISIBLE);

                bean b = (bean)getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://safedoors.in")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                Call<AddFamilyBean> call = cr.add(b.userId , n , g , a , r );
                call.enqueue(new Callback<AddFamilyBean>() {
                    @Override
                    public void onResponse(Call<AddFamilyBean> call, Response<AddFamilyBean> response) {

                        bean b = (bean)getApplicationContext();

                        bar.setVisibility(View.GONE);

                        Log.d("gkfsdg" , "mukul");
                        finish();
                    }

                    @Override
                    public void onFailure(Call<AddFamilyBean> call, Throwable t) {

                        bar.setVisibility(View.GONE);

                        Log.d("dfhglf" , t.toString());

                    }
                });
            }
        });
    }
}
