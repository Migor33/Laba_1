package com.person;

import com.person.personInterface.SortInterface;

import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.repository.IRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Array-based Person list.
 */
public class MyRepository<T> implements IRepository<T> {
    /**
     * Start array.length().
     * Also the value by which the array expands.
     */
    private static final int DEFAULT_LENGTH = 10;
    public static List<IDivision> existDivision = new ArrayList<>();

    private Object[] array;
    Integer head;

    /**
     * Selection Sort
     * using in default sort.
     */
    class SelectionSort<T> implements SortInterface<T> {

        @Override
        public void sort(Comparator<T> comparator, MyRepository<T> myRepository) {
            for (int i = 0; i < myRepository.length() - 1; i++) {
                for (int j = i + 1; j < myRepository.length(); j++) {
                    int compare = comparator.compare(myRepository.get(i), myRepository.get(j));
                    if (compare > 0) {
                        T temp = myRepository.get(i);
                        myRepository.set(i, myRepository.get(j));
                        myRepository.set(j,temp);
                    }
                }
            }
        }

    }


    /**
     * constructor, that create array whit length = DEFAULT_LENGTH.
     */
    public MyRepository() {
        array = new Object[DEFAULT_LENGTH];
        head = -1;
    }

    /**
     * Constructor, that create array whit length = size.
     * @param size length of array.
     */
    public MyRepository(int size) {
        array = new Object[size];
        head = -1;
    }

    /**
     * add one element in list.
     * if the array haven't any free place than expands array by DEFAULT_LENGTH.
     * @param index place for adding
     * @param person element to add.
     */
    public void add(int index, T person) {
        array[index] = person;
    }

    /**
     * add one person in array's end
     * @param a person for adding to array
     */
    @Override
    public void add(T a) {
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
     * @param sort
     */
    public void sortBy(Comparator<T> comparator, SortInterface sort) {
        sort.sort(comparator, this);
    }

    /**
     * default sort by passed comparator.
     * @param comparator
     */
    @Override
    public void sortBy(Comparator<T> comparator) {
        sortBy(comparator,new SelectionSort());
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
        return null;
    }

    /**
     * Search subarray by passed predicate
     * @param personPredicate predicate
     * @return IRepository
     */
    @Override
    public IRepository<T> searchBy(Predicate<T> personPredicate) {
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
    public T get(int index) {
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
    public T delete(int index) {
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
    public T set(int index, T value) {
        Object temp = array[index];
        array[index] = value;
        return (T)temp;
    }

    @Override
    public String toString() {
        return "PersonArray{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
