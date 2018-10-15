package Domain;

public class DecathlonResultCalculated {

    private String AthleteName;
    private Integer LongJumpScore;
    private Integer ShotPutScore;
    private Integer HighJumpScore;
    private Integer FourHundredMeterRunScore;
    private Integer HurdlesScore;
    private Integer DiscusThrowScore;
    private Integer PoleVaultScore;
    private Integer JavelinThrowScore;
    private Integer OneAndHalfKilometerRunScore;

    public String getAthleteName() {
        return AthleteName;
    }

    public void setAthleteName(String athleteName) {
        AthleteName = athleteName;
    }

    public Integer getLongJumpScore() {
        return LongJumpScore;
    }

    public void setLongJumpScore(Integer longJumpScore) {
        LongJumpScore = longJumpScore;
    }

    public Integer getShotPutScore() {
        return ShotPutScore;
    }

    public void setShotPutScore(Integer shotPutScore) {
        ShotPutScore = shotPutScore;
    }

    public Integer getHighJumpScore() {
        return HighJumpScore;
    }

    public void setHighJumpScore(Integer highJumpScore) {
        HighJumpScore = highJumpScore;
    }

    public Integer getFourHundredMeterRunScore() {
        return FourHundredMeterRunScore;
    }

    public void setFourHundredMeterRunScore(Integer fourHundredMeterRunScore) {
        FourHundredMeterRunScore = fourHundredMeterRunScore;
    }

    public Integer getHurdlesScore() {
        return HurdlesScore;
    }

    public void setHurdlesScore(Integer hurdlesScore) {
        HurdlesScore = hurdlesScore;
    }

    public Integer getDiscusThrowScore() {
        return DiscusThrowScore;
    }

    public void setDiscusThrowScore(Integer discusThrowScore) {
        DiscusThrowScore = discusThrowScore;
    }

    public Integer getPoleVaultScore() {
        return PoleVaultScore;
    }

    public void setPoleVaultScore(Integer poleVaultScore) {
        PoleVaultScore = poleVaultScore;
    }

    public Integer getJavelinThrowScore() {
        return JavelinThrowScore;
    }

    public void setJavelinThrowScore(Integer javelinThrowScore) {
        JavelinThrowScore = javelinThrowScore;
    }

    public Integer getOneAndHalfKilometerRunScore() {
        return OneAndHalfKilometerRunScore;
    }

    public void setOneAndHalfKilometerRunScore(Integer oneAndHalfKilometerRunScore) {
        OneAndHalfKilometerRunScore = oneAndHalfKilometerRunScore;
    }

    public Integer getTotalScore() {
        return
                LongJumpScore +
                ShotPutScore +
                HighJumpScore +
                FourHundredMeterRunScore +
                HurdlesScore +
                DiscusThrowScore +
                PoleVaultScore +
                JavelinThrowScore +
                OneAndHalfKilometerRunScore;
    }
    
    
}
