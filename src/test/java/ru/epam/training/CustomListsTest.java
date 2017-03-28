package ru.epam.training;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CustomListsTest {

    private List<String> list;

    public CustomListsTest(List<String> list) {
        this.list = list;
    }

    @Parameterized.Parameters
    public static Collection<Object> data() {
        return Arrays.asList(new Object[]{
                new CustomArrayList(),
                new CustomLinkedList()
        });
    }

    @Test
    public void testThatNewListIsEmpty() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void testThatListNotEmptyAfterAddingElement() {
        list.add("aaaa");
        assertThat(list.isEmpty(), is(false));
    }

    @Test
    public void testThatListContainsElementThatWeAddedBefore() {
        String value = "bbb";

        list.add(value);

        assertTrue(list.contains(value));
    }

    @Test
    public void testThatListNotContainsElementThatWasntAddedToList() throws Exception {
        list.add("fff");
        assertFalse(list.contains("ccc"));
    }

    @Test
    public void testThatListContainsNullIfItWasAdded() {

        list.add(null);

        assertTrue(list.contains(null));
    }

    @Test
    public void testThatListNotContainsNullIfItWasNotAdded() {
        list.add("fff");
        assertFalse(list.contains(null));
    }

    @Test
    public void testThatListsSizeIsDynamic() throws Exception {
        int size = 50;

        for (int i = 0; i < size; i++) {
            list.add(String.valueOf(i));
        }

        assertThat(list.size(), is(size));
    }

    @Test
    public void testThatWeCanGetElementByIndex() {

        list.add("aaa");
        list.add("aa2");
        list.add("aad");

        assertThat(list.get(1), is(equalTo("aa2")));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatWeCantGetElementByIndexMoreThenSize() throws Exception {

        list.add("aaa");
        list.add("aa2");
        list.add("aad");

        list.get(list.size());
    }

    @Test
    public void testThatWeCanRemoveExistedElementFromList() throws Exception {
        String value = "ssss";
        list.add("aa0a");
        list.add("aa1a");
        list.add(value);
        list.add("aa2a");
        list.add("aa3a");
        list.add("aa4a");

        list.remove(value);

        assertFalse("contains",list.contains(value));
    }
}
