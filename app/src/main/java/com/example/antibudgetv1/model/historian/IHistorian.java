package com.example.antibudgetv1.model.historian;

import com.example.antibudgetv1.model.budget.IReadOnlyBudget;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

/**
 * Representation of a historian that manages historical values of a budget.
 * The historian stores historical values, representing account balances.
 * Generally, the historian keeps in sync with the budget model, to ensure that historical tracking
 * is properly associated with budget accounts.
 */
public interface IHistorian extends IReadOnlyHistorian{

    /**
     * Add a historical value to track.
     * @param account the name of the account to associate with the value
     * @param value the value of the account
     * @param date the date the value is noted.
     * @throws IllegalArgumentException if the account does not exist in the budget
     * @throws IllegalArgumentException if there is already a value for the date and account
     */
    void addValue(String account, float value, ChronoLocalDate date);

    /**
     * Delete an existing value.
     * @param account the name of the account that the value exists in
     * @param date the date of the value
     * @throws IllegalArgumentException if the account does not exist in the budget
     * @throws IllegalArgumentException if there is no value for that date and account.
     */
    void deleteValue(String account, ChronoLocalDate date);
}
