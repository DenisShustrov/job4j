package ru.job4j.news;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class levelOfImpl.
 *
 * @author dshustrov
 * @version 1
 * @since 12.12.2018
 */
public class LevelOfImpl {

    /**
     * The method return a list of students whose certificate score is more bound.
     *
     * @param students students list.
     * @param bound    passing score.
     */
    public List<Student> levelOf(List<Student> students, int bound) {
        return students.stream().flatMap(Stream::ofNullable).sorted(((o1, o2) -> Integer.compare(o2.getScope(), o1.getScope()))).takeWhile(v -> v.getScope() > bound).collect(Collectors.toList());
    }
}
