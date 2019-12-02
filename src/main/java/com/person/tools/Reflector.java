package com.person.tools;

import com.person.MyRepository;
import com.person.anotation.Reflected;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reflector {
    public static MyRepository reflect(MyRepository repository) {
        Field[] allFields = repository.getClass().getDeclaredFields();
        List<Field> reflectedFields = new ArrayList<>();
        File file = new File("src\\main\\resources\\properties");
        for (Field field:
             allFields) {
            if  (field.getAnnotation(Reflected.class)!=null)
                reflectedFields.add(field);
        }
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException a) {
            System.out.println(a.getClass().getName());
            return repository;
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
                        System.out.println(a.getClass().getName());
                    } catch (IllegalAccessException a) {
                        System.out.println(a.getClass().getName());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        scanner.close();
        return repository;
    }
}
