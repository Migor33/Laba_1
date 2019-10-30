package com.person;

import java.util.Arrays;

/**
 * Array-based Person list.
 */
public class PersonArray {
    private Person[] array;
    private int head;

    public interface SortCompere {
        boolean compare(Person p1,Person p2);
    }

    public interface SortInterface {
        void sort(SortCompere compare, PersonArray personArray);
    }

    public void sortArray(SortCompere compere, SortInterface sort) {
        sort.sort(compere, this);
    }

    /**
     * Start array.length().
     * Also the value by which the array expands.
     */
    private static final int DEFAULT_LENGTH = 10;

    /**
     * Default constructor, that create array whit length = DEFAULT_LENGTH.
     */
    public PersonArray() {
        array = new Person[DEFAULT_LENGTH];
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
     * add one element in end of list.
     * if the array haven't any free place than expands array by DEFAULT_LENGTH.
     * @param a element to add.
     */
    public void add(Person a) {
        if (++head >= array.length) {
            Person[] newArray = new Person[array.length + DEFAULT_LENGTH];
            System.arraycopy(array, 0, newArray, 0, head);
            newArray[head] = a;
            array = newArray;
        } else {
            array[head] = a;
        }
    }

    /**
     * add an array of Persons in end of the list.
     * if the array haven't any free place than expands by the required places.
     * @param array array to add.
     */
    public void add(Person[] array) {
        if (head + array.length > this.array.length) {
            Person[] newArray = new Person[array.length + head + 1];
            System.arraycopy(this.array, 0, newArray, 0, head + 1);
            for (int i = this.array.length, j = 0; j < array.length; i++, j++) {
                newArray[i] = array[j];
            }
            this.array = newArray;
        } else {
            for (int i = head + 1, j = 0; j < array.length; i++, j++) {
                this.array[i] = array[j];
            }
        }
        head += array.length;
    }

    /**
     * return element with passed index .
     * @param index element's index.
     * @return element of list.
     */
    public Person get(int index) {
        return array[index];
    }

    /**
     * @return count of elements in array.
     */
    public int length() {
        return head + 1;
    }

    /**
     * @return count og places in array.
     */
    public int arraySize() {
        return array.length;
    }

    /**
     * delete one element with passed index.
     * Shift left all elements after deleted element
     * @param index удаляемый элемент.
     */
    public void delete(int index) {
        this.delete(index, 1);
    }

    /**
     * delete several elements.
     * Shift left all elements after deleted elements.
     * @param index first element for deleting.
     * @param count count elements, that need to delete.
     */
    public void delete(int index, int count) {
        int end;
        if (head - count > index) {
            System.arraycopy(array, index+count, array, index, head - count - index + 1);
            head -= count;
        } else {
            head = index;
        }
        for (int i = head + 1; i < array.length; i++) {
            array[i] = null;
        }
    }

    /**
     * set array's element.
     * @param index element's index.
     * @param value new element.
     */
    public void set(int index, Person value) {
        if (index <= head) {
            array[index] = value;
        }
    }

    /**
     * cut all free places.
     * always let DEFAULT_LENGTH places.
     */
    public void cutSize() {
        Person[] newArray;
        if (head < DEFAULT_LENGTH) {
            newArray = new Person[DEFAULT_LENGTH];
        } else {
            newArray = new Person[head + 1];
        }
        System.arraycopy(array, 0, newArray, 0, head + 1);
        array = newArray;
    }

    @Override
    public String toString() {
        return "PersonArray{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
