package com.example.cryptojcomp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cryptojcomp.Banker.bankerAuth;
import com.example.cryptojcomp.Banker.banker_main;
import com.example.cryptojcomp.user.UserAuth;

public class MainActivity extends AppCompatActivity {
    Button banker_btn,user_btn;
    TextView details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banker_btn = findViewById(R.id.banker_btn);
        user_btn = findViewById(R.id.user_btn);
        details = findViewById(R.id.textView4);

        banker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, bankerAuth.class);
                startActivity(i);
            }
        });
        user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UserAuth.class);
                startActivity(i);
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,details.class);
                startActivity(i);
            }
        });
    }
}