package com.person.tools;

import com.person.MyRepository;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * StreamAPI functions
 */
public class StreamAPI {

    /**
     * get list with persons which have name begins with A.
     * @param repository person repository
     * @return list
     */
    public static List<IPerson> nameAndSalary(final MyRepository<IPerson> repository) {
        BigDecimal salary = new BigDecimal(10000);
        return  repository.toList().stream().filter(
                (a) ->  a.getFirstName().charAt(0) == 'A' &&
                        a.getSalary().compareTo(salary) > 0 &&
                        a.getAge() > 30
                ).collect(Collectors.toList());
    }

    /**
     * get list with persons whith have name with aa.
     * @param repository persons repository
     * @return list
     */
    public static List<IPerson> names(final MyRepository<IPerson> repository) {
        return repository.toList().stream().filter(
                (a) -> a.getFirstName().toUpperCase().contains("AA")
        ).collect(Collectors.toList());
    }

    /**
     * get salary map for divisions
     * @param repository Person repository
     * @return map
     */
    public static Map<IDivision, BigDecimal> divisionSalary(final MyRepository<IPerson> repository) {
        return  repository.toList().stream().collect(Collectors.toMap(IPerson::getDivision,
                                                                      (t) -> t.getSalary(),
                                                                      (first, second) -> first.add(second)));
    }

    /**
     * get map for year of born
     * @param repository Person repository
     * @return map
     */
    public static Map<Integer, List<IPerson>> mapYearOfBorn(final MyRepository<IPerson> repository) {
        return repository.toList().stream().collect(Collectors.groupingBy((p) -> p.getBirthdate().getYear()));
    }
}
