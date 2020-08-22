package com.technobrix.tbx.safedoors.Profile;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.GetProfilePOJO.GetProfileBean;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ProfileInfoFragment extends Fragment {

    TextView email , dob , flat, pa, male, edit , society , age , phone , name;

    ProgressBar bar;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_info_fragment , container , false);

        email = (TextView)view.findViewById(R.id.email);
        dob = (TextView)view.findViewById(R.id.dob);
        flat = (TextView)view.findViewById(R.id.flat);
        pa = (TextView)view.findViewById(R.id.address);
        male = (TextView)view.findViewById(R.id.male);
        edit = (TextView)view.findViewById(R.id.edit);
        society = (TextView)view.findViewById(R.id.society);
        age = (TextView)view.findViewById(R.id.age);
        phone = (TextView)view.findViewById(R.id.phone);
       // name = (TextView)view.findViewById(R.id.name);
        bar = (ProgressBar) view.findViewById(R.id.progress);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ProfileEdit.class);
                startActivity(i);
            }
        });

        bar.setVisibility(View.VISIBLE);

        bean b = (bean)getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);
        Call<GetProfileBean> call = cr.getprofile(b.userId);

        Log.d("nisha" , b.userId);

        call.enqueue(new Callback<GetProfileBean>() {
            @Override
            public void onResponse(Call<GetProfileBean> call, Response<GetProfileBean> response) {

                email.setText(response.body().getEmail());

                Log.d("asdasd" , response.body().getEmail());

                dob.setText(response.body().getDob());
                pa.setText(response.body().getPermanentAddress());
                male.setText(response.body().getGender());
                phone.setText(response.body().getPhone());
                flat.setText(response.body().getFlatNo());
                society.setText(response.body().getSocityName());
                email.setText(response.body().getEmail());
                age.setText(response.body().getAge());

                bar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<GetProfileBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

                Log.d("mmmm" , t.toString());

            }
        });
        return  view;
    }
}
