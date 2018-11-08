package ru.job4j.inheritance;

/**
 * Class Profession.
 * @author dshustrov
 * @version 1
 * @since 08.11.2018
 */
public class Profession {
    private String name;

    public String getName() {
        return name;
    }
}

class Doctor extends Profession {
    public String treat(Patient patient) {
        return new Diagnose().findDiagnosis(patient);
    }
}

class Engineer extends Profession {
    public House buildHouse(BuildingMaterials buildingMaterials) {
        return new House();
    }
}

class BuildingMaterials {

}

class Teacher extends Profession {
    public Student teachStudents(Student student) {
        return student;
    }
}

class Patient {
    private String name;
    // либо 5 либо 2
    private int appearance;

    public Patient(String name, int appearance) {
        this.name = name;
        this.appearance = appearance;
    }

    public int getAppearance() {
        return appearance;
    }


}

class House {

}

class Student {

}

class Diagnose {

    public String findDiagnosis(Patient patient) {
        if (patient.getAppearance() == 5) {
            return "здоров";
        } else
            return "болен";
    }
}