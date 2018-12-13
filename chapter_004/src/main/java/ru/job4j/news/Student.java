package ru.job4j.news;

import java.util.Comparator;

/**
 * Class Student.
 *
 * @author dshustrov
 * @version 1
 * @since 12.12.2018
 */
public class Student implements Comparator<Student> {
    /**
     * name SNP studet.
     */
    private String name;
    /**
     * scope ball certificate.
     */
    private int scope;

    /**
     * Constructor.
     *
     * @param name  SNP studet.
     * @param scope ball certificate.
     */
    public Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    /**
     * The method access field scope.
     */
    public int getScope() {
        return scope;
    }

    /**
     * The method access field name.
     */
    public String getName() {
        return name;
    }

    /**
     * The method access field Name.
     */
    @Override
    public int compare(Student o1, Student o2) {
        return o1.name.compareTo(o2.name);
    }

    @Override
    public String toString() {
        return "Student{"
                +
                "name='"
                + name
                + '\''
                +
                ", scope="
                + scope
                +
                '}';
    }
}
