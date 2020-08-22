package com.technobrix.tbx.safedoors.Desscusion;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.TopicListPOJO.TopicList;

import java.util.ArrayList;
import java.util.List;


public class HelpDiskAdapeter extends RecyclerView.Adapter<HelpDiskAdapeter.MyViewHolder> {

    Context context;
    List<TopicList> list = new ArrayList<>();

    public HelpDiskAdapeter(Context context , List<TopicList> list){


        this.list = list;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.helpdesk_list_model,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final TopicList item = list.get(position);
        holder.user.setText(item.getUsername());
        holder.subject.setText(item.getSubject());
        holder.body.setText(item.getBody());

        holder.comment.setText(item.getCommentCount());
        holder.view.setText(item.getViewCount());

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(false).build();
        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(item.getProfilePic() , holder.image , options);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,HelpOne.class);
                intent.putExtra("topic" , item.getId());
                context.startActivity(intent);
            }
        });


    }

    public void setgrid(List<TopicList> list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

      TextView user , subject , body , comment , view;
      ImageView image;


        public MyViewHolder(View itemView) {
            super(itemView);

            body = (TextView)itemView.findViewById(R.id.body);
            subject = (TextView)itemView.findViewById(R.id.subject);
            user = (TextView)itemView.findViewById(R.id.user);
            comment = (TextView)itemView.findViewById(R.id.comment);
            view = (TextView)itemView.findViewById(R.id.view);
            image = (ImageView) itemView.findViewById(R.id.image);


        }
    }
}
