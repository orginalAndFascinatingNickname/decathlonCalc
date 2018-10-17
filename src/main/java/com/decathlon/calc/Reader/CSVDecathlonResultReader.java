package com.decathlon.calc.Reader;

import com.decathlon.calc.Domain.DecathlonEventResult;
import com.decathlon.calc.Domain.DecathlonEventType;
import com.decathlon.calc.Properties.DecathlonCoefficients;
import com.decathlon.calc.DecathlonEventResultPointsCalculator;
import com.decathlon.calc.Domain.DecathlonResultEntry;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVDecathlonResultReader implements IDecathlonResultReader {

    private static final String CSV_DELIMITER = ";";

    private String fileName;
    private DecathlonCoefficients decathlonCoefficients;
    private DecathlonEventResultPointsCalculator pointsCalculator;
    private String[] eventNames;

    public CSVDecathlonResultReader(String fileName) {
        this.fileName = fileName;
        eventNames = new String[]{
                "SPRINT", "LONG_JUMP", "SHOT_PUT", "HIGH_JUMP",
                "FOUR_HUNDRED_METER_RUN", "HURDLES", "DISCUS_THROW",
                "POLE_VAULT", "JAVELIN_THROW",
                "ONE_AND_HALF_KILOMETER_RUN"};
    }

    public CSVDecathlonResultReader(
            String fileName,
            DecathlonCoefficients decathlonCoefficients
    ) {
        this(fileName);
        this.decathlonCoefficients = decathlonCoefficients;
    }

    @Override
    public List<DecathlonResultEntry> readAllEntries() throws Exception {

        List<DecathlonResultEntry> resultEntries = new ArrayList<>();

        //try to open a file
        File inputFile = new File(fileName);

        Scanner inputFileScanner = new Scanner(inputFile);

        while (inputFileScanner.hasNext()) {
            String line = inputFileScanner.nextLine();
            System.out.println(line);
            DecathlonResultEntry resultEntry =
                    parseDecathlonResultEntryString(line);
            resultEntries.add(resultEntry);
        }
        inputFileScanner.close();

        return resultEntries;
    }

    DecathlonResultEntry parseDecathlonResultEntryString(String entryString) {
        /**
         * Assuming that the entry line is constructed in the following order:
         * Athlete's name
         * 100m sprint time in seconds
         * Long jump distance in meters
         * Shot put distance in meters
         * High jump distance in meters
         * 400m run time in seconds
         * 110m hurdles time in seconds
         * Discus throw distance in meters
         * Pole vault distance in meters
         * Javelin throw distance in meters
         * 1500m run time in minutes.seconds
         */

        String[] decathlonResultCols = entryString.split(CSV_DELIMITER);

        //Assume first column is athlete's name
        DecathlonResultEntry resultEntry =
                new DecathlonResultEntry(decathlonResultCols[0]);

        for(int i = 1; i <= eventNames.length; i++) {
            String eventName = eventNames[i - 1];
            Double eventResultValue;
            String eventType =
                    DecathlonEventType.valueOf(eventName).getEventType();
            //Special case for 1.5km run - time is given in minutes and seconds.
            //This value needs to be converted to seconds.
            if(i == 10) {
                String[] oneAndHalfKilometerRunTimeParts =
                        decathlonResultCols[i].split(":");
                eventResultValue =
                        Double.parseDouble(
                                oneAndHalfKilometerRunTimeParts[0]) * 60
                                +
                        Double.parseDouble(oneAndHalfKilometerRunTimeParts[1]);
            }
            else eventResultValue = Double.parseDouble(decathlonResultCols[i]);

            resultEntry.addEventResult(eventName, eventType, eventResultValue);

        }

        return resultEntry;
    }
}
