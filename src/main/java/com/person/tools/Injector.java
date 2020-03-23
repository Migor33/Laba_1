package com.person.tools;

import com.person.anotation.Injected;
import com.person.exceptions.InjectFailedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Injector.
 */
public class Injector {

    public static final Logger log = Logger.getLogger(Injector.class.getName());

    /**
     * inject repository fields.
     * @param repository repository.
     * @param <T> type of elements.
     * @return repository
     * @throws InjectFailedException exceptions.
     */
    public static <T> T inject(T repository) throws InjectFailedException {
        Field[] allFields = repository.getClass().getDeclaredFields();
        List<Field> reflectedFields = new ArrayList<>();
        File file = new File("src\\main\\resources\\properties");
        for (Field field:
             allFields) {
            if  (field.isAnnotationPresent(Injected.class))
                reflectedFields.add(field);
        }
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException a) {
            throw new InjectFailedException(a);
        }
        scanner.useDelimiter(" = |\n");
        while(scanner.hasNext()) {
            String fieldName = scanner.next();
            String className = scanner.next();
            for (int i = 0; i < reflectedFields.size(); i++) {
                Field field = reflectedFields.get(i);
                if (field.getName().equals(fieldName)) {
                    field.setAccessible(true);
                    try {
                        field.set(repository, Class.forName(className).newInstance());
                    } catch (ClassNotFoundException a) {
                        throw new InjectFailedException(a);
                    } catch (IllegalAccessException a) {
                        throw new InjectFailedException(a);
                    } catch (InstantiationException a) {
                        throw new InjectFailedException(a);
                    }
                    break;
                }
            }
        }
        scanner.close();
        log.fine("Repository successfully injected");
        return repository;
    }
}
