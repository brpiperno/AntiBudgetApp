package com.example.antibudgetv1.model.budget;

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
     * @param name the account's name.
     * @throws IllegalArgumentException if a null is provided.
     */
    void setName(String name);

    /**
     * Get the description of the account.
     * @return the description of the account. If the account doesn't have a description,
     * returns an empty string.
     */
    String getDescription();

    /**
     * set a new description for the budget account.
     * @param description the new description.
     * @throws IllegalArgumentException if a null is provided.
     */
    void setDescription(String description);

    /**
     * Add a transaction to the account.
     * @param transaction the transaction to add.
     * @throws IllegalArgumentException if a null is provided
     * or the account already contains a transaction of the same name.
     */
    void addTransaction(ITransaction transaction);

    /**
     * Add a list of transactions.
     * @param transactions the list of transactions to add.
     * @throws IllegalArgumentException if any are null
     * or share a name with a transaction already in the account
     */
    void addTransactions(List<ITransaction> transactions);

    /**
     * Remove the transaction from the account.
     * @param transaction the transaction to remove.
     * @throws IllegalArgumentException if the transaction is null or is not in the account.
     */
    void removeTransaction(ITransaction transaction);

    /**
     * Get a copy of the ITransaction with that name.
     * @param name the name of the transaction
     * @return A copy of the transaction object
     * @throws IllegalArgumentException if there is no transaction of the given name
     * or a null is given.
     */
    ITransaction getTransactionCopy(String name);

    /**
     * Get the reference to the ITransaction described.
     * @param transaction the transaction to look for.
     * @return the reference to the ITransaction described for further editing.
     * @throws IllegalArgumentException if there is no transaction of the given name
     * or a null is provided.
     */
    ITransaction getTransaction(ITransaction transaction);

    /**
     * See if the account has a transaction of a given name.
     * @param name the name of the transaction
     * @return true if the account has a transaction of the given name, false otherwise.
     */
    boolean hasTransactionOfName(String name);

    /**
     * Does the account have this exact transaction
     * @param transaction the transaction to compare to.
     * @return true if the account has the same transaction, false otherwise.
     * Sameness is overwritten to check that two transactions have identical states.
     */
    boolean hasTransaction(ITransaction transaction);


    /**
     * Generate a list of deep copies of transaction that are in the account.
     * @return A list of deepcopies of transactions in the account.
     */
    List<ITransaction> getCopyTransactions();

    /**
     * Provide a deep copy of the account.
     * @return a deep copy of the account.
     */
    IAccount copy();

}
