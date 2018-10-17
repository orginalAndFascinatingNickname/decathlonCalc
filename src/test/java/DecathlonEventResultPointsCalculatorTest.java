import com.decathlon.calc.Domain.DecathlonEventResult;
import com.decathlon.calc.DecathlonEventResultPointsCalculator;
import com.decathlon.calc.IncorrectDecathlonEventTypeException;
import org.junit.Assert;
import org.junit.Test;

public class DecathlonEventResultPointsCalculatorTest {

    /**
    Default decathlon coefficients are used in all of these tests
    Points = INT(A(B — P)C) for track events
    Points = INT(A(P — B)C) for field events

    A, B and C are parameters that vary by discipline
    while P is the performance by the athlete,
    measured in seconds (running), metres (throwing), or centimetres (jumping).\
    Source: https://en.wikipedia.org/wiki/Decathlon#Points_system
     **/


    @Test
    public void calculateTrackEventScore_existingType_success() {
        DecathlonEventResult testResult =
                new DecathlonEventResult("SPRINT", "track", 10.0);
        DecathlonEventResultPointsCalculator calc =
                new DecathlonEventResultPointsCalculator();

        Integer calcResult = calc.calculateTrackEventScore(testResult);
        Assert.assertNotNull(calcResult);
        Assert.assertEquals(1096, (int) calcResult);
    }

    @Test
    public void calculateTrackEventScore_existingTypeNegative_zero() {
        DecathlonEventResult testResult =
                new DecathlonEventResult("SPRINT", "track", -10.0);
        DecathlonEventResultPointsCalculator calc =
                new DecathlonEventResultPointsCalculator();

        Integer calcResult = calc.calculateTrackEventScore(testResult);
        Assert.assertNotNull(calcResult);
        Assert.assertEquals(0, (int) calcResult);
    }

    @Test
    public void calculateTrackEventScore_existingTypeCoefBLessThanP_zero() {
        DecathlonEventResult testResult =
                new DecathlonEventResult("SPRINT", "track", 19.0);
        DecathlonEventResultPointsCalculator calc =
                new DecathlonEventResultPointsCalculator();

        Integer calcResult = calc.calculateTrackEventScore(testResult);
        Assert.assertNotNull(calcResult);
        Assert.assertEquals(0, (int) calcResult);
    }

    @Test
    public void calculateFieldEventScore_existingType_success() {
        DecathlonEventResult testResult =
                new DecathlonEventResult("LONG_JUMP", "field", 8.0);
        DecathlonEventResultPointsCalculator calc =
                new DecathlonEventResultPointsCalculator();

        Integer calcResult = calc.calculateFieldEventScore(testResult);
        Assert.assertNotNull(calcResult);
        Assert.assertEquals(1061, (int) calcResult);
    }

    @Test
    public void calculateFieldEventScore_existingTypeNegative_zero() {
        DecathlonEventResult testResult =
                new DecathlonEventResult("LONG_JUMP", "field", -10.0);
        DecathlonEventResultPointsCalculator calc =
                new DecathlonEventResultPointsCalculator();

        Integer calcResult = calc.calculateFieldEventScore(testResult);
        Assert.assertNotNull(calcResult);
        Assert.assertEquals(0, (int) calcResult);
    }

    @Test
    public void calculateFieldEventScore_existingTypeCoefBGreaterThanP_zero() {
        DecathlonEventResult testResult =
                new DecathlonEventResult("LONG_JUMP", "field", 2.1);
        DecathlonEventResultPointsCalculator calc =
                new DecathlonEventResultPointsCalculator();

        Integer calcResult = calc.calculateFieldEventScore(testResult);
        Assert.assertNotNull(calcResult);
        Assert.assertEquals(0, (int) calcResult);
    }

    @Test
    public void calculateEventScore_existingTypeTrack_success() {
        DecathlonEventResult testResult =
                new DecathlonEventResult("SPRINT", "track", 10.0);
        DecathlonEventResultPointsCalculator calc =
                new DecathlonEventResultPointsCalculator();

        Integer calcResult = null;
        try {
            calcResult = calc.calculateEventScore(testResult);
            Assert.assertNotNull(calcResult);
            Assert.assertEquals(1096, (int) calcResult);
        } catch (IncorrectDecathlonEventTypeException e) {
            Assert.fail("com.decathlon.calc.IncorrectDecathlonEventTypeException " +
                    "should not be thrown.");
        }

    }

    @Test
    public void calculateEventScore_existingTypeField_success() {
        DecathlonEventResult testResult = new DecathlonEventResult("LONG_JUMP", "field", 8.0);
        DecathlonEventResultPointsCalculator calc = new DecathlonEventResultPointsCalculator();

        Integer calcResult = null;
        try {
            calcResult = calc.calculateEventScore(testResult);
            Assert.assertNotNull(calcResult);
            Assert.assertEquals(1061, (int) calcResult);
        } catch (IncorrectDecathlonEventTypeException e) {
            Assert.fail("com.decathlon.calc.IncorrectDecathlonEventTypeException " +
                    "should not be thrown.");
        }
    }

    @Test(expected = IncorrectDecathlonEventTypeException.class)
    public void calculateEventScore_existingTypeField_incorrectEventType()
            throws IncorrectDecathlonEventTypeException {

        DecathlonEventResult testResult =
                new DecathlonEventResult("LONG_JUMP", "DOES_NOT_EXIST", 8.0);
        DecathlonEventResultPointsCalculator calc =
                new DecathlonEventResultPointsCalculator();

        calc.calculateEventScore(testResult);
    }


}