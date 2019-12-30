package com.example.basketballapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText emailID, password, confpassword;
    Button btnSignUp;
    Button btnSignIn;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        confpassword = findViewById(R.id.editText5);
        btnSignUp = findViewById(R.id.button);
        btnSignIn = findViewById(R.id.button2);

        //Setting up Click Listener for Sign Up Button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailID.getText().toString();
                String pswd = password.getText().toString();
                if(email.isEmpty()){
                    emailID.setError("Please enter email ID");
                    emailID.requestFocus();
                }
                else if(pswd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if(pswd.isEmpty() && email.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields are Empty!", Toast.LENGTH_SHORT).show();
                }
                else if(!(pswd.isEmpty() && email.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pswd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Sign Up Unsuccessful", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"An Error has Occurred", Toast.LENGTH_SHORT).show();
                }


            }
        });


        //Setting up Click Listener for Sign In Button
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });

    }
}
