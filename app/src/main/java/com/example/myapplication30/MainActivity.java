package com.example.myapplication30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private TextView regiter;
    private EditText email, password1;
    private ProgressBar progressBar;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regiter=findViewById(R.id.register);
        regiter.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.email0);
        progressBar=findViewById(R.id.progressBar);
        password1=findViewById(R.id.password0);
        login=findViewById(R.id.login0);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(this, register.class));
                break;

            case R.id.login0:

                Login();
                break;

        }

        }

    private void Login()
    {
        String email000=email.getText().toString();
        String password000=password1.getText().toString();
        if(TextUtils.isEmpty(email000))
        {
            email.setError("Enter Email");
            return;
        }
        if(TextUtils.isEmpty(password000))
        {
            password1.setError("Enter Password");
            return;
        }

        mAuth.signInWithEmailAndPassword(email000,password000).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Successfully Registerd", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this, session.class);
                    startActivity(intent);
                    finish();

                }
                else {
                    Toast.makeText(MainActivity.this,"Failed Registeration", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}



