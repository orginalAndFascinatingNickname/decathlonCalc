import com.decathlon.calc.Domain.DecathlonEventResult;
import com.decathlon.calc.Domain.DecathlonResultEntry;
import org.junit.Assert;
import org.junit.Test;

public class DecathlonResultEntryTest {

    @Test
    public void  getEventResult_emptyEventResultList_returnsNull() {
        DecathlonResultEntry resultEntry =
                new DecathlonResultEntry("Test Athlete");

        DecathlonEventResult eventResult =
                resultEntry.getEventResult("DoesNotExist");

        Assert.assertTrue(eventResult == null);
    }

    @Test
    public void  getEventResult_singleEntryInEventResultList_eventResultIsFound() {
        DecathlonResultEntry resultEntry =
                new DecathlonResultEntry("Test Athlete");

        String decathlonEventResultName = "Test";
        String decathlonEventResultType = "TestType";
        Double decathlonEventResultValue = 1.5;
        DecathlonEventResult eventResultToAdd =
                new DecathlonEventResult(
                        decathlonEventResultName,
                        decathlonEventResultType,
                        decathlonEventResultValue
                );

        resultEntry.addEventResult(eventResultToAdd);

        DecathlonEventResult eventResult =
                resultEntry.getEventResult(decathlonEventResultName);

        Assert.assertTrue(eventResult != null);
        Assert.assertTrue(
                eventResult.getName().equals(decathlonEventResultName)
        );
        Assert.assertTrue(
                eventResult.getType().equals(decathlonEventResultType)
        );
        Assert.assertTrue(
                eventResult.getResult().equals(decathlonEventResultValue)
        );
    }

    @Test
    public void  getEventResult_multipleEntriesInEventResultList_eventResultIsFound() {
        DecathlonResultEntry resultEntry =
                new DecathlonResultEntry("Test Athlete");

        String decathlonEventResultName = "Test";
        String decathlonEventResultType = "TestType";
        Double decathlonEventResultValue = 1.5;
        DecathlonEventResult eventResultToAdd =
                new DecathlonEventResult(
                        decathlonEventResultName,
                        decathlonEventResultType,
                        decathlonEventResultValue
                );
        DecathlonEventResult secondEventResultToAdd =
                new DecathlonEventResult("TEST2", "TestType", 1.5);

        resultEntry.addEventResult(eventResultToAdd);
        resultEntry.addEventResult(secondEventResultToAdd);

        DecathlonEventResult eventResult =
                resultEntry.getEventResult(decathlonEventResultName);
        Assert.assertTrue(eventResult != null);
        Assert.assertTrue(
                eventResult.getName().equals(decathlonEventResultName)
        );
        Assert.assertTrue(
                eventResult.getType().equals(decathlonEventResultType)
        );
        Assert.assertTrue(
                eventResult.getResult().equals(decathlonEventResultValue)
        );
    }

    @Test
    public void  getEventResult_multipleEntriesInEventResultList_eventResultIsNotFound() {
        DecathlonResultEntry resultEntry =
                new DecathlonResultEntry("Test Athlete");

        String nonExistingEventResultName = "DoesNotExist";
        String decathlonEventResultName = "Test";
        String decathlonEventResultType = "TestType";
        Double decathlonEventResultValue = 1.5;
        DecathlonEventResult eventResultToAdd =
                new DecathlonEventResult(
                        decathlonEventResultName,
                        decathlonEventResultType,
                        decathlonEventResultValue
                );
        DecathlonEventResult secondEventResultToAdd =
                new DecathlonEventResult("TEST2", "TestType", 1.5);

        resultEntry.addEventResult(eventResultToAdd);
        resultEntry.addEventResult(secondEventResultToAdd);

        DecathlonEventResult eventResult =
                resultEntry.getEventResult(nonExistingEventResultName);
        Assert.assertTrue(eventResult == null);
    }

    @Test
    public void  addEventResultObjectParam_eventResultIsNotInResultEntry_success() {
        DecathlonResultEntry resultEntry = new DecathlonResultEntry("Test Athlete");

        String decathlonEventResultName = "Test";
        String decathlonEventResultType = "TestType";
        Double decathlonEventResultValue = 1.5;
        DecathlonEventResult eventResultToAdd =
                new DecathlonEventResult(
                        decathlonEventResultName,
                        decathlonEventResultType,
                        decathlonEventResultValue
                );

        Assert.assertTrue(resultEntry.addEventResult(eventResultToAdd));

        DecathlonEventResult eventResult =
                resultEntry.getEventResult(decathlonEventResultName);

        Assert.assertNotNull(eventResult);
        Assert.assertEquals(eventResult.getName(), decathlonEventResultName);
        Assert.assertEquals(eventResult.getType(), decathlonEventResultType);
        Assert.assertEquals(eventResult.getResult(), decathlonEventResultValue);
    }

