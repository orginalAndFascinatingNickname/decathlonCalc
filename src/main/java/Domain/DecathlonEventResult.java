package Domain;

public class DecathlonEventResult {

    private String Name;
    private String Type;
    private Double Result;

    public DecathlonEventResult(String name, String type, Double result) {
        this.Name = name;
        this.Result = result;
        this.Type = type;

    }

    public String getName() {
        return Name;
    }

    public String getType() {
        return Type;
    }

    public Double getResult() {
        return Result;
    }

    @Override
    public int hashCode() {
        return Name.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof DecathlonEventResult)) {
            return false;
        }

        return ((DecathlonEventResult) obj).Name.equals(this.Name);
    }

}
