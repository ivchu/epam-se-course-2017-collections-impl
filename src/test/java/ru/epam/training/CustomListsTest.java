package ru.epam.training;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CustomListsTest {

    private List<?> list;

    public CustomListsTest(List<?> list) {
        this.list = list;
    }

    @Parameterized.Parameters
    public static Collection<Object> data() {
        return Arrays.asList(new Object[]{
//                new CustomArrayList(),
                new CustomLinkedList()
        });
    }

}
