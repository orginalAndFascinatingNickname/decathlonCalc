package Domain;


import java.util.HashSet;
import java.util.Set;

public class DecathlonResultEntry {

    private String AthleteName;

    private Set<DecathlonEventResult> eventResults;

    private Integer totalScore;

    public DecathlonResultEntry() {
        eventResults = new HashSet<>();
    }

    public DecathlonResultEntry(String athleteName) {
        this();
        AthleteName = athleteName;
    }

    public DecathlonEventResult getEventResult(String eventName) {
        for(DecathlonEventResult eventResult : eventResults) {
            if(eventResult.getName().equals(eventName)) return eventResult;
        }
        return null;
    }

    public Set<DecathlonEventResult> getAllEventResults() {
        return new HashSet<DecathlonEventResult>(eventResults);
    }

    public boolean addEventResult(DecathlonEventResult eventResult) {
        if(eventResult == null) return false;
        return eventResults.add(eventResult);
    }

    public boolean addEventResult(String name, String type, Double value) {
        if(name == null || type == null || value == null) return false;

        DecathlonEventResult eventResultToAdd = new DecathlonEventResult(name, type, value);
        return addEventResult(eventResultToAdd);
    }

    public String getAthleteName() {
        return AthleteName;
    }

    public void setAthleteName(String athleteName) {
        AthleteName = athleteName;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
}
