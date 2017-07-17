package net.serenitybdd.taxi.glue;

import com.google.common.collect.ImmutableMap;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.taxi.apis.TFLPlaces;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.jayway.restassured.http.ContentType.JSON;
import static com.jayway.restassured.http.ContentType.XML;
import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by NoteP on 13.07.2017.
 */
public class ListPlaceTypesSteps {

    ContentType contentType;

    @When("^(?:.*) retrieves? all the available place types in (.*)$")
    public void retrieveTheAvailablePlaceTypesInFormat(ContentType contentType) throws Throwable {
        this.contentType = contentType;
        given().accept(contentType)
                .when().get(TFLPlaces.placeTypes());
    }

    @Then("^(?:.*) should see at least the following place types:$")
    public void shouldSeeAtLeastTheFollowingPlaceTypes(List<String> expectedPlaceTypes) throws Throwable {
        String placeData = then().extract().asString();
        List<String> actualPlaceTypes = FETCH_PLACE_TYPES.get(contentType).apply(placeData);
        assertThat(actualPlaceTypes).containsAll(expectedPlaceTypes);
    }

    private final Map<ContentType, Function<String, List<String>>> FETCH_PLACE_TYPES;

    {
        FETCH_PLACE_TYPES = ImmutableMap.of(
                XML, (placeData) -> XmlPath.from(placeData).getList("ArrayOfString.string"),
                JSON, (placeData) -> JsonPath.from(placeData).get(".")
        );
    }

}
