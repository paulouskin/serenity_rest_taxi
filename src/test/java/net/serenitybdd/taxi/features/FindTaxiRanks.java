package net.serenitybdd.taxi.features;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by NoteP on 11.07.2017.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/find_taxi_ranks",
    glue="net.serenitybdd.taxi")
public class FindTaxiRanks {
}
