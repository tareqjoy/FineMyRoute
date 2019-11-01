package com.example.finemyroute;

import androidx.annotation.Nullable;

public class Edge {
    private Marker marker1;
    private Marker marker2;
    private double distance;
    private int row;

    public Edge(Marker marker1, Marker marker2, double distance, int row) {
        this.marker1 = marker1;
        this.marker2 = marker2;
        this.distance = distance;
        this.row = row;
    }

    public Marker getMarker1() {
        return marker1;
    }

    public void setMarker1(Marker marker1) {
        this.marker1 = marker1;
    }

    public Marker getMarker2() {
        return marker2;
    }

    public void setMarker2(Marker marker2) {
        this.marker2 = marker2;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Edge temp = (Edge) obj;
        return ((marker1.equals(temp.getMarker1()) && marker2.equals(temp.getMarker2())) || (marker1.equals(temp.getMarker2()) && marker2.equals(temp.getMarker1())));
    }
}
