package ru.job4j.inheritance;

/**
 * Class Diagnose.
 * @author dshustrov
 * @version 1
 * @since 08.11.2018
 */
class Diagnose {
    public String findDiagnosis(Patient patient) {
        if (patient.getAppearance() == 5) {
            return "здоров";
        } else {
            return "болен";
        }

    }
}