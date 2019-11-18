package com.person;

import com.person.enums.Gender;
import com.person.personInterface.IPerson;
import com.person.personInterface.IRepository;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Random;

import static org.junit.Assert.*;

public class PersonArrayTest {

    private static final int N = 20;
    private static final int K =5;
    private static final int MIN_YEAR = 1980;
    private static final int MAX_YEAR = 2018;
    private static final int MIN_PASSPORT_ID = 1000;
    private static final int MAX_PASSPORT_ID = 2000;
    private static final int MAX_MONTH = 12;
    private static final int MAX_DAY = 28;
    private PersonArray array;

    class BubbleSort implements PersonArray.SortInterface {

        @Override
        public void sort(Comparator<IPerson> comparator, PersonArray personArray) {
            for (int i = 0; i < personArray.length()-1; i++) {
                for (int j = 0; j < personArray.length()-i-1; j++) {
                    if (comparator.compare(personArray.get(j),personArray.get(j+1)) > 0) {
                        IPerson temp = personArray.get(j);
                        personArray.set(j,personArray.get(j+1));
                        personArray.set(j+1,temp);
                    }
                }
            }
        }

    }


    @Before
    public void initTest() {
        Random random = new Random();
        array = new PersonArray();
        for (int i = 0; i < N; i++) {
            array.add(new Person(new LocalDate(random.nextInt((MAX_YEAR - MIN_YEAR) + 1) + MIN_YEAR,
                    random.nextInt(MAX_MONTH) + 1,
                    random.nextInt(MAX_DAY) + 1),
                    random.nextInt(MAX_PASSPORT_ID - MIN_PASSPORT_ID) + MIN_PASSPORT_ID, Gender.MALE,
                    "PersonName" + i,
                    "PersonSurname" + i,
                    "PersonMiddleName" + i,
                    null,
                    new BigDecimal(random.nextInt())));
        }
    }

    @After
    public void afterTest() {
        array = null;
    }


    @Test
    public void sortByTest() {
        array.sortBy(Comparator.comparing(IPerson::getAge), new BubbleSort());
        boolean temp = false;
        for (int i = 0; i < array.length()-1; i++) {
            if (temp) {
                break;
            }
            temp = array.get(i).getAge().compareTo(array.get(i+1).getAge()) > 0;
        }
        assertFalse(temp);
    }

    @Test
    public void searchBy() {
        IRepository temp = array.searchBy((p) -> p.getAge() > 12);
        boolean test = true;
        for (int i = 0; temp.get(i)!=null; i++) {
            test = temp.get(i).getAge() > 12;
            if (!test) {
                break;
            }
        }
        assertTrue(test);
    }
}