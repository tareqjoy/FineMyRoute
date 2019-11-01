package com.example.finemyroute;

import androidx.annotation.Nullable;

public class Edge implements Comparable<Edge> {
    private Marker marker;
    private double distance=0;
    private int row;

    public Edge(Marker marker, double distance, int row) {
        this.marker = marker;
       // this.marker2 = marker2;
        this.distance = distance;
        this.row = row;
    }

    public Marker getMarker() {
        return marker;
    }

    public Edge(){

    }



    public void setMarker(Marker marker) {
        this.marker = marker;
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
        return ((marker.equals(temp.getMarker()) && this.distance==((Edge) obj).getDistance()));
    }

    @Override
    public int compareTo(Edge edge) {
      //  return Double.compare(distance - edge.)
        return Double.compare(this.distance , edge.getDistance());
    }
}
