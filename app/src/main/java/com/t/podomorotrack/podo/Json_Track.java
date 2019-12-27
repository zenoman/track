package com.t.podomorotrack.podo;

public class Json_Track {
    private String tgl,lat,lot,place,report,foto,konsumen,idk;

    public Json_Track(String tgl, String lat, String lot, String place, String report, String foto, String konsumen, String idk) {
        this.tgl = tgl;
        this.lat = lat;
        this.lot = lot;
        this.place = place;
        this.report = report;
        this.foto = foto;
        this.konsumen = konsumen;
        this.idk = idk;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getKonsumen() {
        return konsumen;
    }

    public void setKonsumen(String konsumen) {
        this.konsumen = konsumen;
    }

    public String getIdk() {
        return idk;
    }

    public void setIdk(String idk) {
        this.idk = idk;
    }
}
