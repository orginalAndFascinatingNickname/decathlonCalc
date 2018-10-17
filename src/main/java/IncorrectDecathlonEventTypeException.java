public class IncorrectDecathlonEventTypeException extends Exception {

    private String message;

    public IncorrectDecathlonEventTypeException(String givenEventType) {
        message = String.format(
                "Incorrect decathlon event type given. " +
                        "Expected: track or field. Given {0}", givenEventType
        );
    }

}
