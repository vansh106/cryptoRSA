package com.example.cryptojcomp.Banker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cryptojcomp.MainActivity;
import com.example.cryptojcomp.R;
import com.example.cryptojcomp.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class banker_main extends AppCompatActivity {
    EditText uname,uid,upass,uaddress,ubalance,uccn;
    Button reg,display;
    FirebaseAuth auth;
    ProgressBar p;
    TextView mm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banker_main);
        uname = findViewById(R.id.uname);
        uid = findViewById(R.id.uid);
        upass = findViewById(R.id.upass);
        uaddress = findViewById(R.id.uaddress);
        ubalance = findViewById(R.id.ubalance);
        uccn = findViewById(R.id.uccn);
        reg= findViewById(R.id.reg);
        p=findViewById(R.id.progressBar);
        p.setVisibility(View.GONE);
        mm=findViewById(R.id.textView8);
        display=findViewById(R.id.displaybtn);
        auth=FirebaseAuth.getInstance();

        mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(banker_main.this, MainActivity.class);
                startActivity(intent);
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(banker_main.this, userList.class);
                startActivity(intent);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = uid.getText().toString();
                String username = uname.getText().toString();
                String password = upass.getText().toString();
                String address = uaddress.getText().toString();
                String ccn = uccn.getText().toString();
                String balance = ubalance.getText().toString();
                if(id.isEmpty()){
                    uid.setText("");
                    uid.requestFocus();
                    uid.setError("Required");
                    return;
                }if(username.isEmpty()){
                    uname.setText("");
                    uname.requestFocus();
                    uname.setError("Required");
                    return;
                }if(password.isEmpty()){
                    upass.setText("");
                    upass.requestFocus();
                    upass.setError("Required");
                    return;
                }if(address.isEmpty()){
                    uaddress.setText("");
                    uaddress.requestFocus();
                    uaddress.setError("Required");
                    return;
                }if(ccn.isEmpty()){
                    uccn.setText("");
                    uccn.requestFocus();
                    uccn.setError("Required");
                    return;
                }if(balance.isEmpty()){
                    ubalance.setText("");
                    ubalance.requestFocus();
                    ubalance.setError("Required");
                    return;
                }
                p.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(id,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String encrypted = RSA(ccn);
                            User u = new User(username,id,address,balance,encrypted);

                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(banker_main.this, "User Registered Successfully!\nEncrypted ccn:"+ encrypted, Toast.LENGTH_SHORT).show();
                                    uid.setText("");
                                    uname.setText("");
                                    upass.setText("");
                                    uccn.setText("");
                                    uaddress.setText("");
                                    ubalance.setText("");
                                    Log.v("TAG","     hogaya");
                                    p.setVisibility(View.GONE);
                                }
                            });

                        }else{
                            Log.v("TAG","  nahi   hogaya");
                            Toast.makeText(banker_main.this, "Error!", Toast.LENGTH_SHORT).show();
                            p.setVisibility(View.GONE);
                        }
                    }

                });
            }
        });
    }

    private String RSA(String ccn) {
        List<Integer> array = primeNumbers(1000);
        int p = new Random().nextInt(array.size());
        p= array.get(p);
        int q = new Random().nextInt(array.size());
        q=array.get(q);
        Toast.makeText(banker_main.this, "Random Prime numbers are "+ p+" & "+q , Toast.LENGTH_SHORT).show();


        int n = p*q;
        int toitent = (p-1)*(q-1);
        int i,z,e,k=1;
        ArrayList<Integer> arr = new ArrayList(n);
        for ( i = 1; i < toitent; i++)
        {
            z = gcd(i + 1, toitent);
            if (z == 1){
                arr.add(i+1);
            }
        }
        e= arr.get(arr.size()/2);
        int msg= Integer.parseInt(ccn);
        for(i=0;i<e;i++){
            k=k*msg;
            k=k%n;
        }
        String enc = String.valueOf(k);
        return enc;
    }

    private int gcd(int a,int b) {
        return b==0 ? a:gcd(b,a%b);
    }

    public List<Integer> primeNumbers(int n) {
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
    public boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}