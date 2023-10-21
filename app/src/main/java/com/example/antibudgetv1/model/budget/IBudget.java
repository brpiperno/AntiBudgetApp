package com.example.antibudgetv1.model.budget;

/**
 * This is a representation of a budget to be used to represent the regular transactions
 * (withdrawals and deposits) that occur on a monthly basis for a given user.
 * Transactions are grouped according to the Account they occur in.
 *
 */
public interface IBudget extends IReadOnlyBudget{


    /**
     * Set the name of the budget
     * @param name the budget name.
     * @throws IllegalArgumentException if a null is provided.
     */
    void setName(String name);

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
     * Get a reference of the account described.
     * @param account the account to grab a direct reference of.
     * @return the modifiable account described.
     * @throws IllegalArgumentException if the given accout is null or not present in the budget
     */
    IAccount getAccount(IAccount account);
}
