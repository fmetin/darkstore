package com.darkstore.transfer.common.util;

public class DistanceCalculator {
    public static double distanceBetweenCoordinates(double lat1, double long1, double lat2, double long2) {
        double earthRadius = 6371; // Earth's radius in kilometers

        double latDiff = Math.toRadians(lat2 - lat1);
        double longDiff = Math.toRadians(long2 - long1);

        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(longDiff / 2) * Math.sin(longDiff / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = earthRadius * c;

        return Math.round(distance * 1000) / 1000.0; // Round to 3 decimal places for better precision
    }
}
