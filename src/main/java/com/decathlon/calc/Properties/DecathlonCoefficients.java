package com.decathlon.calc.Properties;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DecathlonCoefficients {

    //Default coefficients
    private static final Map<String, Double> DEFAULT_COEFS_MAP;
    static {
        DEFAULT_COEFS_MAP = new HashMap<>();

        DEFAULT_COEFS_MAP.put("SPRINT_A", 25.4347);
        DEFAULT_COEFS_MAP.put("SPRINT_B", 18.0);
        DEFAULT_COEFS_MAP.put("SPRINT_C", 1.81);

        DEFAULT_COEFS_MAP.put("LONG_JUMP_A", 0.14354);
        DEFAULT_COEFS_MAP.put("LONG_JUMP_B", 220.0);
        DEFAULT_COEFS_MAP.put("LONG_JUMP_C", 1.4);

        DEFAULT_COEFS_MAP.put("SHOT_PUT_A", 51.39);
        DEFAULT_COEFS_MAP.put("SHOT_PUT_B", 1.5);
        DEFAULT_COEFS_MAP.put("SHOT_PUT_C", 1.05);

        DEFAULT_COEFS_MAP.put("HIGH_JUMP_A", 0.8465);
        DEFAULT_COEFS_MAP.put("HIGH_JUMP_B", 75.0);
        DEFAULT_COEFS_MAP.put("HIGH_JUMP_C", 1.42);

        DEFAULT_COEFS_MAP.put("FOUR_HUNDRED_METER_RUN_A", 1.53775);
        DEFAULT_COEFS_MAP.put("FOUR_HUNDRED_METER_RUN_B", 82.0);
        DEFAULT_COEFS_MAP.put("FOUR_HUNDRED_METER_RUN_C", 1.81);

        DEFAULT_COEFS_MAP.put("HURDLES_A", 5.74352);
        DEFAULT_COEFS_MAP.put("HURDLES_B", 28.5);
        DEFAULT_COEFS_MAP.put("HURDLES_C", 1.92);

        DEFAULT_COEFS_MAP.put("DISCUS_THROW_A", 12.91);
        DEFAULT_COEFS_MAP.put("DISCUS_THROW_B", 4.0);
        DEFAULT_COEFS_MAP.put("DISCUS_THROW_C", 1.1);

        DEFAULT_COEFS_MAP.put("POLE_VAULT_A", 0.2797);
        DEFAULT_COEFS_MAP.put("POLE_VAULT_B", 100.0);
        DEFAULT_COEFS_MAP.put("POLE_VAULT_C", 1.35);

        DEFAULT_COEFS_MAP.put("JAVELIN_THROW_A", 10.14);
        DEFAULT_COEFS_MAP.put("JAVELIN_THROW_B", 7.0);
        DEFAULT_COEFS_MAP.put("JAVELIN_THROW_C", 1.08);

        DEFAULT_COEFS_MAP.put("ONE_AND_HALF_KILOMETER_RUN_A", 0.03768);
        DEFAULT_COEFS_MAP.put("ONE_AND_HALF_KILOMETER_RUN_B", 480.0);
        DEFAULT_COEFS_MAP.put("ONE_AND_HALF_KILOMETER_RUN_C", 1.85);
    }

    private Map<String, Double> decathlonCoefficientsMap;

    public DecathlonCoefficients() {
        decathlonCoefficientsMap = new HashMap<>();
    }

    public static Double getDefaultCoefficientValue(String name) {
        if(DEFAULT_COEFS_MAP.containsKey(name))
            return DEFAULT_COEFS_MAP.get(name);

        return null;
    }

    public Double getCoefficientValue(String coefName) {
        if(!decathlonCoefficientsMap.containsKey(coefName)) {
            if(!DEFAULT_COEFS_MAP.containsKey(coefName)) return null;
            return DEFAULT_COEFS_MAP.get(coefName);
        }

        return decathlonCoefficientsMap.get(coefName);
    }

    public boolean addCoefficient(String name, Double value) {
        if(
            name == null ||
            value == null ||
            decathlonCoefficientsMap.containsKey(name)
        )
            return false;

        return decathlonCoefficientsMap.put(name, value).equals(value);
    }

    public void readPropertiesFile(String filename) {
        ResourceBundle decathlonCoefsFile = ResourceBundle.getBundle(filename);
        Enumeration<String> coefNames = decathlonCoefsFile.getKeys();

        while(coefNames.hasMoreElements()) {
            String key = coefNames.nextElement();
            String valueString = decathlonCoefsFile.getString(key);

            try {
                Double value = Double.parseDouble(valueString);
                decathlonCoefficientsMap.put(key, value);
            }
            catch (Exception e) {
                System.out.println(
                        String.format(
                                "Failed to parse coefficient value." +
                                        " Given coef value - %s", valueString)
                );
            }
        }
    }

}
