package com.person.division;

import ru.vsu.lab.entities.IDivision;

/**
 * preson Division.
 */
public class Division  implements IDivision {
    /**
     * division id.
     */
    private Integer id;
    /**
     * division name.
     */
    private String name;

    /**
     * Constructor assigning id for a division.
     * @param name name of division.
     * @param id division id.
     */
    public Division(final String name, final Integer id) {
        this.id = id;
        this.name = name;
    }

    /**
     * default constructor.
     */
    public Division() {
    }

    /**
     * @return Division's id
     */
    @Override
    public final Integer getId() {
        return id;
    }

    /**
     *
     * @param id new id
     */
    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    /**
     *
     * @return Division's name
     */
    @Override
    public final String getName() {
        return name;
    }

    /**
     *
     * @param name new name
     */
    @Override
    public final void setName(final String name) {
        this.name = name;
    }
}
