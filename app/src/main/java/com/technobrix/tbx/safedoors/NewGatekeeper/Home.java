package com.technobrix.tbx.safedoors.NewGatekeeper;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.technobrix.tbx.safedoors.R;

public class Home extends AppCompatActivity {

    Button regular , visitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        regular = (Button) findViewById(R.id.regular);
        visitor = (Button) findViewById(R.id.visitor);

        visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Home.this , NewVisitor.class);
                startActivity(i);
            }
        });


        regular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Home.this , RegularVisitor.class);
                startActivity(i);
            }
        });
    }
}
