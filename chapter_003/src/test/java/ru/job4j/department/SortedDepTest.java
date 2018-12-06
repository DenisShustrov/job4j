package ru.job4j.department;

import org.junit.Test;

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
    public void thenaddDepWnenNewArrayList1() {
        String[] dep = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        SortedDep sort = new SortedDep(dep);
        sort.addDep();
        int result = sort.getDirectory().size();
        assertThat(result, is(9));
    }

    @Test
    public void thenaddDepWnenNewArrayList2() {
        String[] dep = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        SortedDep sort = new SortedDep(dep);
        sort.addDep();
        String result = sort.getDirectory().get(sort.getDirectory().size() - 1);
        assertThat(result, is("K2\\SK1"));
    }

    @Test
    public void thensortAscendingWnenNewArrayList() {
        String[] dep = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        SortedDep sort = new SortedDep(dep);
        sort.addDep();
        String result = sort.sortAscending().get(1);
        assertThat(result, is("K1\\SK1"));
    }

    @Test
    public void thensortDescendingWnenNewArrayList() {
        String[] dep = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        SortedDep sort = new SortedDep(dep);
        sort.addDep();
        String result = sort.sortDescending().get(1);
        assertThat(result, is("K2\\SK1"));
    }
}
