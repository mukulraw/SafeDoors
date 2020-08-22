package com.technobrix.tbx.safedoors.MeetingArragemenmt;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.R;


public class Meetingcalender extends Fragment {

    TextView submit;
    DatePicker picker;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.meeting_calender , container , false);
        submit = (TextView) view.findViewById(R.id.submit);
        picker = (DatePicker) view.findViewById(R.id.picker);
        return view;
    }
}
