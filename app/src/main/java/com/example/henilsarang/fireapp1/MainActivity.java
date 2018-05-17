package com.example.henilsarang.fireapp1;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  {

    private Button btnReg;
    private EditText txtemail,txtpass;
    private TextView textViewSignin;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFirebaseAuth = FirebaseAuth.getInstance();
        btnReg = findViewById(R.id.btnreg);
        txtemail = findViewById(R.id.emailText);
        txtpass = findViewById(R.id.passText);
        textViewSignin = findViewById(R.id.textViewSignin);
        mProgressDialog = new ProgressDialog(this);





        btnReg.setOnClickListener((View.OnClickListener) this);
        textViewSignin.setOnClickListener((View.OnClickListener) this);



    }

    private void registerUser(){
        String email = txtemail.getText().toString().trim();
        String pass = txtpass.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(getApplicationContext(),"Please Enter Email",Toast.LENGTH_SHORT).show();

        }


        if(TextUtils.isEmpty(pass)){
            //email is empty
            Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_SHORT).show();

        }

        mProgressDialog.setMessage("Registering User....");
        mProgressDialog.show();


        mFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        Toast.makeText(getApplicationContext(), "Registration Successfully", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();

                    }
            }
        });

    }


    public void onClick(View view){

        if(view == btnReg){

            registerUser();
        }

        if(view == textViewSignin){

        }
    }


}
