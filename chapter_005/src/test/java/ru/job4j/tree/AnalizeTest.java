package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {

    private Analize analize;

    @Before
    public void beforeTest() {
        analize = new Analize();
    }

    @Test
    public void whenPreviousMoreCurrentThatDiffFulfillsWithoutErrors() {
        List<Analize.User> previous = new ArrayList<>();
        previous.add(new Analize.User(123, "Denis"));
        previous.add(new Analize.User(124, "Ivan"));
        previous.add(new Analize.User(125, "Max"));
        previous.add(new Analize.User(126, "Alex"));
        previous.add(new Analize.User(127, "Dima"));
        List<Analize.User> current = new ArrayList<>();
        current.add(new Analize.User(123, "DenisNew"));
        current.add(new Analize.User(124, "Ivan"));
        current.add(new Analize.User(127, "DimaNew"));
        current.add(new Analize.User(128, "New1"));
        Analize.Info in = analize.diff(previous, current);
        assertThat(in.deleted, is(2));
        assertThat(in.changed, is(2));
        assertThat(in.added, is(1));

    }

    @Test
    public void whenCurrentMorePreviousThatDiffFulfillsWithoutErrors() {
        List<Analize.User> previous = new ArrayList<>();
        previous.add(new Analize.User(127, "Dima"));
        List<Analize.User> current = new ArrayList<>();
        current.add(new Analize.User(127, "New1"));
        current.add(new Analize.User(130, "New3"));
        current.add(new Analize.User(131, "New4"));
        Analize.Info in = analize.diff(previous, current);
        assertThat(in.deleted, is(0));
        assertThat(in.changed, is(1));
        assertThat(in.added, is(2));

    }
}
