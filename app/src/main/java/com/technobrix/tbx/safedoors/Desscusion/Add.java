package com.technobrix.tbx.safedoors.Desscusion;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.technobrix.tbx.safedoors.AddTpicPOJO.AddTopicBean;
import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Add extends AppCompatActivity {

    EditText subject , body;
    Toolbar toolbar;
    Button add;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);
        body = (EditText) findViewById(R.id.body);
        subject = (EditText) findViewById(R.id.subject);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bar = (ProgressBar) findViewById(R.id.progress);
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

        toolbar.setTitle("Add Topic");

        add = (Button) findViewById(R.id.topic);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                bar.setVisibility(View.VISIBLE);
                String s = subject.getText().toString();
                String bo = body.getText().toString();

                bean b = (bean)getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://safedoors.in")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                Call<AddTopicBean> call = cr.addtopic(b.socity , b.house_id , b.member_id , s , bo);

                Log.d("m" , b.socity);
                Log.d("u" , b.house_id);
                Log.d("k" , b.member_id);
                Log.d("n" , s);
                Log.d("l" , bo);

                call.enqueue(new Callback<AddTopicBean>() {
                    @Override
                    public void onResponse(Call<AddTopicBean> call, Response<AddTopicBean> response) {

                        //Toast.makeText(Add.this, String.valueOf(response.body().getStatus()), Toast.LENGTH_SHORT).show();

                        bar.setVisibility(View.GONE);

                        finish();


                    }

                    @Override
                    public void onFailure(Call<AddTopicBean> call, Throwable t) {

                        bar.setVisibility(View.GONE);

                    }
                });




            }
        });


    }
}