    @Test
    public void  addEventResultObjectParam_eventResultIsInResultEntry_failure() {
        DecathlonResultEntry resultEntry =
                new DecathlonResultEntry("Test Athlete");

        String decathlonEventResultName = "Test";
        String decathlonEventResultType = "TestType";
        Double decathlonEventResultValue = 1.5;
        DecathlonEventResult eventResultToAdd =
                new DecathlonEventResult(
                        decathlonEventResultName,
                        decathlonEventResultType,
                        decathlonEventResultValue
                );

        resultEntry.addEventResult(eventResultToAdd);

        Assert.assertFalse(resultEntry.addEventResult(eventResultToAdd));
    }

    @Test
    public void  addEventResultObjectParam_eventResultIsNull_failure() {
        DecathlonResultEntry resultEntry =
                new DecathlonResultEntry("Test Athlete");

        Assert.assertFalse(resultEntry.addEventResult(null));
    }

    @Test
    public void  addEventResultStringParams_eventResultIsNotInResultEntry_success() {
        DecathlonResultEntry resultEntry =
                new DecathlonResultEntry("Test Athlete");

        String decathlonEventResultName = "Test";
        String decathlonEventResultType = "TestType";
        Double decathlonEventResultValue = 1.5;

        Assert.assertTrue(resultEntry.addEventResult(
                decathlonEventResultName,
                decathlonEventResultType,
                decathlonEventResultValue)
        );

        DecathlonEventResult eventResult =
                resultEntry.getEventResult(decathlonEventResultName);

        Assert.assertNotNull(eventResult);
        Assert.assertEquals(eventResult.getName(), decathlonEventResultName);
        Assert.assertEquals(eventResult.getType(), decathlonEventResultType);
        Assert.assertEquals(eventResult.getResult(), decathlonEventResultValue);
    }

    @Test
    public void  addEventResultStringParams_eventResultIsInResultEntry_failure() {
        DecathlonResultEntry resultEntry =
                new DecathlonResultEntry("Test Athlete");

        String decathlonEventResultName = "Test";
        String decathlonEventResultType = "TestType";
        Double decathlonEventResultValue = 1.5;

        resultEntry.addEventResult(
                decathlonEventResultName,
                decathlonEventResultType,
                decathlonEventResultValue
        );
        Assert.assertFalse(resultEntry.addEventResult(
                decathlonEventResultName,
                decathlonEventResultType,
                decathlonEventResultValue)
        );
    }

    @Test
    public void  addEventResultStringParams_nameIsNull_failure() {
        DecathlonResultEntry resultEntry =
                new DecathlonResultEntry("Test Athlete");

        String decathlonEventResultName = null;
        String decathlonEventResultType = "TestType";
        Double decathlonEventResultValue = 1.5;

        Assert.assertFalse(resultEntry.addEventResult(
                decathlonEventResultName,
                decathlonEventResultType,
                decathlonEventResultValue)
        );
    }

    @Test
    public void  addEventResultStringParams_typeIsNull_failure() {
        DecathlonResultEntry resultEntry =
                new DecathlonResultEntry("Test Athlete");

        String decathlonEventResultName = "test";
        String decathlonEventResultType =  null;
        Double decathlonEventResultValue = 1.5;

        Assert.assertFalse(resultEntry.addEventResult(
                decathlonEventResultName,
                decathlonEventResultType,
                decathlonEventResultValue)
        );
    }

    @Test
    public void  addEventResultStringParams_valueIsNull_failure() {
        DecathlonResultEntry resultEntry =
                new DecathlonResultEntry("Test Athlete");

        String decathlonEventResultName = "Test";
        String decathlonEventResultType = "TestType";
        Double decathlonEventResultValue = null;

        Assert.assertFalse(resultEntry.addEventResult(
                decathlonEventResultName,
                decathlonEventResultType,
                decathlonEventResultValue)
        );
    }

    @Test
    public void  addEventResultStringParams_allParamsAreNull_failure() {
        DecathlonResultEntry resultEntry = new DecathlonResultEntry("Test Athlete");

        String decathlonEventResultName = null;
        String decathlonEventResultType = null;
        Double decathlonEventResultValue = null;

        Assert.assertFalse(resultEntry.addEventResult(
                decathlonEventResultName,
                decathlonEventResultType,
                decathlonEventResultValue)
        );
    }
}
