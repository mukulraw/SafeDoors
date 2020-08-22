package com.technobrix.tbx.safedoors;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BuyTicket extends AppCompatActivity {

    Button ok;

    TextView success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);

        success = (TextView)findViewById(R.id.succes);

        ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });


        String title = getIntent().getStringExtra("title");

        success.setText("Successful Bought Tickets for Event " + title);

    }
}
