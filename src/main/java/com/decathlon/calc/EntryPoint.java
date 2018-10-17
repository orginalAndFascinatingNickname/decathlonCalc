package com.decathlon.calc;

import com.decathlon.calc.Domain.DecathlonEventResult;
import com.decathlon.calc.Domain.DecathlonResultEntry;
import com.decathlon.calc.Properties.DecathlonCoefficients;
import com.decathlon.calc.Reader.CSVDecathlonResultReader;

import java.util.*;

public class EntryPoint {
    public static void main(String[] args) throws Exception {

        CSVDecathlonResultReader resultReader = new CSVDecathlonResultReader("/home/user/input.csv");
        List<DecathlonResultEntry> result = resultReader.readAllEntries();

        DecathlonEventResultPointsCalculator calculator = new DecathlonEventResultPointsCalculator();

        //Calculate scores for each entry
        for(DecathlonResultEntry entry : result) {
            Integer newScore = 0;

            for(DecathlonEventResult eventResult : entry.getAllEventResults()) {
                newScore += calculator.calculateEventScore(eventResult);
                System.out.print(eventResult.getName() + " ");
                System.out.println(calculator.calculateEventScore(eventResult));
            }
            entry.setTotalScore(newScore);
            System.out.println("TOTAL: " + entry.getTotalScore());
            System.out.println("-------------------------------------");

        }



        //Group results based on total score
        // and sort them by total score in descending order
        Map<Integer, List<DecathlonResultEntry>> scoreGroups= new TreeMap<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);//sort in descending order
                    }
                });

        for(DecathlonResultEntry entry : result) {
            if(!scoreGroups.containsKey(entry.getTotalScore())) {
                List<DecathlonResultEntry> groupEntries = new ArrayList<>();
                groupEntries.add(entry);
                scoreGroups.put(entry.getTotalScore(), groupEntries);
            }
            else {
                scoreGroups.get(entry.getTotalScore()).add(entry);
            }
        }


        //Assign places
        Integer counter = 1;
        String place = "1";
        boolean shared = false;
        List<DecathlonResultEntry> placedResults = new ArrayList<>();

        for(Integer group : scoreGroups.keySet()) {
            List<DecathlonResultEntry> entriesInGroup = scoreGroups.get(group);
            if(entriesInGroup.size() <= 1) {
                entriesInGroup.get(0).setPlace(counter.toString());
                placedResults.add(entriesInGroup.get(0));
                counter++;
            }
            else {
                for(DecathlonResultEntry entry : entriesInGroup) {
                    entry.setPlace(String.format("%d-%d",
                            counter, counter + entriesInGroup.size() - 1)
                    );
                    placedResults.add(entry);
                }
                counter += entriesInGroup.size();
            }
        }


        for(DecathlonResultEntry entry : placedResults) {
            System.out.println(entry.getAthleteName() + " " + entry.getPlace());
        }





        DecathlonCoefficients coefs = new DecathlonCoefficients();
        coefs.readPropertiesFile("decathlon_coefficients");
        System.out.println("TESTING GRADLE");
    }

}
