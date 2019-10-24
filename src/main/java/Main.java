import com.person.*;
import org.joda.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        PersonArray mass = new PersonArray();
        for (int i = 0; i < 20; i++) {
            mass.add(new Person(new LocalDate(1989+i,11,1),1000+i, Person.Gender.MAN,"PersonName" +i,"PersonSurname"+i,"PersonMiddleName" + i));
        }
        System.out.println(mass);
            mass.delete(5,10);
        System.out.println(mass);
        mass.cutSize();
        System.out.println(mass);
        for (int i = 0; i < mass.length(); i++) {
            System.out.println(mass.get(i).getAge());
        }
    }
}
