package net.serenitybdd.taxi.model.locations;

/**
 * Created by NoteP on 20.07.2017.
 */
public class BikeStation {
    private final String id;
    private final String commonName;
    private final String latitude;
    private final String longitude;

    public BikeStation(String id, String commonName, String latitude, String longitude) {
        this.id = id;
        this.commonName = commonName;
        this.latitude = latitude;
        this.longitude = longitude;
    }


}
