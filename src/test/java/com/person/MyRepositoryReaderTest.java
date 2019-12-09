package com.person;

import com.person.tools.MyRepositoryReader;
import com.person.tools.PersonFactory;
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
        MyRepositoryReader reader = new MyRepositoryReader();
        MyRepository myRepository =  (MyRepository)reader.readRepository(new Scanner(file), new PersonFactory());
        assertEquals(COUNT_PERSON_IN_FILE,myRepository.length());
    }
}