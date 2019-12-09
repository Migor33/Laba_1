package com.person.tools;

import com.person.Person;
import com.person.MyRepository;
import com.person.division.Division;

import com.person.exceptions.InjectFailedException;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.factory.ILabFactory;
import ru.vsu.lab.repository.IPersonRepository;
import ru.vsu.lab.repository.IRepository;

/**
 * person factory.
 */
public class PersonFactory implements ILabFactory {

    /**
     * create person.
     * @return person.
     */
    @Override
    public final IPerson createPerson() {
        return new Person();
    }

    /**
     * create division.
     * @return division.
     */
    @Override
    public final IDivision createDivision() {
        return new Division();
    }

    /**
     * create repository.
     * @param clazz class of elements.
     * @param <T> type of elements.
     * @return repository.
     */
    @Override
    public final <T> IRepository<T> createRepository(final Class<T> clazz) {
        try {
            return Injector.inject(new MyRepository<>());
        } catch (InjectFailedException e) {
            e.printStackTrace();
        }
        return new MyRepository<>();
    }

    /**
     * do nothing.
     * @return null.
     */
    @Override
    public final IPersonRepository createPersonRepository() {
        return null;
    }
}
