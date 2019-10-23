package com.person;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import java.util.Objects;

/**
 * Класс, для хранения информации о человеке.
 */
public class Person implements Cloneable {

    public enum Gender {
        MAN,
        WOMEN
    };

    private LocalDate dayOfBorn;
    private int passportID;
    private Gender gender;
    private String name;
    private String surname;
    private String middleName;

    /**
     * Изменяет дату рождения.
     * @param dayOfBorn новая дата рождения.
     */
    public void setDayOfBorn(LocalDate dayOfBorn) {
        this.dayOfBorn = dayOfBorn;
    }

    /**
     * Изменяет номер паспорта.
     * @param passportID новый номер паспорта.
     */
    public void setPassportID(int passportID) {
        this.passportID = passportID;
    }

    /**
     * Изменяет пол.
     * @param gender новый пол.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Изменяет имя.
     * @param name новое имя.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Изменет фамилию.
     * @param surname новая фамилия.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Изменяет отчество.
     * @param middleName новое отчетсво.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     *  Возвращает номер паспорта.
     * @return текущий номер паспорта.
     */
    public int getPassportID() {
        return passportID;
    }

    /**
     * Возращает пол в формате Person.Gender.
     * @return текущйи пол человека.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Возвращает имя в формате String.
     * @return имя человека.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает Фамилию в формате String.
     * @return фамилия человека.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Возвращает отчество в формате String.
     * @return отчество человека.
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
     * Конструктор, создающий объект по переданным данным.
     * @param dayOfBorn дата рождения.
     * @param passportID номер паспорта.
     * @param gender пол.
     * @param name имя.
     * @param surname фамилия.
     * @param middleName отчество.
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
     * Возвращает сколько полных лет человеку.
     * @return целое количество лет.
     */
    public int getAge(){
       return Years.yearsBetween(dayOfBorn,new LocalDate()).getYears();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
