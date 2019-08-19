package com.example.model;

public class NhanHieu {
    int idBrand;
    String nameBrand;
    int imageBrand;

    public NhanHieu() {
    }

    public NhanHieu(String nameBrand, int imageBrand) {
        this.nameBrand = nameBrand;
        this.imageBrand = imageBrand;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    public int getImageBrand() {
        return imageBrand;
    }

    public void setImageBrand(int imageBrand) {
        this.imageBrand = imageBrand;
    }
}
