package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TwoWordsComparTest {

    @Test
    public void whenWordsComparisonThanTrue() {
        TwoWordsCompar twc = new TwoWordsCompar();
        boolean result = twc.wordsComparison("казарма", "амразак");
        assertThat(result, is(true));
    }

    @Test
    public void whenWordsComparisonThanFalse() {
        TwoWordsCompar twc = new TwoWordsCompar();
        boolean result = twc.wordsComparison("казарма", "мразак");
        assertThat(result, is(false));
    }
}
