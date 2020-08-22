package com.technobrix.tbx.safedoors.MeetingArragemenmt;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.technobrix.tbx.safedoors.R;



public class MeetingClock extends Fragment {

    TimePicker picker;
    TextView submit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =  inflater.inflate(R.layout.meeting_clock , container, false);

        picker = (TimePicker)view.findViewById(R.id.timepicker);
        submit = (TextView) view.findViewById(R.id.submit);
        return view;

    }
}
