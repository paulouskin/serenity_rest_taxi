package net.serenitybdd.taxi.model.locations;

/**
 * Created by NoteP on 11.07.2017.
 */
public enum TubeStation {

    LONDON_BRIDGE("London Bridge Station", 51.505353, -0.084826),
    BARBICAN("Barbican", 51.520865, -0.097758),
    CANARY_WHARF("Canary Wharf", 51.50362, -0.01987),
    PADDINGTON("Paddington", 51.5151846554, -0.17553880792),
    TOWER_HILL("Tower Hill Underground Station", 51.509971, -0.076546);

    public final String name;
    public final Double latitude;
    public final Double longitude;

    TubeStation(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
