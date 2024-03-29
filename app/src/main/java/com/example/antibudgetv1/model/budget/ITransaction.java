package com.example.antibudgetv1.model.budget;

/*
Representation of a transaction for creating a budget.
A budget a monthly positive or negative cash flow.
 */
public interface ITransaction {

    /**
     * Return the name of the transaction.
     * @return the name of the transaction.
     */
    String getName();

    /**
     * Set the name of the transaction.
     * @param name the name of the transaction.
     * @throws IllegalArgumentException if a null is provided.
     */
    void setName(String name);

    /**
     * Return the value (either positive or negative) of the transaction.
     * @return the value of the transaction.
     */
    float getValue();

    /**
     * Set the value of the transaction.
     * @param value the value of the transaction.
     *             Positive values are deposits, negative values are withdrawals.
     */
    void setValue(float value);

    /**
     * Get the description of the transaction.
     * @return the description of the transaction.
     */
    String getDescription();

    /**
     * Set the description of the transaction.
     * @param description
     * @throws IllegalArgumentException if a null is provided.
     */
    void setDescription(String description);

    /**
     * Return a deep copy of the transaction.
     * @return
     */
    ITransaction copy();
}
