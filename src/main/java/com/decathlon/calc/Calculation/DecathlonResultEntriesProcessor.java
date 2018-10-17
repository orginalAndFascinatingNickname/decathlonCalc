package com.decathlon.calc.Calculation;

import com.decathlon.calc.Domain.DecathlonEventResult;
import com.decathlon.calc.Domain.DecathlonResultEntry;
import com.decathlon.calc.IncorrectDecathlonEventTypeException;
import com.decathlon.calc.Properties.DecathlonCoefficients;
import com.decathlon.calc.Reader.IDecathlonResultReader;
import com.decathlon.calc.Writer.IDecathlonResultWriter;

import java.util.*;

public class DecathlonResultEntriesProcessor {

    private IDecathlonResultReader reader;
    private IDecathlonResultWriter writer;
    private DecathlonCoefficients coefficients;

    public DecathlonResultEntriesProcessor(
            IDecathlonResultReader reader,
            IDecathlonResultWriter writer
    ) {
        this.reader = reader;
        this.writer = writer;

    }

    public DecathlonResultEntriesProcessor(
            IDecathlonResultReader reader,
            IDecathlonResultWriter writer,
            DecathlonCoefficients coefficients
    ) {
        this(reader, writer);
        this.coefficients = coefficients;

    }

    public void processRawEntries() throws Exception {
        List<DecathlonResultEntry> result = reader.readAllEntries();
        result = calculateTotalScores(result);



        //Group results based on total score
        // and sort them by total score in descending order
        Map<Integer, List<DecathlonResultEntry>> scoreGroups = new TreeMap<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);//sort in descending order
                    }
                });

        for (DecathlonResultEntry entry : result) {
            if (!scoreGroups.containsKey(entry.getTotalScore())) {
                List<DecathlonResultEntry> groupEntries = new ArrayList<>();
                groupEntries.add(entry);
                scoreGroups.put(entry.getTotalScore(), groupEntries);
            } else {
                scoreGroups.get(entry.getTotalScore()).add(entry);
            }
        }


        //Assign places
        Integer counter = 1;
        List<DecathlonResultEntry> placedResults = new ArrayList<>();

        for (Integer group : scoreGroups.keySet()) {
            List<DecathlonResultEntry> entriesInGroup = scoreGroups.get(group);
            if (entriesInGroup.size() <= 1) {
                entriesInGroup.get(0).setPlace(counter.toString());
                placedResults.add(entriesInGroup.get(0));
                counter++;
            } else {
                for (DecathlonResultEntry entry : entriesInGroup) {
                    entry.setPlace(String.format("%d-%d",
                            counter,
                            counter + entriesInGroup.size() - 1)
                    );
                    placedResults.add(entry);
                }
                counter += entriesInGroup.size();
            }
        }

        writer.writeAllResultEntries(placedResults);

    }

    private List<DecathlonResultEntry> calculateTotalScores(List<DecathlonResultEntry> entries)
                                throws IncorrectDecathlonEventTypeException {
        DecathlonEventResultPointsCalculator calculator;
        if(coefficients != null) calculator =
                new DecathlonEventResultPointsCalculator(coefficients);
        else calculator = new DecathlonEventResultPointsCalculator();

        for (DecathlonResultEntry entry : entries) {
            Integer newScore = 0;

            for (DecathlonEventResult eventResult : entry.getAllEventResults()) {
                newScore += calculator.calculateEventScore(eventResult);
            }
            entry.setTotalScore(newScore);
        }
        return entries;
    }
}
