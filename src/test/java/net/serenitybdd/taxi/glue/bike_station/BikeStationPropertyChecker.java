package net.serenitybdd.taxi.glue.bike_station;

import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by NoteP on 20.07.2017.
 */
public class BikeStationPropertyChecker {

    private final Map<String, String> expectedBikeStation;

    private static final List<String> ADDITIONAL_PROPERTIES = ImmutableList.of(
            "Locked",
            "Temporary",
            "NbDocks"
    );

    public BikeStationPropertyChecker(Map<String, String> expectedBikeStation) {
        this.expectedBikeStation = expectedBikeStation;
    }

    public void existsIn(List<Map> places) {
        for (Map place : places) {
            if (expectedBikeStationMatches(place)) {
                return;
            }
        }

        throw new AssertionError("No bike station matching " + expectedBikeStation + " found in " + places);
    }

    private boolean expectedBikeStationMatches(final Map place) {
        if(!place.get("commonName").equals(expectedBikeStation.get("commonName"))) {
            return false;
        }

        List<HashMap> allActualProperties = (List<HashMap>)place.get("additionalProperties");

        for (HashMap actualProperty : allActualProperties) {
            for (String property : ADDITIONAL_PROPERTIES ) {
                if (additionalPropertiesDoesNotMatch(property, actualProperty)) return false;
            }
        }
        return true;
    }

    private boolean additionalPropertiesDoesNotMatch(String property, HashMap actualProperty) {
        if (isNotBlank(expectedBikeStation.get(property)) && (actualProperty.get("key").equals(property))) {
            if (!actualProperty.get("value").equals(expectedBikeStation.get(property))) {
                return true;
            }
        }
        return false;
    }
}
