package com.technobrix.tbx.safedoors.Profile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.ProfilePOJO.SetProfileBean;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Edit extends AppCompatActivity {

    EditText name , gender , dob , age , address;

    Button create;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        name = (EditText) findViewById(R.id.fn);
        gender = (EditText) findViewById(R.id.male);
        dob = (EditText) findViewById(R.id.dob);
        address = (EditText) findViewById(R.id.pa);
        age = (EditText) findViewById(R.id.age);
        create = (Button) findViewById(R.id.create);
        bar = (ProgressBar) findViewById(R.id.progress);

        String g = gender.getText().toString();
        String d = dob.getText().toString();
        String aa = address.getText().toString();
        String a = age.getText().toString();

        bar.setVisibility(View.VISIBLE);
        bean b = (bean)getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<SetProfileBean> call = cr.setprofile(b.userId , b.socity , g , d , aa , a);

        call.enqueue(new Callback<SetProfileBean>() {
            @Override
            public void onResponse(Call<SetProfileBean> call, Response<SetProfileBean> response) {

                Toast.makeText(Edit.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                bar.setVisibility(View.GONE);

                finish();

            }

            @Override
            public void onFailure(Call<SetProfileBean> call, Throwable t) {


                bar.setVisibility(View.GONE);
            }
        });

    }
}
