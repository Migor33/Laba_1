package com.person;

import com.person.personInterface.SortInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Random;

import static org.junit.Assert.*;

public class MyRepositoryTest {

    private static final int N = 20;
    private static final int MIN_YEAR = 1980;
    private static final int MAX_YEAR = 2018;
    private static final int MIN_PASSPORT_ID = 1000;
    private static final int MAX_PASSPORT_ID = 2000;
    private static final int MAX_MONTH = 9;
    private static final int MAX_DAY = 9;
    private MyRepository<IPerson> array;

    class BubbleSort<T> implements SortInterface<T> {

        @Override
        public void sort(Comparator<T> comparator, MyRepository<T> myRepository) {
            for (int i = 0; i < myRepository.length()-1; i++) {
                for (int j = 0; j < myRepository.length()-i-1; j++) {
                    if (comparator.compare(myRepository.get(j), myRepository.get(j+1)) > 0) {
                        T temp = myRepository.get(j);
                        myRepository.set(j, myRepository.get(j+1));
                        myRepository.set(j+1,temp);
                    }
                }
            }
        }

    }


    @Before
    public void initTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Random random = new Random();
        array = new MyRepository<>();
        for (int i = 0; i < N; i++) {
            int year = random.nextInt((MAX_YEAR - MIN_YEAR) + 1) + MIN_YEAR;
            int month = random.nextInt(MAX_MONTH) + 1;
            int day =random.nextInt(MAX_DAY) + 1;
            array.add(new Person(LocalDate.parse("0" + day + "." + "0" + month + '.' + year, formatter),
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
        array.sortBy(Comparator.comparing(IPerson::getAge));
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
        MyRepository<IPerson> temp = (MyRepository<IPerson>) array.searchBy((p) -> p.getAge() > 12);
        boolean test = true;
        for (int i = 0; temp.get(i)!=null; i++) {
            test = temp.get(i).getAge() > 12;
            if (!test) {
                break;
            }
        }
        assertTrue(test);
        Integer k = 0;
        for (int i = 0; i < array.length(); i++) {
            if (array.get(i).getAge()>12) {
                k++;
            }
        }
        assertEquals(k,temp.length());
    }
}