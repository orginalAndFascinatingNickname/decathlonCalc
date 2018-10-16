package Domain;



public class DecathlonResultEntry {

    private String AthleteName;

    private Double HundredMetersSprintTime;
    private Double LongJumpDistance;
    private Double ShotPutDistance;
    private Double HighJumpDistance;
    private Double FourHundredMeterRunTime;
    private Double HurdlesTime;
    private Double DiscusThrowDistance;
    private Double PoleVaultDistance;
    private Double JavelinThrowDistance;
    private Double OneAndHalfKilometerRunTime;
    private Integer TotalScore;


    public DecathlonResultEntry(
            String athleteName,
            Double hundredMetersSprintTime,
            Double longJumpDistance,
            Double shotPutDistance,
            Double highJumpDistance,
            Double fourHundredMeterRunTime,
            Double hurdlesTime,
            Double discusThrowDistance,
            Double poleVaultDistance,
            Double javelinThrowDistance,
            Double oneAndHalfKilometerRunTime,
            Integer totalScore
    ) {
        AthleteName = athleteName;
        HundredMetersSprintTime = hundredMetersSprintTime;
        LongJumpDistance = longJumpDistance;
        ShotPutDistance = shotPutDistance;
        HighJumpDistance = highJumpDistance;
        FourHundredMeterRunTime = fourHundredMeterRunTime;
        HurdlesTime = hurdlesTime;
        DiscusThrowDistance = discusThrowDistance;
        PoleVaultDistance = poleVaultDistance;
        JavelinThrowDistance = javelinThrowDistance;
        OneAndHalfKilometerRunTime = oneAndHalfKilometerRunTime;
        TotalScore = totalScore;
    }

    public String getAthleteName() {
        return AthleteName;
    }

    public Double getHundredMetersSprintTime() {
        return HundredMetersSprintTime;
    }

    public Double getLongJumpDistance() {
        return LongJumpDistance;
    }

    public Double getShotPutDistance() {
        return ShotPutDistance;
    }

    public Double getHighJumpDistance() {
        return HighJumpDistance;
    }

    public Double getFourHundredMeterRunTime() {
        return FourHundredMeterRunTime;
    }

    public Double getHurdlesTime() {
        return HurdlesTime;
    }

    public Double getDiscusThrowDistance() {
        return DiscusThrowDistance;
    }

    public Double getPoleVaultDistance() {
        return PoleVaultDistance;
    }

    public Double getJavelinThrowDistance() {
        return JavelinThrowDistance;
    }

    public Double getOneAndHalfKilometerRunTime() {
        return OneAndHalfKilometerRunTime;
    }
    
    public Integer getTotalScore() {
        return TotalScore;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(String.format("Athelete's name: %s, ", AthleteName));
        builder.append(String.format("Sprint time: %f, ", HundredMetersSprintTime));
        builder.append(String.format("Long jump: %f, ", LongJumpDistance));
        builder.append(String.format("Shot put: %f, ", ShotPutDistance));
        builder.append(String.format("High jump: %f, ", HighJumpDistance));
        builder.append(String.format("400m run: %f, ", FourHundredMeterRunTime));
        builder.append(String.format("110m hurdles: %f, ", HurdlesTime));
        builder.append(String.format("Discus throw: %f, ", DiscusThrowDistance));
        builder.append(String.format("Pole vault: %f, ", PoleVaultDistance));
        builder.append(String.format("Javelin throw: %f, ", JavelinThrowDistance));
        builder.append(String.format("1500m run: %f, ", OneAndHalfKilometerRunTime));
        builder.append(String.format("Total score: %d", TotalScore));
        builder.append("}");

        return builder.toString();
    }
}
