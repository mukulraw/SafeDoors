package com.technobrix.tbx.safedoors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.technobrix.tbx.safedoors.AddFamilyPOJO.AddFamilyBean;
import com.technobrix.tbx.safedoors.FamilyPOJO.FamilyBean;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Add extends AppCompatActivity {

    EditText name , age , gender , relation , user;
    Button submit;
    ProgressBar bar;

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
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String u = user.getText().toString();
                String n = name.getText().toString();
                String g = gender.getText().toString();
                String a = age.getText().toString();
                String r = relation.getText().toString();
                bar.setVisibility(View.VISIBLE);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://safedoors.in")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                Call<AddFamilyBean> call = cr.add(u , n , g , a , r );
                call.enqueue(new Callback<AddFamilyBean>() {
                    @Override
                    public void onResponse(Call<AddFamilyBean> call, Response<AddFamilyBean> response) {

                        bar.setVisibility(View.GONE);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<AddFamilyBean> call, Throwable t) {

                        bar.setVisibility(View.GONE);

                    }
                });
            }
        });
    }
}
