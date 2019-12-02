package com.person.tools;

import com.person.Person;
import com.person.MyRepository;
import com.person.division.Division;

import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.factory.ILabFactory;
import ru.vsu.lab.repository.IPersonRepository;
import ru.vsu.lab.repository.IRepository;


public class PersonFactory implements ILabFactory {

    /**
     * create person
     * @return person
     */
    @Override
    public IPerson createPerson() {
        return new Person();
    }

    /**
     * create division
     * @return division
     */
    @Override
    public IDivision createDivision() {
        return new Division();
    }

    /**
     * create repository
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> IRepository<T> createRepository(Class<T> clazz) {
        return new MyRepository<T>();
    }

    @Override
    public IPersonRepository createPersonRepository() {
        return null;
    }
}
