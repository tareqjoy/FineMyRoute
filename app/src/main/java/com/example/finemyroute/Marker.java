package com.example.finemyroute;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Marker {
    private double Longitude;
    private double Latitude;

    Marker(double Longitude, double Latitude) {
        this.Longitude = Longitude;
        this.Latitude = Latitude;
    }

    Marker(String Longitude, String Latitude) {
        this.Longitude = Double.valueOf(Longitude);
        this.Latitude = Double.valueOf(Latitude);
    }


    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Marker temp = (Marker) obj;
        return this.Longitude == temp.getLongitude() && this.Latitude == temp.getLatitude();
    }

    @NonNull
    @Override
    public String toString() {
        return "Long: " + String.valueOf(Longitude) + " Lat: " + String.valueOf(Latitude);
    }
}
