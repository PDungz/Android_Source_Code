package com.example.lab03_ontap.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab03_ontap.database.NhanVienDBHelper;
import com.example.lab03_ontap.model.NhanVien;

import java.sql.Timestamp;
import java.util.ArrayList;

public class NhanVienDAO {
    // Bien cau hinh
    private NhanVienDBHelper nhanVienDBHelper;
    private SQLiteDatabase sqLiteDatabase;

    public NhanVienDAO(Context context) {
        nhanVienDBHelper = new NhanVienDBHelper(context);
    }

    // Ham: Doc du lieu tu bang trong csdl
    public ArrayList<NhanVien> getListNhanVien() {
        ArrayList<NhanVien> listNhanVien = new ArrayList<>();
        // Mo ket noi voi csdl o che doc
        sqLiteDatabase = nhanVienDBHelper.getReadableDatabase();
        try {
            // Su dung cau lenh sql tra ve du lieu trong bang
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NHANVIEN", null);
//            [0,1,2,3]
//            0 = [mnv, hvt, nt, gt]
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                //
                do {
                    NhanVien nhanVien = new NhanVien();
                    //
                    nhanVien.setMaNV(cursor.getString(0));
                    nhanVien.setHoVaTen(cursor.getString(1));
                    // Ep kieu ngay thang 2003-01-01 00:00:00
                    Timestamp ngaysinh = nhanVien.formatStringToTimestampDate(cursor.getString(2));
                    nhanVien.setNgaySinh(ngaysinh);
                    nhanVien.setGioiTinh(cursor.getString(3));

                    // Them no vao mang danh sach nhan vine
                    listNhanVien.add(nhanVien);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("TAG", "MESSAGE: " + e.getMessage());
        } finally {
            if(sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                sqLiteDatabase.close();
            }
        }
        return listNhanVien;
    }

    // Ham: Tim kiem theo thong tin nhan vien trong csld
    public ArrayList<NhanVien> searchListNhanVien(String search) {
        ArrayList<NhanVien> listNhanVien = new ArrayList<>();
        // Mo ket noi voi csdl o che doc
        sqLiteDatabase = nhanVienDBHelper.getReadableDatabase();
        try {
            // Su dung cau lenh sql tra ve du lieu trong bang
            String searchTerm = "%" + search + "%";
            String query = "SELECT * FROM NHANVIEN WHERE maNV LIKE ? OR hoVaTen LIKE ? OR gioiTinh LIKE ?";
            Cursor cursor = sqLiteDatabase.rawQuery(query, new String[] {searchTerm, searchTerm, searchTerm});
//            [0,1,2,3]
//            0 = [mnv, hvt, nt, gt]
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                //
                do {
                    NhanVien nhanVien = new NhanVien();
                    //
                    nhanVien.setMaNV(cursor.getString(0));
                    nhanVien.setHoVaTen(cursor.getString(1));
                    // Ep kieu ngay thang 2003-01-01 00:00:00
                    Timestamp ngaysinh = nhanVien.formatStringToTimestampDate(cursor.getString(2));
                    nhanVien.setNgaySinh(ngaysinh);
                    nhanVien.setGioiTinh(cursor.getString(3));

                    // Them no vao mang danh sach nhan vine
                    listNhanVien.add(nhanVien);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("TAG", "MESSAGE: " + e.getMessage());
        } finally {
            if(sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                sqLiteDatabase.close();
            }
        }
        return listNhanVien;
    }

    // Ham: Them du lieu
    public long themNhanVien(NhanVien nhanVien) {
        long checkInsert = -1;
        // Mo ket noi voi csdl o che doc
        sqLiteDatabase = nhanVienDBHelper.getReadableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("maNV", nhanVien.getMaNV());
            contentValues.put("hoVaTen", nhanVien.getHoVaTen());
            contentValues.put("ngaySinh", nhanVien.getNgaySinh().toString());
            contentValues.put("gioiTinh", nhanVien.getGioiTinh());

            checkInsert = sqLiteDatabase.insert("NHANVIEN", null, contentValues);

        } catch (Exception e) {
            Log.e("TAG", "MESSAGE: " + e.getMessage());
        } finally {
            if(sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                sqLiteDatabase.close();
            }
        }
        return checkInsert;
    }

    // Ham: Sua du lieu
    public long suaNhanVien(NhanVien nhanVien) {
        long checkUpdate = -1;
        // Mo ket noi voi csdl o che doc
        sqLiteDatabase = nhanVienDBHelper.getReadableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("maNV", nhanVien.getMaNV());
            contentValues.put("hoVaTen", nhanVien.getHoVaTen());
            contentValues.put("ngaySinh", nhanVien.getNgaySinh().toString());
            contentValues.put("gioiTinh", nhanVien.getGioiTinh());

            checkUpdate = sqLiteDatabase.update("NHANVIEN", contentValues, "maNV=?", new String[]{String.valueOf(nhanVien.getMaNV())});
        } catch (Exception e) {
            Log.e("TAG", "MESSAGE: " + e.getMessage());
        } finally {
            if(sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                sqLiteDatabase.close();
            }
        }
        return checkUpdate;
    }

    // Ham: Xoa du lieu
    public long xoaNhanVien(NhanVien nhanVien) {
        long checkDelete = -1;
        // Mo ket noi voi csdl o che doc
        sqLiteDatabase = nhanVienDBHelper.getReadableDatabase();
        try {
            checkDelete = sqLiteDatabase.delete("NHANVIEN", "maNV=?", new String[]{String.valueOf(nhanVien.getMaNV())});

        } catch (Exception e) {
            Log.e("TAG", "MESSAGE: " + e.getMessage());
        } finally {
            if(sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                sqLiteDatabase.close();
            }
        }
        return checkDelete;
    }

    // Ham: Kiem tra id
    public boolean kiemTraMaNV(String maNV) {
        boolean checkMaNV = false;
        // Mo ket noi voi csdl o che doc
        sqLiteDatabase = nhanVienDBHelper.getReadableDatabase();
        try {
            // Su dung cau lenh sql tra ve du lieu trong bang
            String query = "SELECT * FROM NHANVIEN WHERE maNV = ?";
            Cursor cursor = sqLiteDatabase.rawQuery(query, new String[] {maNV});
            checkMaNV = cursor.getCount() > 0;
        } catch (Exception e) {
            Log.e("TAG", "MESSAGE: " + e.getMessage());
        } finally {
            if(sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                sqLiteDatabase.close();
            }
        }
        return checkMaNV;
    }
}
