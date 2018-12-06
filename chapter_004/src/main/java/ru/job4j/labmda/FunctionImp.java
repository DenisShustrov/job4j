package ru.job4j.labmda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Class FunctionImp.
 *
 * @author dshustrov
 * @version 1
 * @since 04.12.2018
 */
public class FunctionImp {

    public static void main(String[] args) {
        FunctionImp functionImp = new FunctionImp();
        functionImp.diapason(2, 52,
                index -> index);

        functionImp.diapason(2, 52,
                index -> index * index);

        functionImp.diapason(2, 52,
                Math::log);
    }

    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> value = new ArrayList<>();
        for (int index = start; index != end; index++) {
            value.add(func.apply((double) index));
        }
        return value;
    }

}
