package com.example.antibudgetv1.model.budget;

import java.util.List;

public interface IReadOnlyBudget {

    /**
     * Get the name of the budget
     * @return the budget name
     */
    String getName();


    /**
     * Get the description of the budget.
     * @return the description of the budget.
     */
    String getDescription();


    /**
     * Get a copy of the account
     * @param name the name of the account.
     * @return a copy of the account specified.
     * @throws IllegalArgumentException if the name is null or not in the budget
     */
    IAccount getAccountCopy(String name);


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
    IBudget copy();

    /**
     * Return a list of strings corresponding to names of accounts stored in the mode.
     * @return a list of Account names.
     */
    List<String> getAccountNames();
}
