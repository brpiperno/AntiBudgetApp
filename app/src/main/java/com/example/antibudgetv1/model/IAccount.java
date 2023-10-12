package com.example.antibudgetv1.model;

import java.util.List;

/**
 * Representation of an bank account in a budget, that holds its monthly transactions.
 *
 * In App, the account must be able to do the following:
 * - Show and edit its name and description
 * - Show all transactions that it has
 * - Get, add, delete, and set individual transactions.
 *      Getters can operate by either name or object.
 */
public interface IAccount {

    /**
     * Get the name of the account
     * @return the account's name
     */
    String getName();

    /**
     * Set the name of the account
     * @param name the account's name
     */
    void setName(String name);

    /**
     * Get the description of the account.
     * @return the description of the account.
     */
    String getDescription();

    /**
     * set a new description for the budget account.
     * @param description the new description.
     */
    void setDescription(String description);

    /**
     * Add a transaction to the account.
     * @param transaction the transaction to add.
     */
    void addTransaction(ITransaction transaction);

    void addTransactions(List<ITransaction> transactions);

    /**
     * Remove the transaction from the account.
     * @param transaction the transaction to remove.
     * @throws IllegalArgumentException if the transaction does not exist in the account.
     */
    void removeTransaction(ITransaction transaction);

    /**
     * Get a copy of the Itransaction with that name.
     * @param name the name of the transaction
     * @return A copy of the transaction object
     * @throws IllegalArgumentException if there is no transaction of the given name.
     */
    ITransaction getTransaction(String name);

    /**
     * Get the reference to the Itransaction described.
     * @param transaction the transaction to look for.
     * @return the reference to the Itransaction described for further editing.
     * @throws IllegalArgumentException if there is no transaction of the given name.
     */
    ITransaction getTransaction(ITransaction transaction);

    /**
     * See if the account has a transaction of a given name.
     * @param name the name of the transaction
     * @return true if the account has a transaction of the given name, false otherwise.
     */
    boolean hasTransaction(String name);

    /**
     * Does the account have this exact transaction
     * @param transaction the transaction to compare to.
     * @return true if the account has the same transaction, false otherwise.
     * Sameness is overwritten to check that two transactions have identical states.
     */
    boolean hasTransaction(ITransaction transaction);


    /**
     * Generate a list of transaction names that are in the model.
     * @return A list of strings of transaction names in the model.
     */
    List<ITransaction> getTransactions();

}
