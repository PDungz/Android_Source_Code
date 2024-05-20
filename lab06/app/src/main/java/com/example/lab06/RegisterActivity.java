package com.example.lab06;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class            RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText fullname = findViewById(R.id.edtFullName);
        EditText gender = findViewById(R.id.edtGender);
        EditText userName = findViewById(R.id.edtUserName);
        EditText passWord = findViewById(R.id.edtPassWord);
        EditText confirmPassWord = findViewById(R.id.edtConfirmPassWord);
        Button btnSignIn = findViewById(R.id.btnSignIn);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Kiểm tra các trường không được để trống
                    if (TextUtils.isEmpty(userName.getText())) {
                        showAlertDialog("Error", "Please enter username");
                        return;
                    }

                    if (TextUtils.isEmpty(passWord.getText())) {
                        showAlertDialog("Error", "Please enter password");
                        return;
                    }

                    // Kiểm tra mật khẩu và xác nhận mật khẩu
                    if (!passWord.getText().toString().equals(confirmPassWord.getText().toString())) {
                        showAlertDialog("Error", "Passwords do not match");
                        return;
                    }

                    // Nếu mọi thứ đều ổn, chuyển sang màn hình đăng nhập
                    Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                    // Đặt thông tin người dùng và mật khẩu vào Intent
                    intent.putExtra("USERNAME", userName.getText().toString());
                    intent.putExtra("PASSWORD", passWord.getText().toString());
                    startActivity(intent);
                } catch (Exception e) {
                    // Xử lý ngoại lệ ở đây
                    e.printStackTrace();
                    showAlertDialog("Error", "An error occurred");
                }
            }


            private void showAlertDialog(String title, String message) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Đóng dialog khi người dùng nhấn OK
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}
