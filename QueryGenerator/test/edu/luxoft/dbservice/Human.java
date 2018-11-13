package edu.luxoft.dbservice;

import edu.luxoft.dbservice.annotation.Column;

public class Human {
    @Column
    private int birthYear;

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
