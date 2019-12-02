package com.person.tools;

import com.person.MyRepository;
import com.person.Person;
import com.person.sorts.BubbleSort;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class ReflectorTest {

    @Test
    public void reflectorTest() throws NoSuchFieldException, IllegalAccessException {
        MyRepository<Person> repository = new MyRepository<>();
        Reflector.reflect(repository);
        Field field = repository.getClass().getDeclaredField("sort");
        field.setAccessible(true);
        assertEquals(field.get(repository).getClass(),BubbleSort.class);
    }
}