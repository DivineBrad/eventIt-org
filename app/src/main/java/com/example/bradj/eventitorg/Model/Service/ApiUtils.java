package com.example.bradj.eventitorg.Model.Service;

import com.example.bradj.eventitorg.Model.DataAccess.RetrofitClient;

/**
 * Created by Bradley Blanchard on 2017-12-29.
 */

public class ApiUtils {

    public static final String BASE_URL = "http://52.60.167.57:8080/";

    public static EventService getEventService() {
        return RetrofitClient.getClient(BASE_URL).create(EventService.class);
    }
}



