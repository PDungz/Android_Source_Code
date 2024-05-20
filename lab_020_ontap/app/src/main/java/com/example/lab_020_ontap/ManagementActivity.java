package com.example.lab_020_ontap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        // Lay id ra
        TextView tenDangNhap = findViewById(R.id.tvTenDangNhap);
        TextView matKhau = findViewById(R.id.tvMatKhau);
        Button dangXuat = findViewById(R.id.btnDangXuat);

        // Lay gia tri duoc gui sang
        Intent intent = getIntent();
        tenDangNhap.setText(intent.getStringExtra("TENDN"));
        matKhau.setText(intent.getStringExtra("MK"));

        //
        dangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagementActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
