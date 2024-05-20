package com.example.lab04_05;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        // lay id
        EditText userName = findViewById(R.id.edtUserName);
        EditText passWord = findViewById(R.id.edtPassWord);
        TextView notification = findViewById(R.id.tvNotification);
        Button btnSignIn = findViewById(R.id.btnSignIn);
        Button btnRegister = findViewById(R.id.btnRegister);

        Intent intent = getIntent();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = intent.getStringExtra("USERNAME");
                String password = intent.getStringExtra("PASSWORD");

                // Kiểm tra các trường không được để trống
                if (!TextUtils.isEmpty(userName.getText()) && !TextUtils.isEmpty(passWord.getText())) {
                    // Kiem tra tai khoan
                    if (username != null && password != null) {
                        // kiem tra username va password dung
                        if(username.equals(userName.getText().toString()) && password.equals(passWord.getText().toString())) {
                            Intent intent = new Intent(SignInActivity.this, CourseActivity.class);
                            startActivity(intent);
                        }
                        else {
                            notification.setText("UserName or PassWord is wrong");
                        }
                    }
                    else {
                        notification.setText("Don’t have an account ? Register Now");

                    }
                }
                else {
                    notification.setText("Please enter username and password");
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
