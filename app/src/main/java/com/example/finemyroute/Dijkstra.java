package com.example.finemyroute;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra extends AsyncTask<Void,Void, String[]> {

    private int dist[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V; // vertices
    List<List<Node>> adj;
    Marker src;
    String[] string;


    Dijkstra(List<List<Node> > adj, Marker src,Marker V){
        this.adj = adj;
        this.src = src;
        this.V = V;
        dist = new int[V];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<Node>(V, new Node());
        string = new String[100];
    }


    // Function to process all the neighbours
    // of the passed node
    private void e_Neighbours(int u)
    {
        int edgeDistance = -1;
        int newDistance = -1;

        // All the neighbors of v
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            // If current node hasn't already been processed
            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[v.node])
                    dist[v.node] = newDistance;

                // Add the current node to the queue
                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }

    @Override
    protected String[] doInBackground(Void... voids) {

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        pq.add(new Node(src, 0));

        // Distance to the source is 0
        dist[src] = 0;
        while (settled.size() != V) {

            // remove the minimum distance node
            // from the priority queue
            int u = pq.remove().node;

            // adding the node whose distance is
            // finalized
            settled.add(u);

            e_Neighbours(u);
        }

        for (int i = 0; i < dist.length; i++){
            string[i] = src + " to " + i + " is " + dist[i];
        }
            return string;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        //super.onPostExecute(strings);
        for (int i = 0; i < dist.length; i++){
            Log.d("Path", "onPostExecute: "+strings[i]);
        }

    }
}
