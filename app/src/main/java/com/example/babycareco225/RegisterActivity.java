package com.example.babycareco225;

import android.app.ProgressDialog;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    TextView btn;
    private FirebaseAuth mAuth;
    Button btnRegister;
    private EditText inputUsername, inputPassword, inputEmail, inputConfirmPassword;
    private ProgressDialog mLoadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView btn = (TextView) findViewById(R.id.alreadyHaveanAccount);
        btn = (TextView) findViewById(R.id.alreadyHaveanAccount);
        inputUsername = findViewById(R.id.username);
        inputPassword = findViewById(R.id.password);
        inputEmail = findViewById(R.id.email);
        inputConfirmPassword = findViewById(R.id.confirmationPassword);

        mAuth = FirebaseAuth.getInstance();

        btnRegister = findViewById(R.id.btnRegister);
        mLoadingBar = new ProgressDialog(RegisterActivity.this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                checkCredential();
            }

        });


        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), LoginActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });

    }

    private void checkCredential() {
        String username = inputUsername.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmationPassword = inputConfirmPassword.getText().toString();

        if (username.isEmpty()) {
            showError(inputUsername, "Your username is Not valid");
        } else if (email.isEmpty() || !email.contains("@")) {
            showError(inputEmail, "Email is not valid");
        } else if (password.isEmpty() || password.length() < 7) {
            showError(inputPassword, "Password must be greater than 7 charaters");
        } else if (confirmationPassword.isEmpty() || !confirmationPassword.equals(password)) {
            showError(inputConfirmPassword, "Password not matched");
        } else {
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setMessage("Please wait   " + username + "We are Checking your Credential");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Succesfully  Registerd", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//to move next page
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        //prevent from back to poge
                        startActivity(intent);


                    } else {
                        Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

                    }
                }
            });


        }


    }

    private void showError(EditText input, String errorMsg) {
        input.setError(errorMsg);
        input.requestFocus();
    }
}