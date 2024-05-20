package com.example.lab03_ontap.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NhanVienDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "NHANVIEN_DB";
    private static final int DB_VERSION = 3;

    public NhanVienDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Tao bang trong csld
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Viet cau lenh sql tao bang
        String create_table = "CREATE TABLE NHANVIEN (" +
                "maNV TEXT PRIMARY KEY," +
                "hoVaTen TEXT," +
                "ngaySinh DATETIME," +
                "gioiTinh TEXT)";
        // Thuc thi cau lenh
        sqLiteDatabase.execSQL(create_table);

        // Them du lieu mau vao bang
        String insert_table = "INSERT INTO NHANVIEN(maNV, hoVaTen, ngaySinh, gioiTinh) VALUES ('nv001', 'Nguyen Van A', '2003-01-01 00:00:00', 'Nam')";
        sqLiteDatabase.execSQL(insert_table);
    }

    // Cap nhat lai csdl
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Kiem tra version
        if(oldVersion != newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NHANVIEN");
            onCreate(sqLiteDatabase);
        }
    }
}
