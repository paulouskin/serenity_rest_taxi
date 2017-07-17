package net.serenitybdd.taxi.model.locations;

/**
 * Created by NoteP on 11.07.2017.
 */
public class TaxiStand {
    public final String commonName;
    public final float distance;

    public TaxiStand(String commonName, float distance) {
        this.commonName = commonName;
        this.distance = distance;
    }
}
