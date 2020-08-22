package com.technobrix.tbx.safedoors.SplashLogin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.RegisterPOJO.RegisterBean;
import com.technobrix.tbx.safedoors.SocityPOJO.SocityBean;
import com.technobrix.tbx.safedoors.flatPOJO.flatBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Register extends AppCompatActivity {
    EditText user,email,mobile,password,repass;
    TextView ca;
    ProgressBar bar2;
    Spinner sp , sp1;
    List<String> soc_id;
    List<String> soc_name;
    List<String> h_name;
    List<String> h_id;
    String houseno = "" , socid = "";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user = (EditText) findViewById(R.id.user);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.mobile);
        sp = (Spinner) findViewById(R.id.spinner);
        sp1 = (Spinner) findViewById(R.id.spinner1);
        password = (EditText) findViewById(R.id.password);
        repass = (EditText) findViewById(R.id.repass);
        bar2 = (ProgressBar) findViewById(R.id.progress2);
        ca = (TextView) findViewById(R.id.ca);


        soc_id = new ArrayList<>();
        soc_name = new ArrayList<>();
        h_id = new ArrayList<>();
        h_name = new ArrayList<>();



        bar2.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<SocityBean> call = cr.sb();
        call.enqueue(new Callback<SocityBean>() {
            @Override
            public void onResponse(Call<SocityBean> call, Response<SocityBean> response) {


        soc_id.clear();
        soc_name.clear();

        soc_name.add("Select Society");

        for (int i = 0 ; i < response.body().getSocityList().size() ; i++)
        {
            soc_name.add(response.body().getSocityList().get(i).getSocityName());
            soc_id.add(response.body().getSocityList().get(i).getId());
        }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Register.this , android.R.layout.simple_spinner_item , soc_name);

        sp.setAdapter(adapter);
        bar2.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<SocityBean> call, Throwable t) {
bar2.setVisibility(View.GONE);
            }
        });



        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, final View view, int i, long l) {


                if (i > 0)
                {

                    h_id.clear();
                    h_name.clear();

                    h_name.add("Select House");

                    socid = soc_id.get(i-1);

                    bar2.setVisibility(View.VISIBLE);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://safedoors.in")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();



                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                    Call<flatBean> call = cr.getFlats(soc_id.get(i-1));

                    call.enqueue(new Callback<flatBean>() {
                        @Override
                        public void onResponse(Call<flatBean> call, Response<flatBean> response) {

                            for (int i = 0 ; i < response.body().getFlatList().size() ; i++)
                            {
                                h_name.add(response.body().getFlatList().get(i).getHouseNo());
                                h_id.add(response.body().getFlatList().get(i).getId());
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Register.this , android.R.layout.simple_spinner_item , h_name);

                            sp1.setAdapter(adapter);
                            bar2.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<flatBean> call, Throwable t) {
                            bar2.setVisibility(View.GONE);

                        }
                    });

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i>0)
                {
                    houseno = h_id.get(i-1);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String e = email.getText().toString();
                String u = user.getText().toString();
                String m = mobile.getText().toString();

                String p = password.getText().toString();
                String r = repass.getText().toString();



                if (e.length()>0)
                {

                    if (u.length()>0)
                    {

                        if (m.length()>0)
                        {

                            if (p.length()>0)
                            {

                                if (Objects.equals(p, r))
                                {

                                    bar2.setVisibility(View.VISIBLE);
                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl("http://safedoors.in")
                                            .addConverterFactory(ScalarsConverterFactory.create())
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                                    Call<RegisterBean> call = cr.bean( u ,e,p, socid ,houseno,r);
                                    call.enqueue(new Callback<RegisterBean>() {
                                        @Override
                                        public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {

                                            if (response.body().getMessage() == "register successfully")
                                            {
                                                Toast.makeText(Register.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                bar2.setVisibility(View.GONE);

                                                finish();
                                            }
                                            else
                                            {
                                                Toast.makeText(Register.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                bar2.setVisibility(View.GONE);

                                            }


                                        }

                                        @Override
                                        public void onFailure(Call<RegisterBean> call, Throwable t) {
                                            bar2.setVisibility(View.GONE);

                                        }
                                    });


                                }
                                else
                                {
                                    Toast.makeText(Register.this , "Password did not match" , Toast.LENGTH_SHORT).show();
                                }

                            }
                            else
                            {
                                Toast.makeText(Register.this , "Invalid Password" , Toast.LENGTH_SHORT).show();
                                password.setError("Invalid Details");
                            }

                        }
                        else
                        {
                            Toast.makeText(Register.this , "Invalid Phone" , Toast.LENGTH_SHORT).show();
                            mobile.setError("Invalid Details");
                        }

                    }
                    else
                    {
                        Toast.makeText(Register.this , "Invalid Username" , Toast.LENGTH_SHORT).show();
                        user.setError("Invalid Details");
                    }

                }
                else
                {
                    Toast.makeText(Register.this , "Invalid Email" , Toast.LENGTH_SHORT).show();
                    email.setError("Invalid Details");
                }














            }
        });




    }
}
