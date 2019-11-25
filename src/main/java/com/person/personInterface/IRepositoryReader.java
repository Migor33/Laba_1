package com.person.personInterface;

import ru.vsu.lab.factory.ILabFactory;
import ru.vsu.lab.repository.IRepository;

import java.util.Scanner;

public interface IRepositoryReader {
    public IRepository readRepository(Scanner scanner, ILabFactory factory);
}
