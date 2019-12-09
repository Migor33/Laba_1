package com.person.tools;

import com.person.MyRepository;
import com.person.exceptions.InjectFailedException;
import com.person.sorts.BubbleSort;
import org.junit.Test;
import ru.vsu.lab.entities.IPerson;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class ReflectorTest {

    @Test
    public void injectTest() throws NoSuchFieldException, IllegalAccessException, InjectFailedException {
        MyRepository<IPerson> repository = new MyRepository<>();
        Injector.inject(repository);
        Field field = repository.getClass().getDeclaredField("sort");
        field.setAccessible(true);
        assertEquals(field.get(repository).getClass(),BubbleSort.class);
    }
}