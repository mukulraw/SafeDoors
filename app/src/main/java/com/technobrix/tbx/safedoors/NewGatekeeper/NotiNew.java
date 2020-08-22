package com.technobrix.tbx.safedoors.NewGatekeeper;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;
import com.technobrix.tbx.safedoors.visitorListPOJO.VisitorList;
import com.technobrix.tbx.safedoors.visitorListPOJO.visitorListBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by tvs on 10/26/2017.
 */

public class NotiNew extends Fragment {

    EditText filter;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    RegularAdapter adapter;
    List<VisitorList> list;
    ProgressBar progress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.notinew , container , false);

        filter = (EditText) view.findViewById(R.id.filter);

        progress = (ProgressBar) view.findViewById(R.id.progress);

        list = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        manager = new GridLayoutManager(getContext(), 1);
        adapter = new RegularAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);


        loadData();
        return view;
    }

    public void loadData() {

        list.clear();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        progress.setVisibility(View.VISIBLE);

        bean b = (bean) getContext().getApplicationContext();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);


        Call<visitorListBean> call = cr.getVisitors(b.socity);

        call.enqueue(new Callback<visitorListBean>() {
            @Override
            public void onResponse(Call<visitorListBean> call, Response<visitorListBean> response) {

                Log.d("asdasdSize" , String.valueOf(response.body().getVisitorList().size()));

                for (int i = 0; i < response.body().getVisitorList().size(); i++)
                {
                    if (Objects.equals(response.body().getVisitorList().get(i).getVisitorType(), "New")) {
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


    public class RegularAdapter extends RecyclerView.Adapter<RegularAdapter.MyViewHolder> {

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
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            final VisitorList item = list.get(position);

            holder.time.setText(item.getIntime());
            holder.visitor.setText("Visitor Name : " + item.getVisitorName());

            //holder.house.setVisibility(View.GONE);

            holder.house.setText("House No. : " + item.getHouseNo());


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

                    bean b = (bean) getContext().getApplicationContext();

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
