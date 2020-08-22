package com.technobrix.tbx.safedoors.NewGatekeeper;

import android.content.Context;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.StaffListPOJO.staffListBean;
import com.technobrix.tbx.safedoors.bean;
import com.technobrix.tbx.safedoors.entryBean;
import com.technobrix.tbx.safedoors.visitorListPOJO.VisitorList;
import com.technobrix.tbx.safedoors.visitorListPOJO.visitorListBean;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegularVisitor extends AppCompatActivity {

    Button in;

    SearchableSpinner spinner;

    Toolbar toolbar;

    ProgressBar progress;

    List<String> name;

    List<String> id;

    List<String> status;

    String iidd , sstt;

    TextView already;

    RecyclerView grid;

    GridLayoutManager manager;

    RegularAdapter adapter;

    EditText filter , comment;

    List<VisitorList> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_visitor);


        grid = (RecyclerView) findViewById(R.id.grid);

        manager = new GridLayoutManager(getApplicationContext(), 1);

        list = new ArrayList<>();

        adapter = new RegularAdapter(this, list);

        grid.setAdapter(adapter);

        grid.setNestedScrollingEnabled(false);

        grid.setLayoutManager(manager);

        spinner = (SearchableSpinner) findViewById(R.id.spinner);

        in = (Button) findViewById(R.id.in);


        name = new ArrayList<>();
        id = new ArrayList<>();
        status = new ArrayList<>();

        progress = (ProgressBar) findViewById(R.id.progress);

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

        toolbar.setTitle("Regular Visitor");

        final bean b = (bean) getApplicationContext();

        progress.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        final AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<staffListBean> call = cr.getStaffList(b.socity);

        call.enqueue(new Callback<staffListBean>() {
            @Override
            public void onResponse(Call<staffListBean> call, Response<staffListBean> response) {

                for (int i = 0; i < response.body().getStaffList().size(); i++) {
                    name.add(response.body().getStaffList().get(i).getStaffName());
                    id.add(response.body().getStaffList().get(i).getStaffId());
                    status.add(response.body().getStaffList().get(i).getStaffStaus());
                }


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegularVisitor.this, R.layout.spinner_model, name);
                spinner.setAdapter(adapter);

                Log.d("fdsg" , String.valueOf(response.body().getStaffList().size()));

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<staffListBean> call, Throwable t) {
                progress.setVisibility(View.GONE);

                Log.d("jdhfsd" , t.toString());
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                iidd = id.get(i);
                sstt = status.get(i);

                if (Objects.equals(sstt, "Out")) {
                    in.setVisibility(View.VISIBLE);
                    //already.setVisibility(View.GONE);
                } else {
                    in.setVisibility(View.VISIBLE);
                    //already.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress.setVisibility(View.VISIBLE);

                Call<entryBean> call1 = cr.regularIn(b.socity, "comment", b.userId, "Regular", iidd);

                Log.d("society", b.socity);
                Log.d("user", b.userId);
                Log.d("id", iidd);

                call1.enqueue(new Callback<entryBean>() {
                    @Override
                    public void onResponse(Call<entryBean> call, Response<entryBean> entryBean) {

                        in.setVisibility(View.VISIBLE);
                       //already.setVisibility(View.GONE);

                        loadData();

                        progress.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<entryBean> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                    }
                });


            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {

        list.clear();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        progress.setVisibility(View.VISIBLE);

        bean b = (bean) getApplicationContext();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);


        Call<visitorListBean> call = cr.getVisitors(b.socity);

        call.enqueue(new Callback<visitorListBean>() {
            @Override
            public void onResponse(Call<visitorListBean> call, Response<visitorListBean> response) {

                Log.d("asdasdSize" , String.valueOf(response.body().getVisitorList().size()));

                for (int i = 0; i < response.body().getVisitorList().size(); i++)
                {
                    if (Objects.equals(response.body().getVisitorList().get(i).getVisitorType(), "Regular")) {
                        list.add(response.body().getVisitorList().get(i));
                    }
                }

                adapter.setGridData(list);


                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<visitorListBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });
    }

    public  class RegularAdapter extends RecyclerView.Adapter<RegularAdapter.MyViewHolder> {

        List<VisitorList> list = new ArrayList<>();
        Context context;


        public RegularAdapter(Context context, List<VisitorList> list) {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<VisitorList> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.regular_list_model, parent, false);
            return new RegularAdapter.MyViewHolder(view);
        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            final VisitorList item = list.get(position);

            holder.time.setText(item.getIntime());
            holder.visitor.setText(item.getStaffName());

            holder.house.setVisibility(View.GONE);

            if (Objects.equals(item.getStatus(), "0")) {
                holder.out.setBackgroundResource(R.drawable.green_circle);
            } else if (Objects.equals(item.getStatus(), "1")) {
                holder.out.setBackgroundResource(R.drawable.red_circle);
            }

            holder.comment.setText(item.getComment());

            holder.out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    progress.setVisibility(View.VISIBLE);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://safedoors.in")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    bean b = (bean) context.getApplicationContext();

                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                    Call<String> visitorOut = cr.out(b.socity, item.getVisitorId());

                    visitorOut.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            loadData();

                            progress.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            progress.setVisibility(View.GONE);
                        }
                    });

                }
            });



        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView time, visitor, house , comment;
            ImageButton out;

            public MyViewHolder(View itemView) {
                super(itemView);


                time = (TextView) itemView.findViewById(R.id.time);
                visitor = (TextView) itemView.findViewById(R.id.visitor);
                house = (TextView) itemView.findViewById(R.id.house);
                comment = (TextView) itemView.findViewById(R.id.comment);
                out = (ImageButton) itemView.findViewById(R.id.out);
            }
        }
    }




}



