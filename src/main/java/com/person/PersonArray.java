package com.person;

/**
 * Класс, реализующий список Person на основе мессива.
 */
public class PersonArray {
    private Person[] mass;
    private int head;

    /**
     * Конструктор по умолчанию, выделяет 10 мест для людей.
     */
    PersonArray() {
        mass = new Person[10];
        head = -1;
    }

    /**
     * Конструктор, выделяющий определенное количество ест под людей.
     * @param size колличество мест.
     */
    PersonArray(int size) {
        mass = new Person[size];
        head = -1;
    }

    /**
     * Конструктор, копирующий переданный массив, ко все элементам приминеется метод .clone().
     * @param mass исходный массив.
     */
    PersonArray(Person[] mass) {
        this.mass = new Person[mass.length];
        for (int i = 0; i < mass.length; i++) {
            try {
                this.mass[i]= (Person) mass[i].clone();
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
        if (++head>=mass.length) {
            Person[] newmass = new Person[mass.length+10];
            for (int i = 0; i < mass.length; i++) {
                newmass[i] = mass[i];
            }
            newmass[head] = a;
            mass = newmass;
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
            Person[] newmass = new Person[m.length+head + 1];
            for (int i = 0; i < mass.length; i++) {
                newmass[i] = mass[i];
            }
            for (int i = mass.length, j = 0; j < m.length; i++, j++) {
                newmass[i] = m[j];
            }
            mass = newmass;
        } else {
            for (int i = head+1, j = 0; j < m.length; i++, j++) {
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
        return head +1;
    }

    /**
     * текущее количество мест в массиве.
     * @return
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
            for (int i = index; i <= head - count; i++) {
                mass[i]=mass[i+count];
            }
            head-=count;
        } else {
            head = index;
        }
    }

    /**
     * заменяет элемент массива.
     * @param index идекс элемента.
     * @param value новый элемент.
     */
    public void set(int index, Person value) {
        if (index<=head) {
            mass[index] = value;
        }
    }

    /**
     * обрезает все свободные места.
     */
    public void cutSize() {
        Person[] newMass;
        if (head < 10) {
            newMass = new Person[10];
        } else {
            newMass = new Person[head+1];
        }
        for (int i = 0; i < newMass.length; i++) {
            newMass[i] = mass[i];
        }
        mass = newMass;
    }
}