package com.railway.model;

public class Route {

    private String source;
    private String destination;
    private int distance;

    public Route() {
    }

    public Route(String source, String destination, int distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return source + " -> " + destination +
               " (" + distance + " km)";
    }
}