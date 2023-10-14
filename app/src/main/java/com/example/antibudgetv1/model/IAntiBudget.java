package com.example.antibudgetv1.model;

import java.util.List;

/**
 * This is a representation of a budget to be used to represent the regular transactions
 * (withdrawals and deposits) that occur on a monthly basis for a given user.
 * Transactions are grouped according to the Account they occur in.
 *
 */
public interface IAntiBudget {

    /**
     * Get the name of the budget
     * @return the budget name
     */
    String getName();

    /**
     * Set the name of the budget
     * @param name the budget name.
     * @throws IllegalArgumentException if a null is provided.
     */
    void setName(String name);

    /**
     * Get the description of the budget.
     * @return the description of the budget.
     */
    String getDescription();

    /**
     * set a new description for the budget.
     * @param description the new description.
     * @throws IllegalArgumentException if a null is provided.
     */
    void setDescription(String description);

    /**
     * Add a new account to the budget.
     * @param account the account to be added.
     * @throws IllegalArgumentException if account is null or account of same name is already there
     */
    void addAccount(IAccount account);

    /**
     * Delete the account from the budget.
     * @param account the account to be deleted.
     * @throws IllegalArgumentException if the provided account is null or not in the budget
     */
    void deleteAccount(IAccount account);

    /**
     * Get a copy of the account
     * @param name the name of the account.
     * @return a copy of the account specified.
     * @throws IllegalArgumentException if the name is null or not in the budget
     */
    IAccount getAccountCopy(String name);

    /**
     * Get a reference of the account described.
     * @param account the account to grab a direct reference of.
     * @return the modifiable account described.
     * @throws IllegalArgumentException if the given accout is null or not present in the budget
     */
    IAccount getAccount(IAccount account);

    /**
     * Check if the budget has an account of the given name
     * @param name the name of the account.
     * @return true if the budget contains an account of the same name, false otherwise.
     */
    boolean hasAccountWithName(String name);

    /**
     * Check if the budget has the provided account.
     * @param account the account to check for.
     * @return true if the budget contains an equal account, false otherwise.
     */
    boolean hasAccount(IAccount account);

    /**
     * Get a deep copy of all accounts in the budget.
     * @return a list of deep copies of the budget's accounts.
     */
    List<IAccount> getCopyOfAccounts();

    /**
     * Make a deep copy of the budget.
     * @return a deep copy of the budget.
     */
    IAntiBudget copy();
}
