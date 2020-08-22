package com.technobrix.tbx.safedoors.Event;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;
import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.BuyTicket;
import com.technobrix.tbx.safedoors.EventBookPOJO.EventBookBean;
import com.technobrix.tbx.safedoors.EventDatePOJO.EventBean;
import com.technobrix.tbx.safedoors.EventDatePOJO.MeetingList;
import com.technobrix.tbx.safedoors.GetEventListPOJO.GetEventListBean;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Calender extends Fragment {

    MaterialCalendarView calendarView;
    ProgressBar progress;
    List<CalendarDay> cl;
    List<String> socIds;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    EventAdapter adapter;

    List<MeetingList> list ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calender , container,false);

        progress = (ProgressBar)view.findViewById(R.id.progress);
        calendarView = (MaterialCalendarView) view.findViewById(R.id.calender);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);


        list = new ArrayList<>();
        adapter = new EventAdapter(getContext() , list);
        manager = new GridLayoutManager(getContext(),1);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(manager);

        socIds = new ArrayList<>();

        cl = new ArrayList<>();

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {


                Log.d("asdas" , String.valueOf(date.getDay()));



                Calendar cal = Calendar.getInstance();


                int year = date.getYear();
                int month = date.getMonth() + 1;
                int day = date.getDay();


                String dat = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);

                progress.setVisibility(View.VISIBLE);

                bean b = (bean)getContext().getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://safedoors.in")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                Call<EventBean> call = cr.event(b.socity ,dat , b.userId);

                Log.d ("nkdf" , dat);

                call.enqueue(new Callback<EventBean>() {
                    @Override
                    public void onResponse(Call<EventBean> call, Response<EventBean> response) {

                        Log.d("jfj" ,"response");

                        Log.d("hfdhsk" , String.valueOf(response.body().getMeetingList().size()));

                        adapter.Setgrid(response.body().getMeetingList());

                        progress.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<EventBean> call, Throwable t) {


                        Log.d("gdg" ,t.toString());
                        progress.setVisibility(View.GONE);

                    }
                });



            }
        });




        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        updateCalendar();

    }

    public void updateCalendar()
    {

        progress.setVisibility(View.VISIBLE);

        cl.clear();
        final Calendar calendar = Calendar.getInstance();

        socIds.clear();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        AllApiInterface cr = retrofit.create(AllApiInterface.class);


        bean b = (bean)getContext().getApplicationContext();

        Call<GetEventListBean> call = cr.getevent(b.socity , b.userId);

        call.enqueue(new Callback<GetEventListBean>() {
            @Override
            public void onResponse(Call<GetEventListBean> call, Response<GetEventListBean> response) {


                Log.d("fjgbdfj" , "responflk");
                for (int i = 0 ; i < response.body().getMeetingList().size() ; i++)
                {

                    socIds.add(response.body().getMeetingList().get(i).getId());


                    String dat = response.body().getMeetingList().get(i).getMeetingDate();

                    Log.d("adsad" , dat);

                    String[] d1 = dat.split("-");


                    int year = Integer.parseInt(d1[0]);
                    int day = Integer.parseInt(d1[2]);
                    int month = Integer.parseInt(d1[1]) - 1;

                    Log.d("asdasd" , String.valueOf(day) + " " + String.valueOf(month) + " " + String.valueOf(year));

                    calendar.set(year , month , day);

                    CalendarDay calendarDay = CalendarDay.from(calendar);
                    cl.add(calendarDay);

                }

                progress.setVisibility(View.GONE);

                calendarView.addDecorators(new EventDecorator(Color.RED, cl));

            }

            @Override
            public void onFailure(Call<GetEventListBean> call, Throwable t) {

                progress.setVisibility(View.GONE);

                Log.d("ghfdghfd" , t.toString());
            }
        });


    }


    private class EventDecorator implements DayViewDecorator {

        private final int color;
        private final HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates) {
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(15, color));
        }
    }



    public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {


        Context context;

        List<MeetingList> list = new ArrayList<>();

        public EventAdapter(Context context ,  List<MeetingList> list){

            this.context = context;

            this .list = list;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.event_list_model , parent , false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {


            final MeetingList item = list.get(position);

            String daa = item.getMeetingDate();
            String[] d1 = daa.split("-");

            holder.d.setText(d1[2]);
            holder. m.setText(d1[1] + " " + d1[0]);

            holder. title.setText(item.getTitle());
            holder.desc.setText(item.getDescription());
            holder.time.setText(item.getMeetingTime());


           Log.d("jkdfg" , item.getEventType());

            if (Objects.equals(item.getEventType(), "unpaid"))
            {
                holder.paid.setText("FREE");
                holder.buyticket.setVisibility(View.GONE);
            }
            else
            {
                holder.paid.setText("INR " + item.getEventPrice());
                holder.buyticket.setVisibility(View.VISIBLE);
            }




            holder.buyticket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    progress.setVisibility(View.VISIBLE);

                    bean b = (bean)context.getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://safedoors.in")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                    Call<EventBookBean> call = cr.eventbook(b.socity , b.house_id , b.userId , item.getId() , item.getEventPrice());

                    call.enqueue(new Callback<EventBookBean>() {
                        @Override
                        public void onResponse(Call<EventBookBean> call, Response<EventBookBean> response) {

                            Intent i = new Intent(context , BuyTicket.class);
                            i.putExtra("title" , item.getTitle());
                            context.startActivity(i);
                            progress.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<EventBookBean> call, Throwable t) {

                            progress.setVisibility(View.GONE);


                        }
                    });


                }
            });

        }

        public void Setgrid(List<MeetingList> list){

            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount()  {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView d , m , title , desc , time;

            Button paid , buyticket;

            public MyViewHolder(View itemView) {
                super(itemView);

                d = (TextView)itemView.findViewById(R.id.day);
                m = (TextView)itemView.findViewById(R.id.month);
                title = (TextView)itemView.findViewById(R.id.title);
                time = (TextView)itemView.findViewById(R.id.time);
                desc = (TextView)itemView.findViewById(R.id.description);

                paid = (Button)itemView.findViewById(R.id.paid);
                buyticket = (Button)itemView.findViewById(R.id.butticket);

            }
        }
    }




}
