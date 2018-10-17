package com.decathlon.calc.Calculation;

import com.decathlon.calc.Domain.DecathlonEventResult;
import com.decathlon.calc.Domain.DecathlonEventType;
import com.decathlon.calc.IncorrectDecathlonEventTypeException;
import com.decathlon.calc.Properties.DecathlonCoefficients;

public class DecathlonEventResultPointsCalculator {

    /**
     Formulas:
     Points = INT(A(B — P)C) for track events
     Points = INT(A(P — B)C) for field events

     A, B and C are parameters that vary by discipline
     while P is the performance by the athlete,
     measured in seconds (running), metres (throwing), or centimetres (jumping).\
     Source: https://en.wikipedia.org/wiki/Decathlon#Points_system
     **/

    private DecathlonCoefficients providedCoefficients;

    public DecathlonEventResultPointsCalculator() {}
    public DecathlonEventResultPointsCalculator(
            DecathlonCoefficients coefficients
    ) {
        if(coefficients != null) providedCoefficients = coefficients;
    }

    public Integer calculateEventScore(DecathlonEventResult eventResult)
                                        throws IncorrectDecathlonEventTypeException {
        if(eventResult.getType().equals("track"))
            return calculateTrackEventScore(eventResult);
        else if(eventResult.getType().equals("field"))
            return calculateFieldEventScore(eventResult);
        throw new IncorrectDecathlonEventTypeException(eventResult.getType());
    }

    public Integer calculateTrackEventScore(DecathlonEventResult eventResult) {
        Double coefValueA;
        Double coefValueB;
        Double coefValueC;

        String eventName = eventResult.getName();

        if(providedCoefficients == null) {
            coefValueA =
                    DecathlonCoefficients
                            .getDefaultCoefficientValue(eventName + "_A");
            coefValueB =
                    DecathlonCoefficients
                            .getDefaultCoefficientValue(eventName + "_B");
            coefValueC = DecathlonCoefficients
                    .getDefaultCoefficientValue(eventName + "_C");
        }
        else {
            coefValueA =
                    providedCoefficients
                            .getCoefficientValue(eventName + "_A");
            coefValueB =
                    providedCoefficients
                            .getCoefficientValue(eventName + "_B");
            coefValueC = providedCoefficients
                    .getCoefficientValue(eventName + "_C");
        }


        if(coefValueA == null || coefValueB == null || coefValueC == null) {
            return null;
        }

        //Decathlon event result should be positive.
        // Negative values can vastly affect calculation results
        if(eventResult.getResult() < 0) {
            return 0;
        }


        Double resultFloat = coefValueA * Math.pow(
                coefValueB - eventResult.getResult(),
                coefValueC
        );

        return resultFloat > 0.0 ? resultFloat.intValue() : 0;
    }

    public Integer calculateFieldEventScore(
            DecathlonEventResult eventResult
    ) {
        Double coefValueA;
        Double coefValueB;
        Double coefValueC;

        String eventName = eventResult.getName();

        if(providedCoefficients == null) {
            coefValueA =
                    DecathlonCoefficients
                            .getDefaultCoefficientValue(eventName + "_A");
            coefValueB =
                    DecathlonCoefficients
                            .getDefaultCoefficientValue(eventName + "_B");
            coefValueC = DecathlonCoefficients
                    .getDefaultCoefficientValue(eventName + "_C");
        }
        else {
            coefValueA =
                    providedCoefficients
                            .getCoefficientValue(eventName + "_A");
            coefValueB =
                    providedCoefficients
                            .getCoefficientValue(eventName + "_B");
            coefValueC = providedCoefficients
                    .getCoefficientValue(eventName + "_C");
        }


        if(coefValueA == null || coefValueB == null || coefValueC == null) {
            return null;
        }


        Double eventResultValue;
        //Special case for jumps: jump distance has to be converter to centimeters
        if(eventName.contains("JUMP") ||
                eventName.equals(DecathlonEventType.POLE_VAULT.name()))

            eventResultValue = eventResult.getResult() * 100;
        else eventResultValue = eventResult.getResult();

        //Decathlon event result should be positive.
        // Negative values can vastly affect calculation results
        if(eventResultValue < 0) {
            return 0;
        }

        Double resultFloat = coefValueA * Math.pow(
                (eventResultValue - coefValueB),
                coefValueC
        );

        return resultFloat > 0.0 ? resultFloat.intValue() : 0;
    }


}
