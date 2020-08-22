package com.technobrix.tbx.safedoors;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.FacilityPOJO.Bean;
import com.technobrix.tbx.safedoors.FacilityPOJO.FacilityList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Facility extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    FacilityAdapter adapter;
    ProgressBar bar;
    TextView date;
    List<FacilityList> list;

    String date1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.facility , container , false);

        recyclerView = (RecyclerView)view.findViewById(R.id.facility);

        date = (TextView)view.findViewById(R.id.date);

        bar = (ProgressBar) view.findViewById(R.id.progress);

        manager = new GridLayoutManager(getContext(),1);

        list = new ArrayList<>();

        adapter = new FacilityAdapter(getContext(),list);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);

        Log.d("kamal" , "hii");

        bar.setVisibility(View.VISIBLE);

        bean b = (bean)getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<Bean> call = cr.bean("1");

        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {

                bar.setVisibility(View.GONE);

                adapter.setgriddata(response.body().getFacilityList());

            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.date_dialog);
                dialog.setCancelable(false);
                dialog.show();


                final DatePicker picker = (DatePicker)dialog.findViewById(R.id.picker);
                Button ok = (Button)dialog.findViewById(R.id.ok);
                Button cancel = (Button)dialog.findViewById(R.id.cancel);


                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String day = String.valueOf(picker.getDayOfMonth());
                        String month = String.valueOf(picker.getMonth() + 1);
                        String year = String.valueOf(picker.getYear());

                        date1 = year + "-" + month + "-" + day;

                        date.setText(year + "-" + month + "-" + day);

                        dialog.dismiss();

                    }
                });


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();


                    }
                });

            }
        });
        return view;
    }

    public class FacilityAdapter extends RecyclerView.Adapter<FacilityAdapter.MyViewHolder>{

        Context context;
        List<FacilityList> list = new ArrayList<>();


        public FacilityAdapter(Context context , List<FacilityList> list){

            this.context = context;
            this.list =  list;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.facility_list_model , parent , false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            final FacilityList item = list.get(position);
            holder.name.setText(item.getName());
            holder.lorem.setText("Price: " + item.getPricePer());
            holder.one.setText(String.valueOf(position + 1) + ".");

            holder.book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.book_dialog);
                    dialog.setCancelable(false);
                    dialog.show();


                    final TextView date = (TextView)dialog.findViewById(R.id.date);
                    final TextView time1 = (TextView)dialog.findViewById(R.id.time1);
                    final TextView time2 = (TextView)dialog.findViewById(R.id.time2);
                    Button ok = (Button)dialog.findViewById(R.id.ok);



                    date.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.datedialog);
                            dialog.setCancelable(false);
                            dialog.show();

                            final DatePicker picker = (DatePicker)dialog.findViewById(R.id.picker);
                            Button submit = (Button)dialog.findViewById(R.id.submit);

                            submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    String day = String.valueOf(picker.getDayOfMonth());
                                    String month = String.valueOf(picker.getMonth() + 1);
                                    String year = String.valueOf(picker.getYear());

                                    String date1 = year + "-" + month + "-" + day;

                                    date.setText(date1);

                                    dialog.dismiss();


                                }
                            });


                        }
                    });


                    time1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.time_dialog);
                            dialog.setCancelable(false);
                            dialog.show();

                            final TimePicker picker = (TimePicker)dialog.findViewById(R.id.picker);
                            Button ok = (Button)dialog.findViewById(R.id.ok);

                            ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                    String hour = String.valueOf(picker.getCurrentHour());
                                    String minute = String.valueOf(picker.getCurrentMinute());

                                    String time = hour + " : " + minute;

                                    time1.setText(time);
                                    dialog.dismiss();

                                }
                            });



                        }
                    });



                    time2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.time_dialog);
                            dialog.setCancelable(false);
                            dialog.show();

                            final TimePicker picker = (TimePicker)dialog.findViewById(R.id.picker);
                            Button ok = (Button)dialog.findViewById(R.id.ok);

                            ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                    String hour = String.valueOf(picker.getCurrentHour());
                                    String minute = String.valueOf(picker.getCurrentMinute());

                                    String time0 = hour + " : " + minute;

                                    time2.setText(time0);
                                    dialog.dismiss();

                                }
                            });



                        }
                    });


                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            bar.setVisibility(View.VISIBLE);

                            bean b = (bean)context.getApplicationContext();

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://safedoors.in")
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllApiInterface cr = retrofit.create(AllApiInterface.class);

                            Call<bookFacilityBean> call = cr.bookFacility(b.socity , b.house_id , item.getId() , b.userId , date.getText().toString() , item.getPricePer() , time1.getText().toString() , time2.getText().toString());

                            call.enqueue(new Callback<bookFacilityBean>() {
                                @Override
                                public void onResponse(Call<bookFacilityBean> call, Response<bookFacilityBean> response) {

                                    bar.setVisibility(View.GONE);

                                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                    dialog.dismiss();

                                }

                                @Override
                                public void onFailure(Call<bookFacilityBean> call, Throwable t) {
                                    dialog.dismiss();
                                    bar.setVisibility(View.GONE);
                                }
                            });











                        }
                    });




                }
            });

        }

        public void setgriddata(List<FacilityList> list){

            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView name , lorem , one , book;

            TextView date , time1 , time2;


            public MyViewHolder(View itemView) {
                super(itemView);

                name = (TextView)itemView.findViewById(R.id.name);
                lorem = (TextView)itemView.findViewById(R.id.text);
                one = (TextView)itemView.findViewById(R.id.one);
                book = (TextView) itemView.findViewById(R.id.book);
                date = (TextView) itemView.findViewById(R.id.date);
                time1 = (TextView) itemView.findViewById(R.id.time1);
                time2 = (TextView) itemView.findViewById(R.id.time2);


            }
        }
    }


}
