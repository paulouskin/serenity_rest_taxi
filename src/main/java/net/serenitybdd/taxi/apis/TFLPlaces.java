package net.serenitybdd.taxi.apis;

import net.serenitybdd.taxi.model.locations.Place;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by NoteP on 10.07.2017.
 */
public class TFLPlaces {

    private static final String BASE_URL = "https://api.tfl.gov.uk";

    private static final String APP_ID = APICredentials.fromLocalEnvironment().getAppId();
    private static final String APP_KEY = APICredentials.fromLocalEnvironment().getAppKey();
    private static final String API_PARAMS = "app_id=%s&app_key=%s";

    private static final String FIND_PLACE_TYPES = BASE_URL + "/Place/Meta/PlaceTypes?" + API_PARAMS;
    private static final String FIND_PLACE_BY_NAME = BASE_URL + "/Place/Search?name=%s&types=%s&"+ API_PARAMS;
    private static final String QUERY_TUBE_STOP_POINT = BASE_URL + "/StopPoint/Search/%s?modes=tube&" + API_PARAMS;
    private static final String FIND_TAXI_RANK_BY_STOPPOINT_ID = BASE_URL + "/StopPoint/%s/TaxiRanks?" + API_PARAMS;

    private static final String FIND_BIKE_POINT_BY_NAME = BASE_URL + "/BikePoint/Search?query=%s&" + API_PARAMS;
    private static final String FIND_BIKE_POINT_BY_ID = BASE_URL + "/BikePoint/%s?" + API_PARAMS;

    public static URL placeTypes() throws MalformedURLException {
        return new URL(String.format(FIND_PLACE_TYPES, APP_ID, APP_KEY));
    }

    public static URL findPlaceByName(Place placeType, String name) throws MalformedURLException{
        return new URL(String.format(FIND_PLACE_BY_NAME, name, placeType, APP_ID, APP_KEY));
    }

    public static URL queryStopPoint(String query) throws MalformedURLException {
        return new URL(String.format(QUERY_TUBE_STOP_POINT, query, APP_ID, APP_KEY));
    }

    public static URL findTaxiRankByStopPointId(String stopPointId) throws MalformedURLException {
        return new URL(String.format(FIND_TAXI_RANK_BY_STOPPOINT_ID, stopPointId, APP_ID, APP_KEY));
    }

    public static URL findBikePointByName(String bikePointName) throws MalformedURLException {
        return new URL(String.format(FIND_BIKE_POINT_BY_NAME, bikePointName, APP_ID, APP_KEY));
    }
}
