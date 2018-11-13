package edu.luxoft.dbservice.entity;

import edu.luxoft.dbservice.annotation.Column;
import edu.luxoft.dbservice.annotation.Id;
import edu.luxoft.dbservice.annotation.Table;

@Table(name = "genre")
public class Genre {

    @Id
    @Column(name = "gnr_id")
    private int id;
    @Column(name = "gnr_name")
    private String genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
