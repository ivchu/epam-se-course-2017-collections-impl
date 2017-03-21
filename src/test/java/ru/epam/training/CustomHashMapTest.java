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

    @Test
    public void testThatNewMapIsEmpty(){

    }

    @Test
    public void testThatNewMapDoesNotContainAnyObject() {

    }

    @Test
    public void testThatWeCanPutKeyValuePairAndCanCheckIt() {
    }

    @Test
    public void testThatWeCantPutNullKey() {
    }

    @Test
    public void testThatWeCanPutNullValue() {
    }

    @Test(expected = OutOfMemoryError.class)
    public void testThatMapHaveInfiniteCapacity() {
    }

    @Test
    public void testThatMapCanPutPairWithKeyThatAlreadyPresented() {
    }

    @Test
    public void testThatMapCanContainsKeysWithSameHashCode() {
    }

    @Test(expected = NullPointerException.class)
    public void testThatContainsKeyMethodThrowsExceptionOnNullKey() {
    }

    @Test(expected = ClassCastException.class)
    public void testThatContainsKeyMethodThrowsExceptionOnWrongKeyClass() {
    }

    @Test
    public void testContainsValueMethodWorksProperlyOn() {
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
