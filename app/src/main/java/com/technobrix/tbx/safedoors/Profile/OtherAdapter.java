package com.technobrix.tbx.safedoors.Profile;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.GetVehiclePOJO.VehicleList;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.RemovePOJO.RemoveBean;
import com.technobrix.tbx.safedoors.bean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.myviewholder> {

    Context context;
    List<VehicleList> list = new ArrayList<>();

    public OtherAdapter(Context context , List<VehicleList> list){

        this.list = list;
        this.context = context;
    }
    @Override
    public OtherAdapter.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.other_list_model , parent , false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(OtherAdapter.myviewholder holder, int position) {

        final VehicleList item = list.get(position);

        holder.name.setText(item.getVehicleName());
        holder.vehicle.setText(item.getVehicleNo());
        holder.novehicle.setText(item.getNoOfVehicle());

        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 bean b = (bean)context.getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://safedoors.in")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                Call<RemoveBean> call = cr.remove(item.getVehicleId() , b.userId);

                call.enqueue(new Callback<RemoveBean>() {
                    @Override
                    public void onResponse(Call<RemoveBean> call, Response<RemoveBean> response) {

                        Toast.makeText(context ,String.valueOf(response.body().getStatus()), Toast.LENGTH_SHORT).show();



                    }

                    @Override
                    public void onFailure(Call<RemoveBean> call, Throwable t) {




                    }
                });



            }
        });


    }

    public void setgrid(List<VehicleList> list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class myviewholder extends RecyclerView.ViewHolder {

        TextView name , novehicle , vehicle ;
        ImageButton close;

        public myviewholder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.car);
            novehicle = (TextView)itemView.findViewById(R.id.one);
            vehicle = (TextView)itemView.findViewById(R.id.vehicle);
            close = (ImageButton) itemView.findViewById(R.id.close);
        }
    }
}
