package com.technobrix.tbx.safedoors.Desscusion;

import android.graphics.Color;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.CommentListPOJO.CommentList;
import com.technobrix.tbx.safedoors.CommentListPOJO.CommentsBean;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.TopicPOJO.TopiBean;
import com.technobrix.tbx.safedoors.bean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HelpOne extends AppCompatActivity {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    HelpOneAdapter adapeter;
    Toolbar toolbar;
    ProgressBar bar;
    List<CommentList> list;
    FloatingActionButton arrow;
    EditText type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_one2);

        bar = (ProgressBar) findViewById(R.id.bar);

        recyclerView = (RecyclerView) findViewById(R.id.helpdesk1);

        type = (EditText) findViewById(R.id.type);

        arrow = (FloatingActionButton) findViewById(R.id.arrow);

        manager = new GridLayoutManager(this,1);

        list = new ArrayList<>();

        adapeter = new HelpOneAdapter(this , list);

        recyclerView.setAdapter(adapeter);

        recyclerView.setLayoutManager(manager);

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

      loadComments();

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String ty = type.getText().toString();

                if (ty.length()>0)
                {
                    bar.setVisibility(View.VISIBLE);

                    bean b = (bean)getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://safedoors.in")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                    Call<TopiBean> call = cr.topi(b.socity , getIntent().getStringExtra("topic") , b.userId , ty);

                    call.enqueue(new Callback<TopiBean>() {
                        @Override
                        public void onResponse(Call<TopiBean> call, Response<TopiBean> response) {



                            loadComments();

                            bar.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<TopiBean> call, Throwable t) {

                            bar.setVisibility(View.GONE);

                        }
                    });

                }



            }
        });







    }

    public void loadComments()
    {
        bar.setVisibility(View.VISIBLE);

        bean b = (bean)getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<CommentsBean> call = cr.comment(b.socity , getIntent().getStringExtra("topic"));

        call.enqueue(new Callback<CommentsBean>() {
            @Override
            public void onResponse(Call<CommentsBean> call, Response<CommentsBean> response) {

                adapeter.set(response.body().getCommentList());

                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<CommentsBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });
    }

}
