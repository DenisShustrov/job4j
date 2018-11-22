package ru.job4j.coffee;

import java.util.ArrayList;
import java.util.List;

/**
 * Class CoffeeMachine.
 *
 * @author dshustrov
 * @version 1
 * @since 08.11.2018
 */
public class CoffeeMachine {

    /**
     * Метод выдачи сдачи для CoffeeMachine.
     *
     * @param value купюра.
     * @return price цена кофе.
     */
    public int[] changes(int value, int price) {
        List<Integer> coin = new ArrayList<>();
        int coinOun = 1;
        int coinTwo = 2;
        int coinFivee = 5;
        int coinTen = 10;
        int change = value - price;
        if (price > value) {
            return null;
        }
        while (change != 0) {
            if (change >= coinTen) {
                coin.add(coinTen);
                change = change - coinTen;
            } else if (change >= coinFivee) {
                coin.add(coinFivee);
                change = change - coinFivee;
            } else if (change >= coinTwo) {
                coin.add(coinTwo);
                change = change - coinTwo;
            } else if (change >= coinOun) {
                coin.add(coinOun);
                change = change - coinOun;
            }
        }
        int[] result = new int[coin.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = coin.get(i);
        }
        return result;
    }
}
