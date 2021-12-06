package com.example.cryptojcomp.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cryptojcomp.MainActivity;
import com.example.cryptojcomp.R;
import com.example.cryptojcomp.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class paymentpanel extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference ref,ref2;
    TextView name,add,balance,ccn,t;
    EditText recid,amount;
    Button pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentpanel);
        auth=FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("Users");
        ref2 = FirebaseDatabase.getInstance().getReference("Users");
        name=findViewById(R.id.uname);
        add=findViewById(R.id.useradd);
        ccn=findViewById(R.id.userccn);
        balance=findViewById(R.id.userbal);
        recid = findViewById(R.id.recid);
        pay = findViewById(R.id.pay);
        amount = findViewById(R.id.amount);
        t=findViewById(R.id.textView17);

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(paymentpanel.this, MainActivity.class);
                startActivity(i);
            }
        });

        ref.child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User u = snapshot.getValue(User.class);
                name.setText(u.getUsername());
                add.setText(u.getAddress());
                ccn.setText(u.getCcn());
                balance.setText(u.getBalance());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = recid.getText().toString();
                String pesa = amount.getText().toString();
                if(id.isEmpty()){
                    recid.setText("");
                    recid.requestFocus();
                    recid.setError("Required");
                    return;
                }if(pesa.isEmpty()){
                    amount.setText("");
                    amount.requestFocus();
                    amount.setError("Required");
                    return;
                }
                int m = Integer.parseInt(pesa);

                ref2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User u = snapshot.getValue(User.class);
                        int apna  = Integer.parseInt(u.getBalance());
                        func(id,m,apna);
                        balance.setText(String.valueOf(apna-Integer.parseInt(pesa)));
                        Toast.makeText(paymentpanel.this, "Transaction successful!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }


        });



    }


    private void func(String id, int pesa, int apna) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    User u = ds.getValue(User.class);

                    String eid = u.getId();
                    String bal = u.getBalance();
                    if(id == eid){
                        int m = Integer.parseInt(bal);
                        u.setBalance(String.valueOf(m+pesa));

                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}