package com.example.cryptojcomp.Banker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cryptojcomp.R;

public class bankerAuth extends AppCompatActivity {

    EditText b_id,b_pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banker_auth);
        login=findViewById(R.id.u_login);
        b_id=findViewById(R.id.b_id);
        b_pass=findViewById(R.id.b_pass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean a= true;
                String id = b_id.getText().toString();
                String pass= b_pass.getText().toString();
                if(!id.equals("0000")){
                    b_id.setText("");
                    b_id.requestFocus();
                    b_id.setError("Wrong Id");
                    a=false;
                }
                if(!pass.equals("12345")){
                    b_pass.setText("");
                    b_pass.requestFocus();
                    b_pass.setError("Wrong password");
                    a=false;
                }
                if(a){
                    Intent i = new Intent(bankerAuth.this,banker_main.class);
                    startActivity(i);
                }
            }
        });

    }
}