package com.example.finemyroute;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.android.gms.common.Feature;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

public class CreateKML extends AsyncTask<Void,Void,Void> {
    double[][] cordinate;
    String line,TAG = "KML file creation";
    CreateKML(double[][] cordinate){
        this.cordinate = cordinate;

    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            File root = new File(Environment.getExternalStorageDirectory(), "KmlFiles");
            if (!root.exists()) {
                root.mkdirs();
            }
            Log.d(TAG, "doInBackground: folder created!");
            File gpxfile = new File(root, "file.kml");
            FileWriter writer = new FileWriter(gpxfile);

            FileReader fileReader =
                    new FileReader("file.txt");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                writer.append(line);
            }

            // Always close files.
            bufferedReader.close();

            for (int i = 0 ; i < cordinate[0].length ; i++){

               writer.append(cordinate[i][0]+","+cordinate[i][1]+",0");
            }
            writer.append("</coordinates>\n" +
                    "      </LineString>\n" +
                    "    </Placemark>\n" +
                    "  </Document>\n" +
                    "</kml>");
            Log.d("file creation", "doInBackground: kml file created!");
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
