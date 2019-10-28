import com.person.Person;
import com.person.PersonArray;
import org.joda.time.LocalDate;

import java.util.Random;

/**
 * Class for showing how PersonArray work
 */
public class Main {
    private static int N = 20;
    private static int K =5;
    private static int MIN_YEAR = 1980;
    private static int MAX_YEAR = 2018;
    private static int MIN_PASSPORT_ID = 1000;
    private static int MAX_PASSPORT_ID = 2000;
    private static int MAX_MONTH = 12;
    private static int MAX_DAY = 28;

    public static void main(String[] args) {
        Random random = new Random();
        PersonArray array = new PersonArray();
        for (int i = 0; i < N; i++) {
            array.add(new Person(new LocalDate(random.nextInt((MAX_YEAR - MIN_YEAR) + 1) + MIN_YEAR,
                    random.nextInt(MAX_MONTH) + 1,
                    random.nextInt(MAX_DAY)),
                    random.nextInt(MAX_PASSPORT_ID - MIN_PASSPORT_ID) + MIN_PASSPORT_ID, Person.Gender.MAN,
                    "PersonName" + i,
                    "PersonSurname" + i,
                    "PersonMiddleName" + i));
        }
        System.out.println(array);
            array.delete(K, N / 2);
        System.out.println(array);
        array.cutSize();
        System.out.println(array);
        for (int i = 0; i < array.length(); i++) {
            System.out.println(array.get(i).getAge());
        }
    }
}
