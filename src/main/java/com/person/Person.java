package com.person;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import java.util.Objects;

/**
 * Class for storing information about human.
 */
public class Person implements Cloneable {
    /**
     * Gender of person.
     */
    public enum Gender {
        MAN,
        WOMEN
    }

    private LocalDate dayOfBorn;
    private int passportID;
    private Gender gender;
    private String name;
    private String surname;
    private String middleName;

    /**
     * Change person's day of born.
     * @param dayOfBorn new day of born.
     */
    public void setDayOfBorn(LocalDate dayOfBorn) {
        this.dayOfBorn = dayOfBorn;
    }

    /**
     * Change passport id.
     * @param passportID new passport id.
     */
    public void setPassportID(int passportID) {
        this.passportID = passportID;
    }

    /**
     * Change person's gender.
     * @param gender новый пол.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Change person's name.
     * @param name new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Change person's surname.
     * @param surname new surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Change person's middle name.
     * @param middleName новое отчетсво.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     *  get passport id
     * @return passportID.
     */
    public int getPassportID() {
        return passportID;
    }

    /**
     * get person's gander in Person.Gender type.
     * @return gender.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * get person's name in String type.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * get person's surname in String type.
     * @return surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * get person's middle name in String type..
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
        return passportID == person.passportID &&
                dayOfBorn.equals(person.dayOfBorn) &&
                gender == person.gender &&
                name.equals(person.name) &&
                surname.equals(person.surname) &&
                middleName.equals(person.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfBorn, passportID, gender, name, surname, middleName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "dayOfBorn=" + dayOfBorn +
                ", passportID=" + passportID +
                ", gender=" + gender +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }

    /**
     * @param dayOfBorn person's day of born.
     * @param passportID person's passport id.
     * @param gender person's gender.
     * @param name person's name.
     * @param surname person's surname.
     * @param middleName person's middle name.
     */
    public Person(LocalDate dayOfBorn, int passportID, Gender gender, String name, String surname, String middleName) {
        this.dayOfBorn = dayOfBorn;
        this.passportID = passportID;
        this.gender = gender;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
    }

    /**
     * get person's Age.
     * @return age in int type
     */
    public int getAge() {
       return Years.yearsBetween(dayOfBorn, new LocalDate()).getYears();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
