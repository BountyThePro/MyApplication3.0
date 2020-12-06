package com.example.myapplication30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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

public class register extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private TextView banner,signtextview,youhaveaccount;
    private EditText email, password1, password2;
    private ProgressBar progressBar;
    private Button btnregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        banner=findViewById(R.id.banner);
        signtextview=findViewById(R.id.signtextview);
        youhaveaccount=findViewById(R.id.youhaveaccount);
        email=findViewById(R.id.email);
        password1=findViewById(R.id.password1);
        password2=findViewById(R.id.password2);
        progressBar=findViewById(R.id.progressBar);
        btnregister=findViewById(R.id.btnregister);
        btnregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
        case R.id.signtextview:
        startActivity(new Intent(this, MainActivity.class));
        break;

        case R.id.btnregister:

            Register();
        break;

    }
}
    public void Register(){
        String email00=email.getText().toString().trim();
        String password00=password1.getText().toString().trim();
        String password01=password2.getText().toString().trim();
        if(TextUtils.isEmpty(email00))
        {
            email.setError("Enter Email");
            email.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password00))
        {
            password1.setError("Enter Password");
            password1.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password01))
        {
            password2.setError("Enter Confirm Password");
            password2.requestFocus();
            return;
        }
        if(!password00.equals(password01))
        {
            password1.setError("Password not match");
            return;
        }

        if(password00.length()<8)
        {
            password1.setError("Password length must be eight");
            return;
        }
        if (!isValidEmail(email00))
        {
            email.setError("Email not Correct");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email00,password00).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(register.this,"Successfully Registerd", Toast.LENGTH_SHORT).show();
                   progressBar.setVisibility(View.VISIBLE);
                    Intent intent=new Intent(register.this, MainActivity.class);
                     startActivity(intent);
                    finish();

                }
                else {
                    Toast.makeText(register.this,"Failed Registeration", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }

            }
        });
    }

    private Boolean isValidEmail(CharSequence target){
        return(!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
