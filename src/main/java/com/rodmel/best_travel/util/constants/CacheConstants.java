package com.rodmel.best_travel.util.constants;

public class CacheConstants {

    public static final String FLY_CACHE_NAME = "flights";
    public static final String HOTEL_CACHE_NAME = "hotels";

    //clean cache everyday to 12:00 am
    public static final String SCHEDULED_RESET_CACHE = "0 0 0 * * ?";
}
