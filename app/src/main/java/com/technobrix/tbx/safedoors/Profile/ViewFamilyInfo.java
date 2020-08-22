package com.technobrix.tbx.safedoors.Profile;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.DeleteFamily.DeleteFamilyBean;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ViewFamilyInfo extends AppCompatActivity {


    Toolbar toolbar;

    TextView name , age , gender , relation , user , pass;

    Button submit;

    ProgressBar bar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_family_info);


        name = (TextView) findViewById(R.id.name);

        age = (TextView) findViewById(R.id.age);

        bar = (ProgressBar) findViewById(R.id.bar);

        gender = (TextView) findViewById(R.id.gender);

        relation = (TextView) findViewById(R.id.relation);

        user = (TextView) findViewById(R.id.user);

        pass = (TextView) findViewById(R.id.pass);

        bar = (ProgressBar) findViewById(R.id.progress);

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


        Bundle b = getIntent().getExtras();

        String n = b.getString("name");
        String a = b.getString("age");
        String male = b.getString("male");
        String r = b.getString("son");


        name.setText(n);
        age.setText(a);
        gender.setText(male);
        relation.setText(r);



        toolbar.setTitle("View Profile");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete , menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.delete)
        {

            /*Intent i = new Intent(ViewFamilyInfo. this , Notification.class);
            startActivity(i);*/


            bar.setVisibility(View.VISIBLE);

            bean b = (bean)getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);

            Call<DeleteFamilyBean> call = cr.del(b.socity ,b.userId , b.house_id , b.family_id);

            call.enqueue(new Callback<DeleteFamilyBean>() {
                @Override
                public void onResponse(Call<DeleteFamilyBean> call, Response<DeleteFamilyBean> response) {


                    Toast.makeText(ViewFamilyInfo.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    bar.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<DeleteFamilyBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                }
            });

        }

        return super.onOptionsItemSelected(item);
    }



}
