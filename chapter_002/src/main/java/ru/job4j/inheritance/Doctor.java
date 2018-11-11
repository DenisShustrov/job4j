package ru.job4j.inheritance;

/**
 * Class Doctor.
 * @author dshustrov
 * @version 1
 * @since 08.11.2018
 */
public class Doctor extends Profession {
    public String treat(Patient patient) {
        return new Diagnose().findDiagnosis(patient);
    }
}