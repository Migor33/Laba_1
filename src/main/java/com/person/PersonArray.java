package com.person;

import java.util.Arrays;

/**
 * Класс, реализующий список Person на основе мессива.
 */
public class PersonArray {
    private Person[] mass;
    private int head;
    /**
     * Начальная длинна массива по умолчанию.
     * Так же значение, на которое расширяется массив.
     */
    private static final int DEFAULT_LENGTH = 10;

    /**
     * Конструктор по умолчанию, выделяет 10 мест для людей.
     */
    public PersonArray() {
        mass = new Person[DEFAULT_LENGTH];
        head = -1;
    }

    /**
     * Конструктор, выделяющий определенное количество ест под людей.
     * @param size колличество мест.
     */
    public PersonArray(int size) {
        mass = new Person[size];
        head = -1;
    }

    /**
     * Конструктор, копирующий переданный массив, ко все элементам приминеется метод .clone().
     * @param mass исходный массив.
     */
    public PersonArray(Person[] mass) {
        this.mass = new Person[mass.length];
        for (int i = 0; i < mass.length; i++) {
            try {
                this.mass[i] = (Person) mass[i].clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        head = mass.length;
    }

    /**
     * добавлние одного элемента на мервое свободное место, элемент кладется по ссылке.
     * В случае, если места нет, массив удлиняется на 10.
     * @param a элемент, который необходимо добавить.
     */
    public void add(Person a) {
        if (++head >= mass.length) {
            Person[] newMass = new Person[mass.length + DEFAULT_LENGTH];
            System.arraycopy(mass, 0, newMass, 0, head);
            newMass[head] = a;
            mass = newMass;
        } else {
            mass[head] = a;
        }
    }

    /**
     * Добавление массива элементов втконец текущего.
     * Если места не хватает, выделяется ровно столько меест, сколько нужно, чтобы хранить итоговый массив.
     * @param m массив, который необходимо добавить.
     */
    public void add(Person[] m) {
        if (head + m.length > mass.length) {
            Person[] newMass = new Person[m.length + head + 1];
            System.arraycopy(mass, 0, newMass, 0, head + 1);
            for (int i = mass.length, j = 0; j < m.length; i++, j++) {
                newMass[i] = m[j];
            }
            mass = newMass;
        } else {
            for (int i = head + 1, j = 0; j < m.length; i++, j++) {
                mass[i] = m[j];
            }
        }
        head += m.length;
    }

    /**
     * Возврашает элемент массива.
     * @param index номер нужного элемента.
     * @return элемент с заданным индексом.
     */
    public Person get(int index) {
        return mass[index];
    }

    /**
     * @return колличество элементов в массиве в данный момент.
     */
    public int length() {
        return head + 1;
    }

    /**
     * @return текущее количество мест в массиве.
     */
    public int massSize() {
        return mass.length;
    }

    /**
     * удаляет один элемент, сдвигая все остальные влево.
     * @param index удаляемый элемент.
     */
    public void delete(int index) {
        this.delete(index, 1);
    }

    /**
     * удаляет несколько элементов, сдвигая все элементы влево.
     * @param index номер, с колторого начать удалять.
     * @param count колличество элементов, которые надо удалить.
     */
    public void delete(int index, int count) {
        int end;
        if (head - count > index) {
            System.arraycopy(mass, index+count, mass, index, head - count - index + 1);
            head -= count;
        } else {
            head = index;
        }
        for (int i = head + 1; i < mass.length; i++) {
            mass[i] = null;
        }
    }

    /**
     * заменяет элемент массива.
     * @param index идекс элемента.
     * @param value новый элемент.
     */
    public void set(int index, Person value) {
        if (index <= head) {
            mass[index] = value;
        }
    }

    /**
     * обрезает все свободные места.
     * Всегда оставляет хотябы 10 мест.
     */
    public void cutSize() {
        Person[] newMass;
        if (head < DEFAULT_LENGTH) {
            newMass = new Person[DEFAULT_LENGTH];
        } else {
            newMass = new Person[head + 1];
        }
        System.arraycopy(mass, 0, newMass, 0, head + 1);
        mass = newMass;
    }

    @Override
    public String toString() {
        return "PersonArray{" +
                "mass=" + Arrays.toString(mass) +
                '}';
    }
}
