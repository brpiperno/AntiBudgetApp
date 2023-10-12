package com.example.antibudgetv1.model;

import java.util.Objects;

/**
 * Simple representation of a transaction used to represent an item in a budget.
 */
public class SimpleTransaction implements ITransaction{
    private String name; //The name of the transaction.
    private float value; //The value of the transaction.
    private String description; //The description of the transaction.

    public SimpleTransaction(String name, float value, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }

    public SimpleTransaction(String name, float value) {
        this(name, value, "");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        checkNull(name);
        this.name = name;
    }

    @Override
    public float getValue() {
        return this.value;
    }

    @Override
    public void setValue(float value) {
        checkNull(value);
        this.value = value;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        checkNull(description);
        this.description = description;
    }

    private void checkNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Provided object is null");
        }
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof SimpleTransaction)) { return false;}

        SimpleTransaction t = (SimpleTransaction) other;
        return t.getName() == this.getName()
                && t.getValue() == this.getValue()
                && t.getDescription() == this.getDescription();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, description);
    }
}
