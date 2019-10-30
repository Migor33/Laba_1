import com.person.Person;
import com.person.PersonArray;
import org.joda.time.LocalDate;

import java.util.Random;

/**
 * Class for showing how PersonArray works.
 */
public class Main {
    private static final int N = 20;
    private static final int K =5;
    private static final int MIN_YEAR = 1980;
    private static final int MAX_YEAR = 2018;
    private static final int MIN_PASSPORT_ID = 1000;
    private static final int MAX_PASSPORT_ID = 2000;
    private static final int MAX_MONTH = 12;
    private static final int MAX_DAY = 28;

    static class SelectionSort implements PersonArray.SortInterface {

        @Override
        public void sort(PersonArray.SortCompere compare, PersonArray personArray) {
            for (int i = 0; i < personArray.length()-1; i++) {
                for (int j = i+1; j < personArray.length(); j++) {
                    if (compare.compare(personArray.get(i),personArray.get(i+1))) {
                        Person temp = personArray.get(i);
                        personArray.set(i,personArray.get(i+1));
                        personArray.set(i+1,temp);
                    }
                }
            }
        }
    }

    static class BubbleSort implements PersonArray.SortInterface {

        @Override
        public void sort(PersonArray.SortCompere compare, PersonArray personArray) {
            for (int i = personArray.length() - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (compare.compare(personArray.get(i),personArray.get(j))) {
                        Person temp = personArray.get(i);
                        personArray.set(i,personArray.get(j));
                        personArray.set(j,temp);
                    }
                }
            }
        }
    }

    /**
     * Class for showing how PersonArray works.
     * @param args don't used.
     */
    public static void main(String[] args) {
        Random random = new Random();
        PersonArray array = new PersonArray();
        for (int i = 0; i < N; i++) {
            array.add(new Person(new LocalDate(random.nextInt((MAX_YEAR - MIN_YEAR) + 1) + MIN_YEAR,
                    random.nextInt(MAX_MONTH) + 1,
                    random.nextInt(MAX_DAY) + 1),
                    random.nextInt(MAX_PASSPORT_ID - MIN_PASSPORT_ID) + MIN_PASSPORT_ID, Person.Gender.MAN,
                    "PersonName" + i,
                    "PersonSurname" + i,
                    "PersonMiddleName" + i));
        }
        System.out.println(array);
        //array.sortArray(((p1, p2) -> p1.getPassportID() < p2.getPassportID()), new SelectionSort());
        array.sortArray(((p1, p2) -> p1.getPassportID() < p2.getPassportID()), new BubbleSort());
        System.out.println(array);
    }
}
