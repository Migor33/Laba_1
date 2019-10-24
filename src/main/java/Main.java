import com.person.Person;
import com.person.PersonArray;
import org.joda.time.LocalDate;

import java.util.Random;

/**
 * Класс, для димонстрации функционала PersonArray.
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
        PersonArray mass = new PersonArray();
        for (int i = 0; i < n; i++) {
            mass.add(new Person(new LocalDate(random.nextInt((maxYear - minYear) + 1) + minYear, random.nextInt(maxMonth) + 1, random.nextInt(maxDay)),
                    random.nextInt(maxPassID - minPassID) + minPassID, Person.Gender.MAN,
                    "PersonName" + i,
                    "PersonSurname" + i,
                    "PersonMiddleName" + i));
        }
        System.out.println(mass);
            mass.delete(k, n / 2);
        System.out.println(mass);
        mass.cutSize();
        System.out.println(mass);
        for (int i = 0; i < mass.length(); i++) {
            System.out.println(mass.get(i).getAge());
        }
    }
}
