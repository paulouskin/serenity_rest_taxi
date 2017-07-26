package net.serenitybdd.taxi.glue.bike_station;

import com.google.common.collect.ImmutableList;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.taxi.apis.TFLPlaces;
import net.serenitybdd.taxi.glue.transformers.TubeStationConverter;
import net.serenitybdd.taxi.helpers.PlacePropertyChecker;
import net.serenitybdd.taxi.model.locations.TubeStation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.serenitybdd.rest.SerenityRest.get;
import static net.serenitybdd.rest.SerenityRest.given;

/**
 * Created by NoteP on 20.07.2017.
 */
public class FindingBikeStationSteps {

    TubeStation currentLocation;

    String jsonResponse;

    List<Map> bikePointsWithAdditionalInfo = new ArrayList<>();

    private List<String> bikeAdditionalProperies = ImmutableList.of(
            "Locked",
            "Temporary",
            "NbDocks"
    );

    @Given("(?:.*) come out from (.*) station")
    public void userIsCurrentlyIn(@Transform(TubeStationConverter.class) TubeStation tubeStation) {
        this.currentLocation = tubeStation;
    }

    @When("^s?he looks for the nearby bike station$")
    public void lookForBikeStationByName() throws Throwable{
        jsonResponse = given().accept(ContentType.JSON)
                .when().get(TFLPlaces.findBikePointByName(currentLocation.name))
                .then().extract().asString();
        List<Map> bikePoints = JsonPath.from(jsonResponse).getList(".");
        List ids = bikePoints.stream()
                .map(x -> x.get("id")).collect(Collectors.toList());
        for (Object id : ids) {
            HashMap bikePoint = get(TFLPlaces.findBikePointById((String)id))
                    .then().extract().jsonPath().get(".");
            bikePointsWithAdditionalInfo.add(bikePoint);
        }
    }

    @Then("^s?he should find next bike stations$")
    public void shouldFindBikeStationsWithNextDetails(List<Map<String,String>> bikePoints) throws Throwable {

        bikePoints.stream().forEach(
                (Map<String, String> bikeStation) ->
                        checkBikeStationPropertiesForPlace(bikeStation, bikeAdditionalProperies).existsIn(bikePointsWithAdditionalInfo)
        );
    }

    private PlacePropertyChecker checkBikeStationPropertiesForPlace(Map<String, String> bikeStation, List<String> additionalProperties) {
        return new PlacePropertyChecker(bikeStation, additionalProperties);
    }
}
