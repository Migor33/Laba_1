package com.person;

import com.person.anotation.Injected;
import com.person.personInterface.SortInterface;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.repository.IRepository;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Array-based Person list.
 * @param <T> type of elements.
 */
@XmlRootElement(name="repository")
@XmlSeeAlso({Person.class})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MyRepository<T> implements IRepository<T> {
    /**
     * Start array.length().
     * Also the value by which the array expands.
     */
    private static final int DEFAULT_LENGTH = 10;
    /**
     * Divisions List.
     */
    public static List<IDivision> existDivision = new ArrayList<>();

    private Object[] getArray() {
        return array;
    }

    @XmlElement(name="person")
    private void setArray(Object[] array) {
        this.array = array;
        head = array.length - 1;
    }

    /**
     * default sort.
     */
    @Injected
    private SortInterface<T> sort;

    /**
     * array of elements.
     */
    private Object[] array;
    /**
     * last element.
     */
    private Integer head;


    /**
     * constructor, that create array whit length = DEFAULT_LENGTH.
     */
    public MyRepository() {
        array = new Object[DEFAULT_LENGTH];
        head=-1;
    }

    /**
     * Constructor, that create array whit length = size.
     * @param size length of array.
     */
    public MyRepository(final int size) {
        array = new Object[size];
        head = -1;
    }

    /**
     * add one element in list.
     * if the array haven't any free place than expands array by DEFAULT_LENGTH.
     * @param index place for adding
     * @param person element to add.
     */
    public void add(final int index,final T person) {
        array[index] = person;
    }

    /**
     * add one person in array's end
     * @param a person for adding to array
     */
    @Override
    public void add(final T a) {
        if (++head >= array.length) {
            Object[] newArray = new Object[array.length + DEFAULT_LENGTH];
            System.arraycopy(array, 0, newArray, 0, head);
            newArray[head] = a;
            array = newArray;
        } else {
            array[head] = a;
        }
    }


    /**
     * Sort array by passed sort and comparator.
     * @param comparator
     */
    public void sortBy(final Comparator<T> comparator) {
        sort.sort(comparator, this);
    }


    /**
     * convert the PersonArray in List
     * @return result list
     */
    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>(head+1);
        for (int i = 0; i < head + 1; i++) {
            list.add((T)array[i]);
        }
        return list;
    }

    /**
     * Search subarray by passed predicate
     * @param personPredicate predicate
     * @return IRepository
     */
    @Override
    public IRepository<T> searchBy(final Predicate<T> personPredicate) {
        MyRepository newRepository = new MyRepository();
        for (Object temp :
                array) {
            if (personPredicate.test((T)temp)) {
                newRepository.add((T)temp);
            }
        }
        return newRepository;
    }


    /**
     * return element with passed index .
     * @param index element's index.
     * @return element of list.
     */
    @Override
    public T get(final int index) {
        Object temp = null;
        if (index < array.length) {
            temp = array[index];
        }
        return (T)temp;
    }

    /**
     * @return count of person in array.
     */
    public Integer length() {
        return head+1;
    }

    /**
     * delete one element with passed index.
     * Shift left all elements after deleted element
     * @param index удаляемый элемент.
     */
    @Override
    public T delete(final int index) {
       Object temp = array[index];
        for (int i = index; i < head; i++) {
            array[i]=array[i+1];
        }
        array[head]=null;
        head--;
       return (T)temp;
    }

    /**
     * set array's element.
     * @param index element's index.
     * @param value new element.
     */
    @Override
    public T set(final int index,final T value) {
        Object temp = array[index];
        array[index] = value;
        return (T)temp;
    }
}
