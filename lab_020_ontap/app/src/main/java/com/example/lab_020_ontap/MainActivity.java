package com.example.lab_020_ontap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lay id ra
        EditText tenDangNhap = findViewById(R.id.edtTenDangNhap);
        EditText matKhau = findViewById(R.id.edtMatKhau);
        TextView thongbao = findViewById(R.id.tvThongBao);
        Button dangNhap = findViewById(R.id.btnDangNhap);

        //
        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tendn = tenDangNhap.getText().toString();
                String mk = matKhau.getText().toString();
                if (tendn.equals("admin") && mk.equals("123")) {
                    // Xet gia tri chuyen qua
                    Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
                    intent.putExtra("TENDN", tendn);
                    intent.putExtra("MK", mk);
                    // Chuyen lay out
                    startActivity(intent);
                } else {
                    thongbao.setText("Mat khau hoac ten dang nhap khong dung");
                }
            }
        });
    }
}