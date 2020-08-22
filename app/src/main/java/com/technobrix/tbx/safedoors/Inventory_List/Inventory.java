package com.technobrix.tbx.safedoors.Inventory_List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.InventoryPhonePOJO.InventoryPhoneBean;
import com.technobrix.tbx.safedoors.InventryListPOJO.InventoryBean;
import com.technobrix.tbx.safedoors.InventryListPOJO.InventryList;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;



public class Inventory extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    InventoryAdapter adapter;
    ProgressBar bar;
    List<InventryList>list;
    FloatingActionButton phone;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inventory_items , container , false);

        recyclerView = (RecyclerView)view.findViewById(R.id.inventory);

        phone = (FloatingActionButton) view.findViewById(R.id.phone);

        bar = (ProgressBar) view.findViewById(R.id.progress);

        list = new ArrayList<>();

        adapter = new InventoryAdapter(getContext() , list);

        manager = new GridLayoutManager(getContext(),1);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);




        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               /* Intent intent = new Intent(Intent.ACTION_DIAL);

                intent.setData(Uri.parse("tel:" + ));

                startActivity(intent);*/

                bar.setVisibility(View.VISIBLE);

                bean b = (bean)getContext().getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://safedoors.in")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);
                Call<InventoryPhoneBean> call = cr.inventoryphone(b.socity_id);
                call.enqueue(new Callback<InventoryPhoneBean>() {
                    @Override
                    public void onResponse(Call<InventoryPhoneBean> call, Response<InventoryPhoneBean> response) {

                        Intent intent = new Intent(Intent.ACTION_DIAL);

                        intent.setData(Uri.parse("tel:"));

                        startActivity(intent);

                   bar.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<InventoryPhoneBean> call, Throwable t) {


                        bar.setVisibility(View.GONE);

                    }
                });






            }
        });

        Log.d("kdsg" , "hii");

       bean b = (bean)getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);
        Call<InventoryBean> call = cr.inventory("1");

        Log.d("mnishaaaa" , b.socity_id);

        call.enqueue(new Callback<InventoryBean>() {
            @Override
            public void onResponse(Call<InventoryBean> call, Response<InventoryBean> response) {

                adapter.setgrid(response.body().getInventryList());

                bar.setVisibility(View.GONE);

                Log.d("mukuy" ,"response");

                Log.d("asdasdasd" , String.valueOf(response.body().getInventryList().size()));


            }

            @Override
            public void onFailure(Call<InventoryBean> call, Throwable t) {

                bar.setVisibility(View.GONE);
                Log.d("nishu" , t.toString());
            }
        });
        return view;

    }


    public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder> {

        Context context;
        List<InventryList>list = new ArrayList<>();

        public InventoryAdapter(Context context , List<InventryList>list){

            this.list = list;
            this.context = context;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.inventory_list_model , parent , false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            InventryList item = list.get(position);

            holder.name.setText(item.getName());
            holder.one.setText(String.valueOf(position + 1));
            holder.price.setText("Rs." + item.getPricePer());
            holder.qty.setText(item.getQty());


        }

        public void setgrid(List<InventryList> list){

            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView name , one , price , qty;

            public MyViewHolder(View itemView) {
                super(itemView);

                name = (TextView)itemView.findViewById(R.id.name);
                one = (TextView)itemView.findViewById(R.id.one);
                price = (TextView)itemView.findViewById(R.id.price);
                qty = (TextView)itemView.findViewById(R.id.quantity);
            }
        }
    }

}
