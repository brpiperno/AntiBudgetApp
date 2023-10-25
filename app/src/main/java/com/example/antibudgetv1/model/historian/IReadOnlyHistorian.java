package com.example.antibudgetv1.model.historian;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;

import java.time.chrono.ChronoLocalDate;
import java.util.SortedMap;

/**
 * Representation of a read-only historian that manages historical values of a budget.
 * The historian stores historical values, representing account balances.
 * Generally, the historian keeps in sync with the budget model, to ensure that historical tracking
 * is properly associated with budget accounts.
 * In App, the user uses the historian to:
 * - Add Values
 * - Delete Values
 * - View Values
 * - Load Data from external .csv files
 */
public interface IReadOnlyHistorian {

    /**
     * Return a map of date-balances for an account
     * @param account the account name to get values.
     * @param start the inclusive start date.
     * @param end the inclusive end date.
     * @return a navigable map of date and balance values
     */
    SortedMap<ChronoLocalDate, Float> getKnownValues(String account, ChronoLocalDate start, ChronoLocalDate end);

    /**
     * Make the historian update the list of accounts it has awareness of according to it's budget.
     * If there are accounts in the budget that are not in the historian, the historian adds them.
     * Accounts in the historian that are not in the budget are left as is.
     */
    void updateAccounts();

    /**
     * Provide a spline function that represents interpolated values.
     * @param account the account to trend values for.
     * @param interpolator the function used to interpolate between actualized values.
     * @return A spline that represents the the value of the account as the y axis,
     * with the x axis being a simple incrementing count of days where day 0 is 1970-01-01 (ISO)
     * @throws IllegalArgumentException if the account isn't in the historian.
     */
    UnivariateFunction getValues(String account, UnivariateInterpolator interpolator);
}
