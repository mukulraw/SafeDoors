package com.technobrix.tbx.safedoors.Desscusion;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.technobrix.tbx.safedoors.CommentListPOJO.CommentList;
import com.technobrix.tbx.safedoors.R;

import java.util.ArrayList;
import java.util.List;


public class HelpOneAdapter extends RecyclerView.Adapter<HelpOneAdapter.MyViewHolder> {

    Context context;

    List<CommentList> list = new ArrayList<>();

    public HelpOneAdapter(Context context ,  List<CommentList> list ){

        this.context = context;

        this.list = list;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.help1_list_model,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        CommentList item = list.get(position);
        holder.timer.setText(item.getCreatedTime());
        holder.date.setText(item.getCreatedDate());
        holder.id.setText(item.getCommentId());
        //holder.text.setText();

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(false).build();
        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(item.getProfilePic() , holder.image , options);


    }

    public void set( List<CommentList> list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text , timer , date , id;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            text = (TextView)itemView.findViewById(R.id.lorem);
            timer = (TextView)itemView.findViewById(R.id.timer);
            date = (TextView)itemView.findViewById(R.id.date);
            id = (TextView)itemView.findViewById(R.id.id);

            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }
}
