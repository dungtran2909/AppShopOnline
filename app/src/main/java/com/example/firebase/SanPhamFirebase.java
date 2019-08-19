package com.example.firebase;

public class SanPhamFirebase {
    private int MaSP;
    private String TenSP;
    private String HinhSP;
    private int GiaSP;
    private int SlSP;

    public SanPhamFirebase() {
    }

    public SanPhamFirebase(int maSP, String tenSP, String hinhSP, int giaSP, int slSP) {
        MaSP = maSP;
        TenSP = tenSP;
        HinhSP = hinhSP;
        GiaSP = giaSP;
        SlSP = slSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public String getHinhSP() {
        return HinhSP;
    }

    public void setHinhSP(String hinhSP) {
        HinhSP = hinhSP;
    }

    public int getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(int giaSP) {
        GiaSP = giaSP;
    }

    public int getSlSP() {
        return SlSP;
    }

    public void setSlSP(int slSP) {
        SlSP = slSP;
    }
}
