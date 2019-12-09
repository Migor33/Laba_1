package com.person.personInterface;

import com.person.MyRepository;

import java.util.Comparator;

/**
 * sort interface.
 * @param <T> type of elements.
 */
public interface SortInterface<T> {

    /**
     * sort repository.
     * @param comparator comparator.
     * @param myRepository repository.
     */
    void sort(final Comparator<T> comparator, MyRepository<T> myRepository);

}
