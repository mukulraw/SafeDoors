package com.technobrix.tbx.safedoors.NoticeBoard;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.R;

public class NoticeBoard2 extends Fragment {

    TextView admin , date , des , no;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.notice_board2 , container , false);

        date = (TextView)view.findViewById(R.id.date);
        //admin = (TextView)view.findViewById(R.id.admin);
        des = (TextView)view.findViewById(R.id.notice);
        no = (TextView)view.findViewById(R.id.no);


        Bundle b = getArguments();

        String d = b.getString("date");
        //String a = b.getString("title");
        String de = b.getString("description");
        String n = b.getString("title");

        date.setText(d);
        //admin.setText(a);
        des.setText(Html.fromHtml(de));
        no.setText("Notice : " + n);







        return  view;
    }
}
