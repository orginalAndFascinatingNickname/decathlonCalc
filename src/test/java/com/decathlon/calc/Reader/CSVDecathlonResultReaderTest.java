package com.decathlon.calc.Reader;

import com.decathlon.calc.Domain.DecathlonEventType;
import com.decathlon.calc.Domain.DecathlonResultEntry;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CSVDecathlonResultReaderTest {

    @Test
    public void parseDecathlonResultEntryString_correctFormat_success() {
        String lineToParse =
                "Jaana Lind;13.75;4.84;10.12;1.50;68.44;19.18;" +
                        "30.85;2.80;33.88;6:22.75 ";
        CSVDecathlonResultReader reader =
                new CSVDecathlonResultReader("DUMMYFILE");
        DecathlonResultEntry resultEntry =
                reader.parseDecathlonResultEntryString(lineToParse);

        Assert.assertNotNull(resultEntry);
        Assert.assertEquals(resultEntry.getAthleteName(),"Jaana Lind");
        Assert.assertEquals(resultEntry.getEventResult(
                DecathlonEventType.SPRINT.name()).getResult(),
                Double.valueOf(13.75)
        );
        Assert.assertEquals(resultEntry.getEventResult(
                DecathlonEventType.LONG_JUMP.name()).getResult(),
                Double.valueOf(4.84)
        );
        Assert.assertEquals(resultEntry.getEventResult(
                DecathlonEventType.SHOT_PUT.name()).getResult(),
                Double.valueOf(10.12)
        );
        Assert.assertEquals(resultEntry.getEventResult(
                DecathlonEventType.HIGH_JUMP.name()).getResult(),
                Double.valueOf(1.5)
        );
        Assert.assertEquals(resultEntry.getEventResult(
                DecathlonEventType.FOUR_HUNDRED_METER_RUN.name()).getResult(),
                Double.valueOf(68.44)
        );
        Assert.assertEquals(resultEntry.getEventResult(
                DecathlonEventType.HURDLES.name()).getResult(),
                Double.valueOf(19.18)
        );
        Assert.assertEquals(resultEntry.getEventResult(
                DecathlonEventType.DISCUS_THROW.name()).getResult(),
                Double.valueOf(30.85)
        );
        Assert.assertEquals(resultEntry.getEventResult(
                DecathlonEventType.POLE_VAULT.name()).getResult(),
                Double.valueOf(2.8)
        );
        Assert.assertEquals(resultEntry.getEventResult(
                DecathlonEventType.JAVELIN_THROW.name()).getResult(),
                Double.valueOf(33.88)
        );
        Assert.assertEquals(resultEntry.getEventResult(
                DecathlonEventType.ONE_AND_HALF_KILOMETER_RUN.name()).getResult(),
                Double.valueOf(382.75)
        );


    }

    @Test(expected = Exception.class)
    public void parseDecathlonResultEntryString_incorrectFormat_failure() {
        String lineToParse =
                "Jaana Lind;assda;4.84;10.12;1.50;68.44;19.18;" +
                        "30.85;2.80;33.88;6:22.75 ";
        CSVDecathlonResultReader reader =
                new CSVDecathlonResultReader("DUMMYFILE");

        reader.parseDecathlonResultEntryString(lineToParse);

        Assert.fail("This test should throw an exception since " +
                "the lineToParse is in incorrect format");


    }

    @Test(expected = Exception.class)
    public void parseDecathlonResultEntryString_missingColumns_failure() {
        //Second column is missing
        String lineToParse =
                "Jaana Lind;4.84;10.12;1.50;68.44;19.18;" +
                        "30.85;2.80;33.88;6:22.75 ";
        CSVDecathlonResultReader reader =
                new CSVDecathlonResultReader("DUMMYFILE");

        DecathlonResultEntry resultEntry =
                reader.parseDecathlonResultEntryString(lineToParse);

        Assert.fail("This test should throw an exception since " +
                "the lineToParse is missing some columns");


    }
}