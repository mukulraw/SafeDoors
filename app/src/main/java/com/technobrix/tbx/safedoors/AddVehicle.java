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

import com.technobrix.tbx.safedoors.AddVehiclePOJO.AddBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AddVehicle extends AppCompatActivity {


    Toolbar toolbar;
    EditText name , vehicle , novehicle;
    Button add;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        toolbar = (Toolbar) findViewById(R.id .toolbar);
        name = (EditText) findViewById(R.id .name);
        vehicle = (EditText) findViewById(R.id .vehicle);
        novehicle = (EditText) findViewById(R.id .novehicle);
        bar = (ProgressBar) findViewById(R.id.bar);
        add = (Button) findViewById(R.id .add);
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

        toolbar.setTitle("Add Vehicle");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                bar.setVisibility(View.VISIBLE);

                String n = name.getText().toString();
                String v = vehicle.getText().toString();
                String nv = novehicle.getText().toString();

                Log.d("hii" , "hello");

                bean b = (bean)getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://safedoors.in")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                Call<AddBean> call = cr.addbean(b.userId , n , nv , v );
                call.enqueue(new Callback<AddBean>() {
                    @Override
                    public void onResponse(Call<AddBean> call, Response<AddBean> response) {

                        //Toast.makeText(AddVehicle.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();

                        bar.setVisibility(View.GONE);
                        finish();

                        Log.d("nisha" , "response");

                    }

                    @Override
                    public void onFailure(Call<AddBean> call, Throwable t) {

                        bar.setVisibility(View.GONE);


                        Log.d("mukul" , t.toString());
                    }
                });

            }
        });
    }
}
