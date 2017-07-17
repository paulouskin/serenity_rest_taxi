package net.serenitybdd.taxi.glue;

import cucumber.api.java.Before;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

/**
 * Created by NoteP on 11.07.2017.
 */
public class SettingTheStage {

    @Before
    public void RecruitTheActor() {
        OnStage.setTheStage(new Cast());
    }
}
