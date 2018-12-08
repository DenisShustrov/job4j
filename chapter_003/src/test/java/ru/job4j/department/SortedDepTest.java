package ru.job4j.department;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author dshustrov (denisshustroff@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SortedDepTest {
    @Test
    public void thenaddDepWnenUpdatedTreeSet() {
        String[] dep = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        SortedDep sort = new SortedDep(dep);
        sort.addDep();
        int result = sort.getDirectory().size();
        assertThat(result, is(9));
    }

    @Test
    public void thenAddDepWnenUpdatedTreeSet2() {
        String[] dep = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        SortedDep sort = new SortedDep(dep);
        sort.addDep();
        Iterator<String> iter = sort.getDirectory().iterator();
        String result = iter.next();
        assertThat(result, is("K1"));
    }

    @Test
    public void thensortDescendingWnenUpdatedTreeSet() {
        String[] dep = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        SortedDep sort = new SortedDep(dep);
        sort.addDep();
        sort.sortDescending();
        Iterator<String> iter = sort.getDirectory().iterator();
        String result = iter.next();
        assertThat(result, is("K2"));
    }

    @Test
    public void thenSortAscendingAfterSortDescendingWnenUpdatedTreeSet() {
        String[] dep = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        SortedDep sort = new SortedDep(dep);
        sort.addDep();
        sort.sortDescending();
        sort.sortAscendingAfterSortDescending();
        Iterator<String> iter = sort.getDirectory().iterator();
        String result = iter.next();
        assertThat(result, is("K1"));
    }
}
