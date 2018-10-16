import Reader.CSVDecathlonResultReader;

public class EntryPoint {
    public static void main(String[] args) throws Exception {

        CSVDecathlonResultReader resultReader = new CSVDecathlonResultReader("/home/user/input.csv");
        resultReader.readAllEntries();
        System.out.println("TESTING GRADLE");
    }

}
