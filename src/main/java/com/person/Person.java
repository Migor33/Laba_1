package com.person;

import com.person.enums.Gender;
import com.person.personInterface.IDivision;
import com.person.personInterface.IPerson;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Class for storing information about human.
 */
public class Person implements Cloneable, IPerson {


    private LocalDate birthdate;
    private Integer id;
    private Gender gender;
    private String firstName;
    private String lastName;
    private String middleName;
    private IDivision division;
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
    public Person(LocalDate birthdate, int id, Gender gender, String firstName, String lastName, String middleName, IDivision division, BigDecimal salary) {
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
    Person() {
    }

    /**
     *
     * @return person's salary.
     */
    @Override
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     *
     * @param salary new salary.
     */
    @Override
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     *
     * @return person's division.
     */
    @Override
    public IDivision getDivision() {
        return division;
    }

    /**
     *
     * @param division new division.
     */
    @Override
    public void setDivision(IDivision division) {
        this.division = division;
    }

    /**
     *
     * @return person's birth date.
     */
    @Override
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * Change person's day of born.
     * @param birthdate new day of born.
     */
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Change passport id.
     * @param id new passport id.
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *  searchBy passport id
     * @return id.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Change person's gender.
     * @param gender новый пол.
     */
    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Change person's firstName.
     * @param name new firstName.
     */
    @Override
    public void setFirstName(String name) {
        this.firstName = name;
    }

    /**
     * Change person's lastName.
     * @param surname new lastName.
     */
    @Override
    public void setLastName(String surname) {
        this.lastName = surname;
    }

    /**
     * Change person's middleName.
     * @param middleName new middleName.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * searchBy person's gander in Person.Gender type.
     * @return gender.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * searchBy person's firstName in String type.
     * @return firstName.
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * searchBy person's lastName in String type.
     * @return lastName.
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * searchBy person's middle firstName in String type..
     * @return middleName.
     */
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                birthdate.equals(person.birthdate) &&
                gender == person.gender &&
                firstName.equals(person.firstName) &&
                lastName.equals(person.lastName) &&
                middleName.equals(person.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthdate, id, gender, firstName, lastName, middleName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "birthdate=" + birthdate +
                ", id=" + id +
                ", gender=" + gender +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }

    /**
     * searchBy person's Age.
     * @return age in int type
     */
    @Override
    public Integer getAge() {
       return Years.yearsBetween(birthdate, new LocalDate()).getYears();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
