package com.person;

import com.person.enums.Gender;
import com.person.enums.ReaderStage;
import com.person.personInterface.IDivision;
import com.person.personInterface.IRepository;
import com.person.personInterface.IRepositoryReader;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonArrayReader implements IRepositoryReader {

    private static List<ReaderStage> readerStage = new ArrayList();

    /**
     * read PersonArray from scanner
     * @param scanner scanner
     * @return IRepository
     */
    @Override
    public IRepository readRepository(Scanner scanner) {
        IRepository personArray = new PersonArray();
        Scanner tempScanner = new Scanner(scanner.nextLine());
        tempScanner.useDelimiter(";");
        for (int i = 0;tempScanner.hasNext();i++) {
            String temp = tempScanner.next();
            if (temp.equals("id")) {
                readerStage.add(ReaderStage.ID);
            } else if (temp.equals("name")) {
                readerStage.add(ReaderStage.NAME);
            } else if (temp.equals("gender")) {
                readerStage.add(ReaderStage.GENDER);
            } else if (temp.equals("BirtDate")) {
                readerStage.add(ReaderStage.BIRT_DATE);
            } else if (temp.equals("Division")) {
                readerStage.add(ReaderStage.DIVISION);
            } else if (temp.equals("Salary")) {
                readerStage.add(ReaderStage.SALARY);
            } else readerStage.add(null);
        }
        while(scanner.hasNext()) {
            tempScanner = new Scanner(scanner.nextLine());
            tempScanner.useDelimiter("[;]");
            Person person = new Person();
            for (int i = 0;tempScanner.hasNext(); i++) {
                switch (readerStage.get(i)) {
                    case ID:
                        person.setId(tempScanner.nextInt());
                        break;
                    case NAME:
                        person.setFirstName(tempScanner.next());
                        break;
                        case GENDER:
                        String temp = tempScanner.next();
                        if (temp.equals("Male")) {
                            person.setGender(Gender.MALE);
                        } else if (temp.equals("Female")) {
                            person.setGender(Gender.FEMALE);
                        }
                        break;
                    case SALARY:
                        person.setSalary(tempScanner.nextBigDecimal());
                        break;
                    case DIVISION:
                        person.setDivision(readDivision(tempScanner.next()));
                        break;
                    case BIRT_DATE:
                        tempScanner.useDelimiter("[;.]");
                        int day = tempScanner.nextInt();
                        int month = tempScanner.nextInt();
                        int year = tempScanner.nextInt();
                        tempScanner.useDelimiter("[;]");
                        person.setBirthdate(new LocalDate(year,month,day));
                        break;
                    default:
                        tempScanner.next();
                        break;
                }
            }
            personArray.add(person);
        }
        return personArray;
    }

    /**
     * search exist division with passed name.
     * if division don't exist, create new.
     * @param a name of division.
     * @return division with this name.
     */
    IDivision readDivision(String a) {
        for (int i = 0; i < Division.exsistDivision.size(); i++) {
            IDivision temp = Division.exsistDivision.get(i);
            if (a.equals(temp.getName())) {
                return temp;
            }
        }
        return new Division(a);
    }
}
