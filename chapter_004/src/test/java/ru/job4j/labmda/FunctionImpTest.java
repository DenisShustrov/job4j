package ru.job4j.labmda;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author dshustrov
 * @version $Id$
 * @since 0.1
 */
public class FunctionImpTest {
    @Test
    public void thenStartDiapasonWhenLinearDependence() {
        FunctionImp imp = new FunctionImp();
        Double result = imp.diapason(2, 52, index -> index).get(1);
        assertThat(result, is(3.0));
    }

    @Test
    public void thenStartDiapasonWhenSquareDependence() {
        FunctionImp imp = new FunctionImp();
        Double result = imp.diapason(2, 52, index -> index * index).get(1);
        assertThat(result, is(9.0));
    }

    @Test
    public void thenStartDiapasonWhenLogarithmicDependence() {
        FunctionImp imp = new FunctionImp();
        Double result = imp.diapason(2, 52,
                Math::log).get(1);
        Double expect = Math.log(3);
        assertThat(result, is(expect));
    }
}
