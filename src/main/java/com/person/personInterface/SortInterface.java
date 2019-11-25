package com.person.personInterface;

import com.person.MyRepository;

import java.util.Comparator;

public interface SortInterface<T> {

    void sort(Comparator<T> comparator, MyRepository<T> myRepository);

}
