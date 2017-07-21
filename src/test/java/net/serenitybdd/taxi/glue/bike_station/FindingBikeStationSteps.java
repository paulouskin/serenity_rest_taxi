package net.serenitybdd.taxi.glue.bike_station;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.it.Ma;
import net.serenitybdd.taxi.apis.TFLPlaces;
import net.serenitybdd.taxi.glue.transformers.TubeStationConverter;
import net.serenitybdd.taxi.model.locations.TubeStation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.serenitybdd.rest.SerenityRest.given;

/**
 * Created by NoteP on 20.07.2017.
 */
public class FindingBikeStationSteps {

    TubeStation currentLocation;

    String jsonResponse;

    @Given("(?:.*) come out from (.*) station")
    public void userIsCurrentlyIn(@Transform(TubeStationConverter.class) TubeStation tubeStation) {
        this.currentLocation = tubeStation;
    }

    @When("^s?he looks for the nearby bike station$")
    public void lookForBikeStationByName() throws Throwable{
        jsonResponse = given().accept(ContentType.JSON)
                .when().get(TFLPlaces.findBikePointByName(currentLocation.name))
                .then().extract().asString();
    }

    @Then("^s?he should find next bike stations$")
    public void shouldFindBikeStationsWithNextDetails(List<Map<String,String>> bikePoints) {
        List<Map> places = JsonPath.from(jsonResponse).get(".");
        List commonNames = places.stream()
                .map(x -> x.get("commonName")).collect(Collectors.toList());
        commonNames.stream().forEach(System.out::println);
    }

    private BikeStationPropertyChecker checkBikeStationPropertiesForPlace(Map<String, String> bikeStation) {
        return new BikeStationPropertyChecker(bikeStation);
    }
}
