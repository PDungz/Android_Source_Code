package com.example.lab03_ontap.model;

import java.sql.Timestamp;

public class NhanVien {
    private String maNV;
    private String hoVaTen;
    private Timestamp ngaySinh;
    private String GioiTinh;

    public NhanVien() {

    }

    public NhanVien(String maNV, String hoVaTen, Timestamp ngaySinh, String gioiTinh) {
        this.maNV = maNV;
        this.hoVaTen = hoVaTen;
        this.ngaySinh = ngaySinh;
        GioiTinh = gioiTinh;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public Timestamp getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Timestamp ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public Timestamp formatStringToTimestampDate(String releaseDateString) {
        Timestamp releaseDateT = null;
        if (releaseDateString != null && !releaseDateString.isEmpty()) {
            releaseDateT = Timestamp.valueOf(releaseDateString);
        }
        return  releaseDateT;
    }

    public Timestamp formatStringToTimestampDateTime(String releaseDateString) {
        Timestamp releaseDateT = null;
        if (releaseDateString != null && !releaseDateString.isEmpty()) {
            releaseDateString += " 00:00:00";
            releaseDateT = Timestamp.valueOf(releaseDateString);
        }
        return  releaseDateT;
    }

}
