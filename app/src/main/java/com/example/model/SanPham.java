package com.example.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SanPham implements Serializable {
    @SerializedName("MaSP")
    int MaSP;

    @SerializedName("TenSP")
    String TenSP;

    @SerializedName("DonGia")
    int DonGia;

    @SerializedName("MaDanhMuc")
    int MaDanhMuc;

    @SerializedName("TinhTrang")
    boolean TinhTrang;

    @SerializedName("MoTa")
    String MoTa;

    @SerializedName("HinhSP")
    String  HinhSP;

    @SerializedName("MaNhanHieu")
    int MaNhanHieu;

    @SerializedName("SoLuongTon")
    int SoLuongTon;

    int SlSP;
    boolean chonSP;
    boolean likeSP;

    public SanPham() {
    }

    public SanPham(int maSP, String tenSP, int donGia, int slSP) {
        MaSP = maSP;
        TenSP = tenSP;
        DonGia = donGia;
        SlSP = slSP;
    }

    public SanPham(int maSP, String tenSP, int donGia, String hinhSP, int slSP) {
        MaSP = maSP;
        TenSP = tenSP;
        DonGia = donGia;
        HinhSP = hinhSP;
        SlSP = slSP;
    }

    public SanPham(int maSP, String tenSP, int donGia, int maDanhMuc, boolean tinhTrang, String moTa, String hinhSP, int maNhanHieu, int soLuongTon, int slSP, boolean chonSP, boolean likeSP) {
        MaSP = maSP;
        TenSP = tenSP;
        DonGia = donGia;
        MaDanhMuc = maDanhMuc;
        TinhTrang = tinhTrang;
        MoTa = moTa;
        HinhSP = hinhSP;
        MaNhanHieu = maNhanHieu;
        SoLuongTon = soLuongTon;
        SlSP = slSP;
        this.chonSP = chonSP;
        this.likeSP = likeSP;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int donGia) {
        DonGia = donGia;
    }

    public int getMaDanhMuc() {
        return MaDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        MaDanhMuc = maDanhMuc;
    }

    public boolean isTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getHinhSP() {
        return HinhSP;
    }

    public void setHinhSP(String hinhSP) {
        HinhSP = hinhSP;
    }

    public int getMaNhanHieu() {
        return MaNhanHieu;
    }

    public void setMaNhanHieu(int maNhanHieu) {
        MaNhanHieu = maNhanHieu;
    }

    public int getSoLuongTon() {
        return SoLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        SoLuongTon = soLuongTon;
    }

    public int getSlSP() {
        return SlSP;
    }

    public void setSlSP(int slSP) {
        SlSP = slSP;
    }

    public boolean isChonSP() {
        return chonSP;
    }

    public void setChonSP(boolean chonSP) {
        this.chonSP = chonSP;
    }

    public boolean isLikeSP() {
        return likeSP;
    }

    public void setLikeSP(boolean likeSP) {
        this.likeSP = likeSP;
    }
}
