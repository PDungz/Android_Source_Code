package com.example.tacpham.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tacpham.model.TacPham;
import com.example.tacpham.database.TacPhamDBHelper;

import java.util.ArrayList;
import java.util.List;

public class TacPhamDAO {
    private TacPhamDBHelper tacPhamDBHelper;
    private SQLiteDatabase sqLiteDatabase;

    public TacPhamDAO(Context context) {
        tacPhamDBHelper = new TacPhamDBHelper(context);
    }

    public void open() {
        sqLiteDatabase = tacPhamDBHelper.getWritableDatabase();
    }

    public void close() {
        tacPhamDBHelper.close();
    }

    public long addTacPham(TacPham tacPham) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTP", tacPham.getMaTP());
        contentValues.put("tenTP", tacPham.getTenTP());
        contentValues.put("nhaXB", tacPham.getNhaXB());
        contentValues.put("soXB", tacPham.getSoXB());
        contentValues.put("soLuong", tacPham.getSoLuong());
        contentValues.put("donGia", tacPham.getDonGia());
        return sqLiteDatabase.insert("tableTacPham", null, contentValues);
    }

    public boolean deleteTacPham(String maTP) {
        int rowsAffected = sqLiteDatabase.delete("tableTacPham", "maTP=?", new String[]{maTP});
        return rowsAffected > 0;
    }

    public boolean updateTacPham(TacPham tacPham) {
        ContentValues values = new ContentValues();
        values.put("tenTP", tacPham.getTenTP());
        values.put("nhaXB", tacPham.getNhaXB());
        values.put("soXB", tacPham.getSoXB());
        values.put("soLuong", tacPham.getSoLuong());
        values.put("donGia", tacPham.getDonGia());

        int rowsAffected = sqLiteDatabase.update("tableTacPham", values, "maTP=?", new String[]{tacPham.getMaTP()});
        return rowsAffected > 0;
    }

    public List<TacPham> getListTacPham() {
        List<TacPham> tacPhamList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM tableTacPham", null);
            if (cursor != null && cursor.moveToFirst()) {
                int maTPIndex = cursor.getColumnIndex("maTP");
                int tenTPIndex = cursor.getColumnIndex("tenTP");
                int nhaXBIndex = cursor.getColumnIndex("nhaXB");
                int soXBIndex = cursor.getColumnIndex("soXB");
                int soLuongIndex = cursor.getColumnIndex("soLuong");
                int donGiaIndex = cursor.getColumnIndex("donGia");
                do {
                    if (maTPIndex != -1 && tenTPIndex != -1 && nhaXBIndex != -1 &&
                            soXBIndex != -1 && soLuongIndex != -1 && donGiaIndex != -1) {
                        String maTP = cursor.getString(maTPIndex);
                        String tenTP = cursor.getString(tenTPIndex);
                        String nhaXB = cursor.getString(nhaXBIndex);
                        int soXB = cursor.getInt(soXBIndex);
                        int soLuong = cursor.getInt(soLuongIndex);
                        double donGia = cursor.getDouble(donGiaIndex);
                        TacPham tacPham = new TacPham(maTP, tenTP, nhaXB, soXB, soLuong, donGia);
                        tacPhamList.add(tacPham);
                    } else {
                        Log.e("TacPhamDAO", "Không tìm thấy chỉ mục cột");
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("TacPhamDAO", "Lỗi khi lấy thông tin tác phẩm từ CSDL", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return tacPhamList;
    }
}
