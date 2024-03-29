package com.example.orderfoodversion3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class signin extends AppCompatActivity {
EditText email,password;
Button signin ;
ProgressBar progress ;
FirebaseAuth mAuth;
TextView dontAcount;

  /*  @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(),home.class);
            startActivity(i);
            finish();
        }
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();
        password = findViewById(R.id.password);

        email = findViewById(R.id.email);
        signin = findViewById(R.id.btnSignIn);
        progress = findViewById(R.id.progress);
        dontAcount=findViewById(R.id.dontAcount);
        signin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                progress.setVisibility(View.VISIBLE);
                String pass = password.getText().toString();
                String mail = email.getText().toString();
                if(TextUtils.isEmpty(mail)){
                    Toast.makeText(signin.this, "Enter your  email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(signin.this, "Enter your  password", Toast.LENGTH_SHORT).show();
                }

                mAuth.signInWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progress.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(signin.this, "Authentication succes.",Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), home.class);
                                    startActivity(i);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(signin.this, "Authentication failed.",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
        dontAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(signin.this,SignUpActivity.class);
                startActivity(i);
            }
        });
    }
   /* public Boolean validateEmail(){
String mail = email.getText().toString();
        if(mail.isEmpty()){
            email.setError();
        }
    }
        public void checkUser(){
            progress.setVisibility(View.VISIBLE);
            String pass = password.getText().toString();
            String mail = email.getText().toString();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

            Query checkUserData = reference.orderByChild("username").equalTo(mail);

            checkUserData.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        email.setError(null);
                        String passwordFromDb = snapshot.child(mail).child("password").getValue(String.class);

                        if (!Objects.equals(passwordFromDb,pass)){
                            email.setError(null);
                            Intent i = new Intent(signin.this,MainActivity.class);
                            startActivity(i);}
                        else {
                            password.setError("Invalid Credentials");
                            password.requestFocus();

                        }
                    }else {
                        email.setError("users does not existe ");
                        email.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });*/

        }

