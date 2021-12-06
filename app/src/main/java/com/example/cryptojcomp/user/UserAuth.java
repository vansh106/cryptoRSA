package com.example.cryptojcomp.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cryptojcomp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserAuth extends AppCompatActivity {
    FirebaseAuth auth;
    EditText uid,upass;
    Button ulogin;
    ProgressBar pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_auth);
        auth=FirebaseAuth.getInstance();
        uid = findViewById(R.id.useremail);
        upass = findViewById(R.id.userpass);
        ulogin = findViewById(R.id.u_login);
        pg=findViewById(R.id.progressBar2);
        pg.setVisibility(View.GONE);
        ulogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg.setVisibility(View.VISIBLE);
                String email=uid.getText().toString();
                String password=upass.getText().toString();
                if(password.isEmpty()){
                    upass.setText("");
                    upass.requestFocus();
                    upass.setError("Required");
                    return;
                }if(email.isEmpty()){
                    uid.setText("");
                    uid.requestFocus();
                    uid.setError("Required");
                    return;
                }
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            pg.setVisibility(View.GONE);
                            Intent i = new Intent(UserAuth.this, paymentpanel.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(UserAuth.this, "Wrong credentials!", Toast.LENGTH_SHORT).show();
                            pg.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}