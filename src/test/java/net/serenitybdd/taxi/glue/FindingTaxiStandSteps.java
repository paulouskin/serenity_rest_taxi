package net.serenitybdd.taxi.glue;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.taxi.apis.TFLPlaces;
import net.serenitybdd.taxi.glue.transformers.TubeStationConverter;
import net.serenitybdd.taxi.model.locations.Place;
import net.serenitybdd.taxi.model.locations.TubeStation;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by NoteP on 13.07.2017.
 */
public class FindingTaxiStandSteps {

    TubeStation currentLocation;

    String jsonResponse;

    @Given("(?:.*) is (?:at|planning a getaway from) (.*)")
    public void userIsCurrentlyIn(@Transform(TubeStationConverter.class) TubeStation tubeStation) {
        this.currentLocation = tubeStation;
    }

    @When("^s?he looks for the closest taxi rank$")
    public void lookForTheClosesTaxiRank() throws Throwable {
        List<Map> matches = with().get(TFLPlaces.queryStopPoint(currentLocation.name)).
                then().extract().jsonPath().getList("matches");
        String stopPointId = matches.get(0).get("id").toString();
        jsonResponse = with().get(TFLPlaces.findTaxiRankByStopPointId(stopPointId)).
                then().extract().asString();
    }

    @When("^s?he looks for the taxi rank with (.*) name")
    public void lookForTheTaxiRankWithGivenName(String taxiName) throws Throwable {
        jsonResponse = given().accept(ContentType.JSON).when().get(TFLPlaces.findPlaceByName(Place.TaxiRank, taxiName))
                .then().extract().asString();
    }


    @Then("^no taxi ranks should be returned$")
    public void noTaxiRacksShouldBeReturned() throws Throwable {
        List<String> taxiRanks = JsonPath.from(jsonResponse).getList(".id");
        assertThat(taxiRanks.size(), equalTo(0));
    }

    @Then("^(\\d+) taxi ranks should be found$")
    public void taxiRanksShouldBeFound(int taxiRanksFound) throws Throwable {
        List<String> taxiRanks = JsonPath.from(jsonResponse).getList(".id");
        assertThat(taxiRanks.size(), equalTo(taxiRanksFound));
    }

    @Then("^s?he should find the taxi ranks with the following details$")
    public void shouldFindTheTaxiRankWithTheFollowingDetails(List<Map<String,String>> taxiRanks) throws Throwable {
        List<Map> places = JsonPath.from(jsonResponse).getList(".", Map.class);
        taxiRanks.stream().forEach(
                (Map<String,String> taxiRank) -> checkThatATaxiRankLike(taxiRank).existsIn(places)
        );
    }

    private TaxiRankPropertyChecker checkThatATaxiRankLike(Map<String, String> taxiRank) {
        return new TaxiRankPropertyChecker(taxiRank);
    }

}
