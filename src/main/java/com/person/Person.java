package com.person;

import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

/**
 * Class for storing information about human.
 */
public class Person implements IPerson {

    /**
     * person birth date.
     */
    private LocalDate birthdate;
    /**
     * person id.
     */
    private Integer id;
    /**
     * person gender.
     */
    private Gender gender;
    /**
     * person first name.
     */
    private String firstName;
    /**
     * person last name.
     */
    private String lastName;
    /**
     * person middle name.
     */
    private String middleName;
    /**
     * person division.
     */
    private IDivision division;
    /**
     * person salary.
     */
    private BigDecimal salary;


    /**
     * @param birthdate person's day of born.
     * @param id person's passport id.
     * @param gender person's gender.
     * @param firstName person's firstName.
     * @param lastName person's lastName.
     * @param middleName person's middle firstName.
     * @param division division.
     * @param salary salary.
     */
    public Person(final LocalDate birthdate,
                  final int id,
                  final Gender gender,
                  final String firstName,
                  final String lastName,
                  final String middleName,
                  final IDivision division,
                  final BigDecimal salary) {
        this.birthdate = birthdate;
        this.id = id;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.division = division;
        this.salary = salary;
    }

    /**
     * Constructor for PersonArrayReader.
     * can't be used outside the package.
     */
    public Person() {
    }

    /**
     *
     * @return person's salary.
     */
    @Override
    public final BigDecimal getSalary() {
        return salary;
    }

    /**
     *
     * @param salary new salary.
     */
    @Override
    public final void setSalary(final BigDecimal salary) {
        this.salary = salary;
    }

    /**
     *
     * @return person's division.
     */
    @Override
    public final IDivision getDivision() {
        return division;
    }

    /**
     *
     * @param division new division.
     */
    @Override
    public final void setDivision(final IDivision division) {
        this.division = division;
    }

    /**
     * get person's birth date.
     * @return person's birth date.
     */
    @Override
    public final LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * Change person's day of born.
     * @param birthdate new day of born.
     */
    public final void setBirthdate(final LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Change passport id.
     * @param id new passport id.
     */
    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    /**
     * get passport id.
     * @return id.
     */
    @Override
    public final Integer getId() {
        return id;
    }

    /**
     * Change person's gender.
     * @param gender новый пол.
     */
    public final void setGender(final Gender gender) {
        this.gender = gender;
    }

    /**
     * Change person's firstName.
     * @param name new firstName.
     */
    @Override
    public final void setFirstName(final String name) {
        this.firstName = name;
    }

    /**
     * Change person's lastName.
     * @param surname new lastName.
     */
    @Override
    public final void setLastName(final String surname) {
        this.lastName = surname;
    }

    /**
     * Change person's middleName.
     * @param middleName new middleName.
     */
    public final void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    /**
     * get person's gander in Person.Gender type.
     * @return gender.
     */
    public final Gender getGender() {
        return gender;
    }

    /**
     * get person's firstName in String type.
     * @return firstName.
     */
    @Override
    public final String getFirstName() {
        return firstName;
    }

    /**
     * get person's lastName in String type.
     * @return lastName.
     */
    @Override
    public final String getLastName() {
        return lastName;
    }

    /**
     * get person's middle firstName in String type..
     * @return middleName.
     */
    public final String getMiddleName() {
        return middleName;
    }

    /**
     * aet person's Age.
     * @return age in int type
     */
    @Override
    public final Integer getAge() {
        Period period = Period.between(birthdate, LocalDate.now());
       return period.getYears();
    }
}
