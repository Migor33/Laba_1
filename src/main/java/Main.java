import com.person.Person;
import com.person.PersonArray;
import org.joda.time.LocalDate;

import java.util.Random;

/**
 * Class for showing how PersonArray work
 */
public class Main {
    public static void main(String[] args) {
        int n = 20;
        int k=5;
        int minYear = 1980;
        int maxYear = 2018;
        int minPassID = 1000;
        int maxPassID = 2000;
        int maxMonth = 12;
        int maxDay = 28;
        Random random = new Random();
        PersonArray array = new PersonArray();
        for (int i = 0; i < n; i++) {
            array.add(new Person(new LocalDate(random.nextInt((maxYear - minYear) + 1) + minYear, random.nextInt(maxMonth) + 1, random.nextInt(maxDay)),
                    random.nextInt(maxPassID - minPassID) + minPassID, Person.Gender.MAN,
                    "PersonName" + i,
                    "PersonSurname" + i,
                    "PersonMiddleName" + i));
        }
        System.out.println(array);
            array.delete(k, n / 2);
        System.out.println(array);
        array.cutSize();
        System.out.println(array);
        for (int i = 0; i < array.length(); i++) {
            System.out.println(array.get(i).getAge());
        }
    }
}
