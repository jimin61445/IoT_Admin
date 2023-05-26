package com.example.firebase;

public class WifiInfo {
    String Ssid;
    String Bssid;
    String Rssi;

    public WifiInfo(String Ssid, String Bssid, String Rssi){
        this.Ssid = Ssid;
        this.Bssid = Bssid;
        this.Rssi = Rssi;
    }

    public String getSsid(){return Ssid;}
    public void setSsid(String Ssid){this.Ssid = Ssid;}
    public String getBssid(){
        return Bssid;
    }
    public void setBssid(String Bssid){
        this.Bssid = Bssid;
    }

    public String getRssi(){
        return Rssi;
    }
    public void setRssi(String Rssi){
        this.Rssi = Rssi;
    }
}
