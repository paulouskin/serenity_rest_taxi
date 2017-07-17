package net.serenitybdd.taxi.glue.transformers;

import cucumber.api.Transformer;
import net.serenitybdd.taxi.model.locations.TubeStation;
import net.serenitybdd.taxi.model.locations.UnknownTubeStationException;

import java.util.Arrays;

/**
 * Created by NoteP on 11.07.2017.
 */
public class TubeStationConverter extends Transformer<TubeStation> {
    @Override
    public TubeStation transform(String tubeStationName) {
        return Arrays.stream(TubeStation.values())
                .filter(station -> station.name.equalsIgnoreCase(tubeStationName))
                .findFirst()
                .orElseThrow(() -> new UnknownTubeStationException(tubeStationName));
    }
}
