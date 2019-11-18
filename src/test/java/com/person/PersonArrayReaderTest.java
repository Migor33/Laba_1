package com.person;

import com.person.personInterface.IRepository;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PersonArrayReaderTest {

    @Test
    public void readRepository() throws FileNotFoundException {
        File file = new File("src\\main\\resources\\persons.csv");
        PersonArrayReader readr = new PersonArrayReader();
        IRepository presonArray =  readr.readRepository(new Scanner(file));

    }
}