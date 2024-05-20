package com.example.tacpham.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TacPhamDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "TacPhamDB";
    private static final int DB_VERSION = 1;

    public TacPhamDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng dữ liệu cho tác phẩm
        String createTableSQL = "CREATE TABLE tableTacPham (\n" +
                "    maTP TEXT PRIMARY KEY,\n" +
                "    tenTP TEXT,\n" +
                "    nhaXB TEXT,\n" +
                "    soXB INTEGER,\n" +
                "    soLuong INTEGER,\n" +
                "    donGia REAL\n" +
                ")";
        db.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tableTacPham");
        onCreate(db);
    }
}
