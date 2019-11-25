package com.person.division;

import ru.vsu.lab.entities.IDivision;

public class Division  implements IDivision {

    Integer id;
    String name;

    /**
     * Constructor assigning id for a division.
     * @param name name of division.
     */
    public Division(String name, Integer id) {
        this.id = id;
        this.name = name;
    }

    public Division() {
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
