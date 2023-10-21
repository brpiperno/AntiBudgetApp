package com.example.antibudgetv1.model.historian;

import com.example.antibudgetv1.model.budget.IReadOnlyBudget;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Map;

/**
 * Representation of a read-only historian that manages historical values of a budget.
 * The historian stores historical values, representing account balances.
 * Generally, the historian keeps in sync with the budget model, to ensure that historical tracking
 * is properly associated with budget accounts.
 */
public interface IReadOnlyHistorian {

    /**
     * Return a map of dates and their associated historical values.
     * @param account the account to get values.
     * @return a map of known values
     * @throws IllegalArgumentException if the account does not exist in the budget
     */
    Map<ChronoLocalDate, Float> getKnownValues(String account);

    /**
     * Retun a map of dates and associated values that belong to an account and fall within a range.
     * @param account the account name to get values.
     * @param start the inclusive start date.
     * @param end the inclusive end date.
     * @return
     */
    Map<ChronoLocalDate, Float> getKnownValues(
            String account, ChronoLocalDate start, ChronoLocalDate end);

    /**
     * Get the value stored for a particular account on a given date.
     * @param account the account that the value represents the balance of.
     * @param date the date of the value.
     * @return the value stored.
     * @throws IllegalArgumentException if there is no value on the date provided for the account.
     */
    Float getValue(String account, ChronoLocalDate date);

    /**
     * Get a readonly reference of the budget used
     * @return the reference to the budget used.
     */
    IReadOnlyBudget getBudget();

    /**
     * Does the historian have a value at the given date
     * @return true if it has at least one value on the given date, false otherwise.
     */
    boolean hasValue(ChronoLocalDate date);

    /**
     * Does the historian have a value at the given date for the given account?
     * @param account the name of the account in the budget
     * @param date the date of the value
     * @return True if there is a value present, false otherwise.
     */
    boolean hasValue(String account, ChronoLocalDate date);
}
