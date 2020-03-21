package com.person.tools;

import com.person.MyRepository;
import com.person.Person;
import com.person.enums.ReaderStage;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;
import ru.vsu.lab.factory.ILabFactory;
import ru.vsu.lab.repository.IRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Repository reader.
 */
public class MyRepositoryReader {

    /**
     * list of stages.
     */
    private static List<ReaderStage> readerStage = new ArrayList();

    /**
     * read PersonArray from scanner
     * scanner will be closed.
     * @param scanner scanner.
     * @param factory factory.
     * @return IRepository
     */
    public final IRepository readRepository(final Scanner scanner,
                                      final ILabFactory factory) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        IRepository personArray = factory.createRepository(Person.class);
        Scanner tempScanner = new Scanner(scanner.nextLine());
        tempScanner.useDelimiter(";");
        for (int i = 0; tempScanner.hasNext(); i++) {
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
            } else {
                readerStage.add(null);
            }
        }
        while (scanner.hasNext()) {
            tempScanner.close();
            tempScanner = new Scanner(scanner.nextLine());
            tempScanner.useDelimiter(";");
            IPerson person = factory.createPerson();
            for (int i = 0; tempScanner.hasNext(); i++) {
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
                        person.setDivision(readDivision(tempScanner.next(),
                                                        factory));
                        break;
                    case BIRT_DATE:
                        person.setBirthdate(LocalDate.parse(tempScanner.next(),
                                                            formatter));
                        break;
                    default:
                        tempScanner.next();
                        break;
                }
            }
            personArray.add(person);
        }
        tempScanner.close();
        scanner.close();
        return personArray;
    }

    /**
     * search exist division with passed name.
     * if division don't exist, create new.
     * @param a name of division.
     * @param factory factory.
     * @return division with this name.
     */
    public static IDivision readDivision(final String a, final ILabFactory factory) {
        for (int i = 0; i < MyRepository.existDivision.size(); i++) {
            IDivision temp = MyRepository.existDivision.get(i);
            if (a.equals(temp.getName())) {
                return temp;
            }
        }
        IDivision division = factory.createDivision();
        division.setName(a);
        division.setId(MyRepository.existDivision.size());
        MyRepository.existDivision.add(division);
        return division;
    }
}
