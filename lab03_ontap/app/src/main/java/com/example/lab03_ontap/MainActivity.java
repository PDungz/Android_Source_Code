package com.example.lab03_ontap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lab03_ontap.adapter.NhanVienAdapter;
import com.example.lab03_ontap.dao.NhanVienDAO;
import com.example.lab03_ontap.model.NhanVien;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    // bien cau hinh
    ListView listViewNhanVien;
    ArrayList<NhanVien> nhanVienArrayList = new ArrayList<>();
    NhanVienDAO nhanVienDAO;
    NhanVienAdapter nhanVienAdapter;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lay id ra
        EditText timkiem = findViewById(R.id.edtTimKiem);
        TextView btnTimKiem = findViewById(R.id.btnTimKiem);
        EditText maNV = findViewById(R.id.edtMaNV);
        EditText hoVaTen = findViewById(R.id.edtHoVaTen);
        EditText ngaySinh = findViewById(R.id.edtNgaySinh);
        //EditText gioiTinh = findViewById(R.id.edtGioiTinh);
        RadioGroup gioiTinh = findViewById(R.id.rdgGioiTinh);
        RadioButton rbNam = findViewById(R.id.rbNam);
        RadioButton rbNu = findViewById(R.id.rbNu);
        Button them = findViewById(R.id.btnThem);
        Button sua = findViewById(R.id.btnSua);
        Button xoa = findViewById(R.id.btnXoa);
        listViewNhanVien = findViewById(R.id.lvNhanVien);

        // Lay danh sach nhan vien
        nhanVienDAO = new NhanVienDAO(context);
        nhanVienArrayList = nhanVienDAO.getListNhanVien();

        // Tao adapter va chuyen danh sach nhan vien qua adapter de hien thi len
        nhanVienAdapter = new NhanVienAdapter(context, nhanVienArrayList);
        listViewNhanVien.setAdapter(nhanVienAdapter);

        listViewNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lay danh sach nhan
                NhanVien nhanVien = nhanVienArrayList.get(position);

                //Set cac gia tri cho text view
                maNV.setText(nhanVien.getMaNV());
                hoVaTen.setText(nhanVien.getHoVaTen());
                String ngaythangthoigian = nhanVien.getNgaySinh().toString();
                String ngaythangnam = ngaythangthoigian.substring(0,10);
                ngaySinh.setText(ngaythangnam);
                //gioiTinh.setText(nhanVien.getGioiTinh());

                if(nhanVien.getGioiTinh().equals("Nam") && !nhanVien.getGioiTinh().equals("khac")) {
                    rbNam.setChecked(true);
                } else if (nhanVien.getGioiTinh().equals("Nu") && !nhanVien.getGioiTinh().equals("khac")) {
                    rbNu.setChecked(true);
                }
            }
        });

        // Tim kiem
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = timkiem.getText().toString();
                nhanVienArrayList.clear();
                nhanVienArrayList.addAll(nhanVienDAO.searchListNhanVien(search));
                nhanVienAdapter.notifyDataSetChanged();
            }
        });

        // Them
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tao doi tuong nhan vien
                NhanVien nhanVien = new NhanVien();

                try {
                    // Set nhan vien
                    nhanVien.setMaNV(maNV.getText().toString());
                    nhanVien.setHoVaTen(hoVaTen.getText().toString());
                    Timestamp ns = nhanVien.formatStringToTimestampDateTime(ngaySinh.getText().toString());
                    nhanVien.setNgaySinh(ns);
                    //nhanVien.setGioiTinh(gioiTinh.getText().toString());
                    RadioButton ktrGioiTinh = findViewById(gioiTinh.getCheckedRadioButtonId());
                    String gioitinh = ktrGioiTinh != null ? ktrGioiTinh.getText().toString() : "khac";
                    nhanVien.setGioiTinh(gioitinh);

                    if(validateNhanVien(nhanVien).equals("OK")) {
                        if(!nhanVienDAO.kiemTraMaNV(maNV.getText().toString())) {
                                long result = nhanVienDAO.themNhanVien(nhanVien);

                                if(result > 0) {
                                    showAlert("Thong bao", "Thanh cong");
                                } else {
                                    showAlert("Thong bao", "That bai");
                                }

                        } else {
                            showAlert("Canh bao", "MaNV nay da ton tai" + maNV.getText().toString());
                        }
                    } else {
                        showAlert("Canh bao", validateNhanVien(nhanVien));
                    }
                } catch (Exception e) {
                    showAlert("Canh bao", "Kieu ngay sinh nhap vao khong dung");
                }
                nhanVienArrayList.clear();
                nhanVienArrayList.addAll(nhanVienDAO.getListNhanVien());
                nhanVienAdapter.notifyDataSetChanged();
            }
        });

        // Sua
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tao doi tuong nhan vien
                NhanVien nhanVien = new NhanVien();

                try {
                    // Set nhan vien
                    nhanVien.setMaNV(maNV.getText().toString());
                    nhanVien.setHoVaTen(hoVaTen.getText().toString());
                    Timestamp ns = nhanVien.formatStringToTimestampDateTime(ngaySinh.getText().toString());
                    nhanVien.setNgaySinh(ns);
                    //nhanVien.setGioiTinh(gioiTinh.getText().toString());
                    RadioButton ktrGioiTinh = findViewById(gioiTinh.getCheckedRadioButtonId());
                    String gioitinh = ktrGioiTinh != null ? ktrGioiTinh.getText().toString() : "khac";
                    nhanVien.setGioiTinh(gioitinh);

                    if(validateNhanVien(nhanVien).equals("OK")) {
                        if(nhanVienDAO.kiemTraMaNV(maNV.getText().toString())) {
                                long result = nhanVienDAO.suaNhanVien(nhanVien);

                                if (result > 0) {
                                    showAlert("Thong bao", "Thanh cong");
                                } else {
                                    showAlert("Thong bao", "That bai");
                                }

                        } else {
                            showAlert("Canh bao", "MaNV nay khong ton tai" + maNV.getText().toString());
                        }
                    } else {
                        showAlert("Canh bao", validateNhanVien(nhanVien));
                    }
                } catch (Exception e) {
                    showAlert("Canh bao", "Kieu ngay sinh nhap vao khong dung");
                }
                nhanVienArrayList.clear();
                nhanVienArrayList.addAll(nhanVienDAO.getListNhanVien());
                nhanVienAdapter.notifyDataSetChanged();
            }
        });

        // Xoa
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tao doi tuong nhan vien
                NhanVien nhanVien = new NhanVien();

                // Set nhan vien
                nhanVien.setMaNV(maNV.getText().toString());
                if(nhanVienDAO.kiemTraMaNV(maNV.getText().toString())) {
                    long result = nhanVienDAO.xoaNhanVien(nhanVien);

                    if (result > 0) {
                        showAlert("Thong bao", "Thanh cong");
                    } else {
                        showAlert("Thong bao", "That bai");
                    }
                }
                else {
                    showAlert("Canh bao", "MaNV nay da ton tai" + maNV.getText().toString());
                }
                nhanVienArrayList.clear();
                nhanVienArrayList.addAll(nhanVienDAO.getListNhanVien());
                nhanVienAdapter.notifyDataSetChanged();
            }
        });
    }

    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title).setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    // Kiem tra dieu kien ngoai le
    private String validateNhanVien(NhanVien nhanVien) {
        // Check if maNV is null or empty
        if (nhanVien.getMaNV() == null || nhanVien.getMaNV().isEmpty()) {
            return "Mã nhân viên không được để trống.";
        }

        // Check if hoVaTen is null or empty
        if (nhanVien.getHoVaTen() == null || nhanVien.getHoVaTen().isEmpty()) {
            return "Họ và tên không được để trống.";
        } else {
            // Validate that hoVaTen only contains letters
            if (!nhanVien.getHoVaTen().matches("^[a-zA-Z\\s]+$")) {
                return "Họ và tên chỉ được chứa chữ cái.";
            }
        }

        // Check if ngaySinh is null
        if (nhanVien.getNgaySinh() == null) {
            return "Ngày sinh không được để trống.";
        } else {
            // Validate the date format
            try {
                if (!isValidDateFormat("yyyy-MM-dd", nhanVien.getNgaySinh())) {
                    return "Ngày sinh phải theo định dạng yyyy-MM-dd.";
                }
            } catch (Exception e) {
                return "Ngày sinh phải theo định dạng yyyy-MM-dd.";
            }
        }

        // Check if gioiTinh is null or empty
        if (nhanVien.getGioiTinh() == null || nhanVien.getGioiTinh().isEmpty()) {
            return "Giới tính không được để trống.";
        }

        // Validate gender value
        String gioiTinh = nhanVien.getGioiTinh();
        if (!gioiTinh.equalsIgnoreCase("Nam") && !gioiTinh.equalsIgnoreCase("Nu")) {
            return "Giới tính chỉ chấp nhận Nam hoặc Nu.";
        }

        // If all validations pass
        return "OK";
    }


    private boolean isValidDateFormat(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        String dateStr = sdf.format(date);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


}