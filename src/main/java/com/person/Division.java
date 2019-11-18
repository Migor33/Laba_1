package com.person;

import com.person.personInterface.IDivision;

import java.util.ArrayList;
import java.util.List;

public class Division  implements IDivision {

    Integer id;
    String name;
    static Integer lastId = -1;
    static List<IDivision> exsistDivision = new ArrayList<>();

    /**
     * Constructor assigning id for a division.
     * @param name name of division.
     */
    public Division(String name) {
        this.id = ++lastId;
        this.name = name;
        exsistDivision.add(this);
    }

    /**
     * @return Division's id
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id new id
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return Division's name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @param name new name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }
}
