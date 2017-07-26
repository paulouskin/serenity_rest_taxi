package net.serenitybdd.taxi.helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by NoteP on 26.07.2017.
 */
public class PlacePropertyChecker {

    private final Map<String, String> expectedPlaceProperties;

    private final List<String> additionalProperties;

    public PlacePropertyChecker(Map<String, String> expectedPlaceProperties, List<String> additionalProperties) {
        this.expectedPlaceProperties = expectedPlaceProperties;
        this.additionalProperties = additionalProperties;
    }

    public void existsIn(List<Map> places) {
        for (Map place : places) {
            if(expectedPropertiesMatch(place)) {
                return;
            }
        }

        throw new AssertionError("No items matching " + expectedPlaceProperties + " found in " + places);
    }

    private boolean expectedPropertiesMatch(Map place) {
        if (!place.get("commonName").equals(expectedPlaceProperties.get("commonName"))) {
            return false;
        }

        List<HashMap> allActualProperties = (List<HashMap>)place.get("additionalProperties");

        for (HashMap actualProperty : allActualProperties) {
            for (String property : additionalProperties) {
                if (additionalPropertyDoesNotMatch(actualProperty, property)) return false;
            }
        }
        return  true;
    }

    private boolean additionalPropertyDoesNotMatch(HashMap actualProperty, String property) {
        if (isNotBlank(expectedPlaceProperties.get(property)) && (actualProperty.get("key").equals(property))) {
            if (!actualProperty.get("value").equals(expectedPlaceProperties.get(property))) {
                return true;
            }
        }
        return false;
    }
}
