package com.technobrix.tbx.safedoors.MeetingArragemenmt;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.Create_MeetingPOJO.CreateBean;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Meeting extends Fragment {

   LinearLayout date1 , date2 , date3 ;

    TextView   time1 , time2 , time3 , submit;

    EditText title, descrp;
    Context context;
    ProgressBar bar;
    String date , starttime , endtime;
    Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.meeting , container , false);

        title = (EditText)view.findViewById(R.id.meetingtitle) ;
         descrp = (EditText)view.findViewById(R.id.meeetingd) ;
        bar = (ProgressBar)view.findViewById(R.id.progress);

        date1 = (LinearLayout)view.findViewById(R.id.date1);
        date2 = (LinearLayout)view.findViewById(R.id.date2);
        date3 = (LinearLayout)view.findViewById(R.id.date3);

        time1 = (TextView)view.findViewById(R.id.time1);
        time2 = (TextView)view.findViewById(R.id.time2);
        time3 = (TextView)view.findViewById(R.id.time3);

        submit = (TextView)view.findViewById(R.id.submit);



        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.meetingdialog);
                dialog.setCancelable(true);
                dialog.show();

                TextView submit = dialog.findViewById(R.id.submit);
                final DatePicker picker = dialog.findViewById(R.id.picker);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String day = String.valueOf(picker.getDayOfMonth());
                        String month = String.valueOf(picker.getMonth() + 1);
                        String year = String.valueOf(picker.getYear());

                        date = year + "-" + month + "-" + day;

                        time1.setText(year + "-" + month + "-" + day);

                        dialog.dismiss();

                    }
                });

            }
        });


        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.timepicker);
                dialog.setCancelable(true);
                dialog.show();

                Button submit = dialog.findViewById(R.id.submit);
                final TimePicker picker = dialog.findViewById(R.id.time);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String hour = String.valueOf(picker.getCurrentHour());
                        String minute = String.valueOf(picker.getCurrentMinute());

                        starttime = hour + " : " + minute;

                        time2.setText(hour + " : " + minute);
                        dialog.dismiss();


                    }
                });
            }

        });

        date3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.timepicker);
                dialog.setCancelable(true);
                dialog.show();


                Button submit = dialog.findViewById(R.id.submit);
                final TimePicker picker = dialog.findViewById(R.id.time);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String hour = String.valueOf(picker.getCurrentHour());
                        String minute = String.valueOf(picker.getCurrentMinute());

                        time3.setText(hour + " : " + minute);

                        dialog.dismiss();

                    }
                    });
                    }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                String t = title.getText().toString();
                String des = descrp.getText().toString();
                bar.setVisibility(View.VISIBLE);

                bean b = (bean)getContext().getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://safedoors.in")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);
                Call<CreateBean> call = cr.creat(b.socity , b.userId ,date , starttime , t  , des);

                Log.d("hdfshg" , b.socity);
                Log.d("hdfshg" , b.userId);
                Log.d("hdfshg" , date);
                Log.d("hdfshg" , starttime);
                Log.d("hdfshg" , t);
                Log.d("hdfshg" , des);

                call.enqueue(new Callback<CreateBean>() {
                    @Override
                    public void onResponse(Call<CreateBean> call, Response<CreateBean> response) {

                        Toast.makeText(getContext() , response.body().getStatus() , Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<CreateBean> call, Throwable t) {
                        bar.setVisibility(View.GONE);

                    }
                });
            }
        });

        return view;
    }
}
