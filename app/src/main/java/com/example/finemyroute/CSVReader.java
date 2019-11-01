package com.example.finemyroute;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CSVReader {
    private HashMap<Double, ArrayList<Marker>> list;


    CSVReader(File file) {
        //    File fileDirectory = Environment.getRootDirectory();
        // File file = context.getExternalFilesDir("file.csv");
        //  fileToGet.mkdir();

        readCSVFile(file);

        //  Log.d("checker1",Environment.DIRECTORY_DOWNLOADS);

    }

    CSVReader() {

    }

    public void readCSVFile(File file) {

        try {
            list = new HashMap<>();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                if(list.containsKey(tokens[0])){
                    list.get(tokens[0]).add(new Marker(tokens[0], tokens[1], tokens[2]));
                }else {
                    list.put(tokens[0],new ArrayList<Marker>());
                    list.get(tokens[0]).add(new Marker(tokens[0], tokens[1], tokens[2]));

                }

              //  list.add(new Marker(tokens[0], tokens[1], tokens[2]));

                //  Log.d("checker2",tokens[0]);
                /*
                s0 = tokens[0].toString();
                s1 = tokens[1].toString();
                s2 = tokens[2].toString();
                s3 = tokens[3].toString();
                s4 = tokens[4].toString();
                s5 = tokens[5].toString();*/
            }
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            Log.d("checkerErr1", e.getMessage());
        } catch (IOException e) {
            Log.d("checkerErr2", e.getMessage());
            //   e.printStackTrace();
        }


    }

    public HashMap<String, ArrayList<Marker>> getList() {
        return list;
    }

}
