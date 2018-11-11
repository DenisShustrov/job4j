package ru.job4j.inheritance;

/**
 * Class Profession.
 * @author dshustrov
 * @version 1
 * @since 08.11.2018
 */
class Teacher extends Profession {
    public Student teachStudents(Student student) {
        return student;
    }
}
