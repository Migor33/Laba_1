package com.person;

import com.person.personInterface.IPerson;
import com.person.personInterface.IRepository;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Array-based Person list.
 */
public class PersonArray implements IRepository {
    /**
     * Start array.length().
     * Also the value by which the array expands.
     */
    private static final int DEFAULT_LENGTH = 10;
    private static Logger log = Logger.getLogger(PersonArray.class.getName());

    private IPerson[] array;
    Integer head;


    /**
     * Interface for sorting
     */
    public interface SortInterface {

        void sort(Comparator<IPerson> comparator, PersonArray personArray);

    }

    /**
     * Selection Sort
     * using in default sort.
     */
    class SelectionSort implements PersonArray.SortInterface {

        @Override
        public void sort(Comparator<IPerson> comparator, PersonArray personArray) {
            for (int i = 0; i < personArray.length() - 1; i++) {
                for (int j = i + 1; j <personArray.length(); j++) {
                    int compare = comparator.compare(personArray.get(i),personArray.get(j));
                    if (compare > 0) {
                        IPerson temp = personArray.get(i);
                        personArray.set(i,personArray.get(j));
                        personArray.set(j,temp);
                    }
                }
            }
        }

    }


    /**
     * Default constructor, that create array whit length = DEFAULT_LENGTH.
     */
    public PersonArray() {
        array = new IPerson[DEFAULT_LENGTH];
        head = -1;
    }

    /**
     * Constructor, that create array whit length = size.
     * @param size length of array.
     */
    public PersonArray(int size) {
        array = new Person[size];
        head = -1;
    }

    /**
     * Constructor, that clone elements of passed array in list.
     * @param array array that need to clone in list.
     */
    public PersonArray(Person[] array) {
        this.array = new Person[array.length];
        for (int i = 0; i < array.length; i++) {
            try {
                this.array[i] = (Person) array[i].clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        head = array.length;
    }

    /**
     * add one element in list.
     * if the array haven't any free place than expands array by DEFAULT_LENGTH.
     * @param index place for adding
     * @param person element to add.
     */
    public void add(int index, IPerson person) {
        try {
            array[index] = person;
        }
        catch (ArrayIndexOutOfBoundsException a) {
            log.info((Supplier<String>) a);
        }
    }

    /**
     * add one person in array's end
     * @param a person for adding to array
     */
    @Override
    public void add(IPerson a) {
        if (++head >= array.length) {
            IPerson[] newArray = new IPerson[array.length + DEFAULT_LENGTH];
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
     * @param sort
     */
    public void sortBy(Comparator<IPerson> comparator, SortInterface sort) {
        sort.sort(comparator, this);
    }

    /**
     * default sort by passed comparator.
     * @param comparator
     */
    @Override
    public void sortBy(Comparator<IPerson> comparator) {
        sortBy(comparator,new SelectionSort());
    }

    /**
     * convert the PersonArray in List
     * @return result list
     */
    @Override
    public List<IPerson> toList() {
        List<IPerson> list = new ArrayList<IPerson>(head+1);
        for (int i = 0; i < head + 1; i++) {
            list.add(array[i]);
        }
        return null;
    }

    /**
     * Search subarray by passed predicate
     * @param personPredicate predicate
     * @return IRepository
     */
    @Override
    public IRepository searchBy(Predicate<IPerson> personPredicate) {
        PersonArray newRepository = new PersonArray();
        for (IPerson temp :
                array) {
            if (personPredicate.test(temp)) {
                newRepository.add(temp);
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
    public IPerson get(int index) {
        IPerson temp = null;
        if (index < array.length) {
            temp = array[index];
        }
        return temp;
    }

    /**
     * @return count of person in array.
     */
    @Override
    public Integer length() {
        return head+1;
    }

    /**
     * delete one element with passed index.
     * Shift left all elements after deleted element
     * @param index удаляемый элемент.
     */
    @Override
    public IPerson delete(int index) {
       IPerson temp = array[index];
        for (int i = index; i < head; i++) {
            array[i]=array[i+1];
        }
        array[head]=null;
        head--;
       return temp;
    }

    /**
     * set array's element.
     * @param index element's index.
     * @param value new element.
     */
    @Override
    public IPerson set(int index, IPerson value) {
        try {
            IPerson temp = array[index];
            array[index] = value;
            return temp;
        } catch (ArrayIndexOutOfBoundsException a) {
            log.info((Supplier<String>) a);
            return null;
        }
    }

    @Override
    public String toString() {
        return "PersonArray{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
