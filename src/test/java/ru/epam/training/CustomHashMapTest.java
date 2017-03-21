package ru.epam.training;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class CustomHashMapTest {

    @Test
    public void testThatWeCanCreate() {
        Map m = new CustomHashMap();

        assertThat(m, is(notNullValue()));
    }
}
