package com.edubreeze.model;

import com.edubreeze.database.DatabaseHelper;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;

/**
 * An administrative location class for Local Government Areas (Based on Nigeria's admin levels)
 */
@DatabaseTable(tableName = "lgas")
public class Lga {

    @DatabaseField(id = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private State state;

    @ForeignCollectionField(eager = false)
    private ForeignCollection<School> schools;

    public Lga() {
        // ORMLite needs a no-arg constructor
    }

    public Lga(int id, String name, State state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ForeignCollection<School> getSchools() {
        return schools;
    }

    public static Lga find(int lgaId) throws SQLException{
        return  DatabaseHelper.getLgaDao().queryForId(lgaId);
    }

    @Override
    public int hashCode() {
        return new Integer(id).hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        return id == ((Lga) other).getId();
    }
}
