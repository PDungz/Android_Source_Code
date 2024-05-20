package com.example.tacpham.model;

public class TacPham {
    private  String maTP;
    private  String tenTP;
    private  String nhaXB;
    private  Integer soXB;
    private  Integer soLuong;
    private  Double donGia;

    public String getMaTP() {
        return maTP;
    }

    public void setMaTP(String maTP) {
        this.maTP = maTP;
    }

    public String getTenTP() {
        return tenTP;
    }

    public void setTenTP(String tenTP) {
        this.tenTP = tenTP;
    }

    public String getNhaXB() {
        return nhaXB;
    }

    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }

    public Integer getSoXB() {
        return soXB;
    }

    public void setSoXB(Integer soXB) {
        this.soXB = soXB;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public TacPham(String maTP, String tenTP, String nhaXB, Integer soXB, Integer soLuong, Double donGia) {
        this.maTP = maTP;
        this.tenTP = tenTP;
        this.nhaXB = nhaXB;
        this.soXB = soXB;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public TacPham() {
    }
}
