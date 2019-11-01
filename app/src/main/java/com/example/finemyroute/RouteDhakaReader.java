package com.example.finemyroute;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.opencsv.CSVReader;

public class RouteDhakaReader {

    private Context context;
    private HashMap<Marker,ArrayList<Edge>> roads= new HashMap<>();
    private HashMap<Marker,Double> distance= new HashMap<>();

    public RouteDhakaReader(Context context) {
        this.context = context;
    }

    public void readData(double destLat, double destLon){
        try {
            File fileToGet = context.getExternalFilesDir("RoadmapDhaka.csv");
            CSVReader reader = new CSVReader(new FileReader(fileToGet.getAbsoluteFile()));
            String[] nextLine;
            int i = 0;
            double velocity = 1;
            while ((nextLine = reader.readNext()) != null) {
                i++;
                Marker marker1,marker2;
                double lat1,lon1,lat2,lon2,time,sDist;
                lon1 = Double.parseDouble(nextLine[1]);
                lat1 = Double.parseDouble(nextLine[2]);
                lon2 = Double.parseDouble(nextLine[nextLine.length-4]);
                lat2 = Double.parseDouble(nextLine[nextLine.length-3]);
                time = Double.parseDouble(nextLine[nextLine.length-1]);


                if(i ==1){
                    velocity = distance(lat1,lon1,lat2,lon2)/time;
                    Log.d("printing", "printDistance: "+velocity);
                }


                marker1 = new Marker(lat1,lon1);
                marker2 = new Marker(lat2,lon2);

                sDist = distance(lat1,lon1,destLat,destLon);
                distance.put(marker1,sDist/velocity);
                Log.d("printing", "printDistance: "+velocity+" "+sDist+ " "+ sDist/velocity+" "+i);
                sDist = distance(lat2,lon2,destLat,destLon);
                distance.put(marker2,sDist/velocity);

                if(roads.get(marker1) != null && roads.get(marker2)!= null){
                    roads.get(marker1).add(new Edge(marker2,time,i));
                    roads.get(marker2).add(new Edge(marker1,time,i));
                }
                else if(roads.get(marker1) == null && roads.get(marker2)!= null){
                    ArrayList<Edge> edge = new ArrayList<>();
                    edge.add(new Edge(marker2,Double.parseDouble(nextLine[nextLine.length-1]),i));
                    roads.put(marker1,edge);
                    roads.get(marker2).add(new Edge(marker1,Double.parseDouble(nextLine[nextLine.length-1]),i));
                }
                else if(roads.get(marker1) != null && roads.get(marker2)== null){
                    ArrayList<Edge> edge = new ArrayList<>();
                    edge.add(new Edge(marker1,Double.parseDouble(nextLine[nextLine.length-1]),i));
                    roads.put(marker2,edge);
                    roads.get(marker1).add(new Edge(marker2,Double.parseDouble(nextLine[nextLine.length-1]),i));
                }
                else if(roads.get(marker1) == null && roads.get(marker2)== null){
                    ArrayList<Edge> edge = new ArrayList<>();
                    edge.add(new Edge(marker2,Double.parseDouble(nextLine[nextLine.length-1]),i));
                    roads.put(marker1,edge);
                    edge = new ArrayList<>();
                    edge.add(new Edge(marker1,Double.parseDouble(nextLine[nextLine.length-1]),i));
                    roads.put(marker2,edge);
                }

                if(i == 20)
                    break;

            }
        } catch (IOException e) {
            Log.d("Csv read exception",e.getMessage());
        }

        printEverything();
    }

    private void printEverything() {

        for(Marker a: roads.keySet()){
            for(Edge e:roads.get(a)){
                Log.d("printing", "printEverything: ("+a.getLatitude()+","+a.getLongitude()+") -> "+"("+e.getMarker().getLatitude()+","+e.getMarker().getLongitude()+") -> "+e.getDistance()+" "+e.getRow());
            }
        }



    }

    public HashMap<Marker, ArrayList<Edge>> getRoads() {
        return roads;
    }

    public void setRoads(HashMap<Marker, ArrayList<Edge>> roads) {
        this.roads = roads;
    }

    public HashMap<Marker, Double> getDistance() {
        return distance;
    }

    public void setDistance(HashMap<Marker, Double> distance) {
        this.distance = distance;
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;
            return (dist);
        }
    }


}
