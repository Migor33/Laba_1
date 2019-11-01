package com.person;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        public void sort(PersonArray.SortCompere compare, PersonArray personArray) {
            for (int i = 0; i < personArray.length()-1; i++) {
                for (int j = 0; j < personArray.length()-i-1; j++) {
                    if (compare.compare(personArray.get(j),personArray.get(j+1))) {
                        Person temp = personArray.get(j);
                        personArray.set(j,personArray.get(j+1));
                        personArray.set(j+1,temp);
                    }
                }
            }
        }

    }
    class SelectionSort implements PersonArray.SortInterface {

        @Override
        public void sort(PersonArray.SortCompere compare, PersonArray personArray) {
            for (int i = 0; i < personArray.length() - 1; i++) {
                for (int j = i + 1; j <personArray.length(); j++) {
                    if (compare.compare(personArray.get(i),personArray.get(j))) {
                        Person temp = personArray.get(i);
                        personArray.set(i,personArray.get(j));
                        personArray.set(j,temp);
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
                    random.nextInt(MAX_PASSPORT_ID - MIN_PASSPORT_ID) + MIN_PASSPORT_ID, Person.Gender.MAN,
                    "PersonName" + i,
                    "PersonSurname" + i,
                    "PersonMiddleName" + i));
        }
    }

    @After
    public void afterTest() {
        array = null;
    }

    private boolean testSort(PersonArray.SortInterface sortInterface) {
        array.sortArray((p1,p2) -> p1.getPassportID() < p2.getPassportID(),sortInterface);
        boolean temp = false;
        for (int i = 0; i < array.length()-1; i++) {
            if (temp) {
                break;
            }
            temp = array.get(i).getPassportID() < array.get(i+1).getPassportID();
        }
        return temp;
    }

    @Test
    public void sortArrayTest() {
        assertFalse(testSort(new BubbleSort()));
        assertFalse(testSort(new SelectionSort()));
    }

    @Test
    public void getArray() {
        PersonArray temp = array.getArray((p) -> p.getAge() > 12);
        boolean test = true;
        for (int i = 0; i < temp.length(); i++) {
            test = temp.get(i).getAge() > 12;
            if (!test) {
                break;
            }
        }
        assertTrue(test);
    }

    @Test
    public void getTest() {
        Person a = array.get(19);
        Person temp = array.get((p) -> p.getAge() == a.getAge());
        assertEquals(temp.getAge(),a.getAge());
        temp = array.get((p) -> p.equals(a));
        assertEquals(temp,a);
        temp = array.get((p) -> p == a);
        assertSame(temp,a);
        temp = array.get((p) -> p.getAge() > 200);
        assertNull(temp);
    }
}