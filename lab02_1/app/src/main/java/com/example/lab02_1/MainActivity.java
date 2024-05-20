package com.example.lab02_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lay id
        EditText a = findViewById(R.id.edtA);
        EditText b = findViewById(R.id.edtB);
        EditText c = findViewById(R.id.edtC);
        Button btn = findViewById(R.id.btn);
        TextView kq = findViewById(R.id.tvKq);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(a.getText().toString()) && !TextUtils.isEmpty(b.getText().toString()) && !TextUtils.isEmpty(c.getText().toString())) {
                    try {
                        // Lay du lieu tu edit ra
                        double a1 = Double.parseDouble(a.getText().toString());
                        double b1 = Double.parseDouble(b.getText().toString());
                        double c1 = Double.parseDouble(c.getText().toString());

                        // Tính delta
                        double delta = b1 * b1 - 4 * a1 * c1;

                        if (delta > 0) {
                            // Hai nghiệm phân biệt
                            double x1 = (-b1 + Math.sqrt(delta)) / (2 * a1);
                            double x2 = (-b1 - Math.sqrt(delta)) / (2 * a1);
                            kq.setText("Phuong trinh co 2n: x1=" + x1 + " , x2=" + x2);
                        } else if (delta == 0) {
                            // Nghiệm kép
                            double x = -b1 / (2 * a1);
                            kq.setText("Nghiệm kép x = " + x);
                        } else {
                            // Không có nghiệm thực
                            kq.setText("Phuong trinh vo nghiem");
                        }
                    } catch (NumberFormatException e) {
                        // Hiển thị thông báo nếu không nhập giá trị số hợp lệ
                        kq.setText("Vui lòng nhập giá trị số hợp lệ");
                    }
                } else {
                    // Không được để trống
                    kq.setText("Không được để trống");
                }
            }
        });

    }
}