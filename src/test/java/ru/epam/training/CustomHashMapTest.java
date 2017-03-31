package ru.epam.training;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder
public class CustomHashMapTest {
    private Map<Integer, String> m;

    @Before
    public void init() {
        m = new CustomHashMap<>();
    }

    @Test
    public void testThatWeCanCreate() {

        assertThat(m, is(notNullValue()));

    }

    @Test
    public void testThatNewMapIsEmpty() {
        assertThat(m.isEmpty(), is(true));
    }

    @Test
    public void testThatOnNewMapContainKeyMethodReturnFalseForAnyObject() {
        assertThat(m.containsKey(new Integer(1)), is(false));
    }

    @Test
    public void testThatWeCanPutKeyValuePairAndCanCheckIt() {
        m.put(new Integer(3), "abc");
        assertThat(m.containsKey(3), is(true));
    }

    @Test
    public void testThatWeCanPutNullValue() {
        m.put(5, null);
    }


    @Test
    public void testThatWECanGetValueByKey(){
        String value = "value";
        m.put(1, value);
        assertEquals(value, m.get(1));
    }
    @Test
    public void testThatMapCanPutPairWithKeyThatAlreadyPresentedAndGetPreviousValue() {
        String value = "ss1";
        m.put(1, value);
        assertEquals(m.put(1, "ss2"), value);
    }

    @Test
    public void testThatMapCanContainsKeysWithSameHashCode() {
        String value1 = "ss1";
        String value2 = "ss2";
        m.put(1, value1);
        m.put(17, value2);
        m.containsKey(1);
        m.containsKey(17);
    }


    @Test
    public void testContainsValueMethodWorksProperlyForKeysWithDiffHash() {
        String value1 = "ss1";
        String value2 = "ss2";
        m.put(1, value1);
        m.put(2, value2);
        assertTrue(m.containsValue(value1));
        assertTrue(m.containsValue(value2));
    }

    @Test
    public void testContainsValueMethodWorksProperlyForKeysWithSameHash() {
        String value1 = "ss1";
        String value2 = "ss2";
        m.put(1, value1);
        m.put(17, value2);
        assertTrue(m.containsValue(value1));
        assertTrue(m.containsValue(value2));
    }


    @Test
    public void testContainsValueMethodWorksProperlyOnNullInputValue() {
    }

    @Test(expected = ClassCastException.class)
    public void testValueContainsMethodThrowsExceptionOnWrongInputValueClass() {
    }

    @Test
    public void testThatMapCalculateItsSizeProperly() {
    }


}
