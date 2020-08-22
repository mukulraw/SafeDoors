package com.technobrix.tbx.safedoors.SplashLogin;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.R;

public class GetStarted extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        tv = (TextView) findViewById(R.id.sign);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GetStarted .this , Login .class );
                startActivity(i);
                finish();
            }
        });
    }
}
