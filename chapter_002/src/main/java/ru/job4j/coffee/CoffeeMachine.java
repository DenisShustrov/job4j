package ru.job4j.coffee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
     * Массив c монетами разного наминала.
     */
    private Integer[] coins;

    /**
     * Конструктор.
     * @param coins задаем количество и наминал монет для разных кофемашин.
     */
     public CoffeeMachine(Integer[] coins) {
         this.coins = coins;
     }

    /**
     * Метод выдачи сдачи для CoffeeMachine.
     *
     * @param value купюра.
     * @return price цена кофе.
     */
    public int[] changes(int value, int price) {
        List<Integer> values = new ArrayList<>();
        int change = value - price;
        if (price > value) {
            return null;
        }
        Arrays.sort(coins, Collections.reverseOrder());
        while (change != 0) {
            for (int x : coins) {
                if (change >= x) {
                    values.add(x);
                    change = change - x;
                    break;
                }
            }
        }
        int[] result = new int[values.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = values.get(i);
        }
        return result;
    }
}
