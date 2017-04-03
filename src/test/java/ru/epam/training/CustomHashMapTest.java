package ru.epam.training;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
    public void testThatWECanGetValueByKey() {
        String value = "value";
        m.put(1, value);
        assertEquals(value, m.get(1));
    }

    @Test
    public void testThatWeCanGetNullKeyElement() {
        String value = null;
        m.put(1, value);
        assertEquals(value, m.get(1));
    }

    @Test
    public void testThatMapCanPutPairWithKeyThatAlreadyPresentedAndGetPreviousValue() {
        String value = "ss1";
        m.put(1, value);
        assertEquals(value, m.put(1, "ss2"));
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
        String value1 = null;
        String value2 = "ss2";
        m.put(1, value1);
        m.put(17, value2);
        assertTrue(m.containsValue(value1));
        assertTrue(m.containsValue(value2));
    }


    @Test
    public void testThatMapCalculateItsSizeProperlyForPuttingSomeElements() {
        int expectedSize = 50;
        for (int i = 0; i < 50; i++) {
            m.put(i, "ss" + i);
        }
        assertTrue(expectedSize == m.size());
    }

    @Test
    public void testThatForMultipleKeysWithTheSameHashIfWePutNewValueWithOldKeyOnlyValueWillBeChanged() {
        int expectedSize = 3;
        m.put(1, "ss1");
        m.put(17, "ss17");
        m.put(33, "ss33");
        m.put(17, "ss172");
        assertTrue(m.size() == expectedSize);
    }

    @Test
    public void testThatWeCanGetCorrectElementWithKeyWhichHashHaveMultipleElementsInBucket() {
        String value1 = "ss1";
        String value2 = "ss17";
        String value3 = "ss33";
        m.put(1, value1);
        m.put(17, value2);
        m.put(33, value3);
        assertEquals(value1, m.get(1));
        assertEquals(value2, m.get(17));
        assertEquals(value3, m.get(33));
    }


    @Test
    public void testThatRemoveMethodNotDeletingWholeLLFromBucket() {
        String value1 = "ss1";
        String value2 = "ss17";
        String value3 = "ss33";
        m.put(1, value1);
        m.put(17, value2);
        m.put(33, value3);
        m.remove(33);
        assertTrue(m.containsKey(1));
        assertTrue(m.containsKey(17));
        assertTrue(m.containsValue(value1));
        assertTrue(m.containsValue(value2));
        assertFalse(m.containsKey(33));
        assertFalse(m.containsValue(value3));

    }

    @Test
    public void testThatRemoveMethodReturnsPreviousValue() {
        String value1 = "ss1";
        m.put(1, value1);
        assertEquals(value1, m.remove(1));
    }

    @Test
    public void testThatRemoveMethodReturnsNullIfNoSuchKeyInMap() {
        assertEquals(null, m.remove(1));
    }

    @Test
    public void testThatRemoveMethodWorksWellWithNullKey() {
        String value1 = "ss1";
        String value2 = "ss17";
        String value3 = "ss33";
        m.put(1, value1);
        m.put(null, value2);
        m.put(33, value3);
        assertEquals(value2, m.remove(null));
    }

    @Test
    public void testThatRemoveMethodChangingSizeProperlyRemovingInRightOrder() {
        int putAmount = 50;
        int removeAmount = 40;
        int expectedSize = putAmount - removeAmount;
        for (int i = 0; i < putAmount; i++) {
            m.put(i, "ss" + i);
        }
        for (int i = 0; i < removeAmount; i++) {
            m.remove(i);
        }
        assertTrue(expectedSize == m.size());

    }

    @Test
    public void testThatRemoveMethodChangingSizeProperlyRemovingInBackOrder() {
        int putAmount = 50;
        int removeAmount = 40;
        int expectedSize = putAmount - removeAmount;
        for (int i = 0; i < putAmount; i++) {
            m.put(i, "ss" + i);
        }
        for (int i = removeAmount - 1; i >= 0; i--) {
            m.remove(i);
        }
        assertTrue(expectedSize == m.size());

    }

}
