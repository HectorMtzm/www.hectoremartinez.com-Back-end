package com.phoenixgb6.portfolio.universitycrm_rest.utilityentity;

// Simple class with only one attribute used to deserialize json that only contain an ID key.
public class idObject {

    private int id;

    public idObject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
