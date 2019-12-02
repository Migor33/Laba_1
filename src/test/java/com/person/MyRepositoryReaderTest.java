package com.person;

import com.person.tools.PersonFactory;
import com.person.tools.PersonArrayReader;
import com.person.tools.Reflector;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class MyRepositoryReaderTest {

    private static Integer COUNT_PERSON_IN_FILE = 25898;

    @Test
    public void readRepository() throws FileNotFoundException {
        File file = new File("src\\main\\resources\\persons.csv");
        PersonArrayReader reader = new PersonArrayReader();
        MyRepository myRepository =  (MyRepository)reader.readRepository(new Scanner(file), new PersonFactory());
        assertEquals(COUNT_PERSON_IN_FILE,myRepository.length());
    }
}