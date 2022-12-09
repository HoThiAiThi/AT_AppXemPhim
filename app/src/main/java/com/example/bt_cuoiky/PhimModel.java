package com.example.bt_cuoiky;

public class PhimModel {
    String tenPhim, namChieu, anh, tomTat;

    PhimModel(){

    }

    public PhimModel(String tenPhim, String namChieu, String anh, String tomTat) {
        this.tenPhim = tenPhim;
        this.namChieu = namChieu;
        this.anh = anh;
        this.tomTat = tomTat;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getNamChieu() {
        return namChieu;
    }

    public void setNamChieu(String namChieu) {
        this.namChieu = namChieu;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTomTat() {
        return tomTat;
    }

    public void setTomTat(String tomTat) {
        this.tomTat = tomTat;
    }
}
