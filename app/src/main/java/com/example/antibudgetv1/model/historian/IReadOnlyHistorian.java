package com.example.antibudgetv1.model.historian;

import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.SortedMap;
import java.util.function.Function;

/**
 * Representation of a read-only historian that manages historical values of a budget.
 * The historian stores historical values, representing account balances.
 * Generally, the historian keeps in sync with the budget model, to ensure that historical tracking
 * is properly associated with budget accounts.
 * In App, the user uses the historian to:
 * - Add Values
 * - Delete Values
 * - View Trends (either interpolating or extrapolating)
 * - Load Data from external .csv files
 */
public interface IReadOnlyHistorian {

    /**
     * Return a map of dates and associated values that belong to an account and fall within a range.
     * @param account the account name to get values.
     * @param start the inclusive start date.
     * @param end the inclusive end date.
     * @return a sorted map of all actualized values
     */
    SortedMap<ChronoLocalDate, Float> getKnownValues(
            String account, ChronoLocalDate start, ChronoLocalDate end);

    /**
     * Make the historian update the list of accounts it has awareness of according to it's budget.
     * If there are accounts in the budget that are not in the historian, the historian adds them.
     * Accounts in the historian that are not in the budget are left as is.
     */
    void updateAccounts();

    /**
     * Trend values for a given account. Extrapolates and Interpolates according to the function.
     * @param account the account to trend values for.
     * @param start the start date (inclusive).
     * @param end the end date (inclusive).
     * @param unit the time period between data points.
     *             As units are date specific and represent personal accounting, \
     *             options are DAY, WEEK, MONTH, YEAR, DECADE
     * @param func the function used to determine interpolation and extrapolation, as required.
     * @return A sorted map of values,
     * some being logged values, some being interpolated, and some extrapolated.
     * @throws IllegalStateException if a ChronoUnit other than DAY, WEEK, YEAR, or DECADE is used.
     * @throws IllegalArgumentException if the account isn't in the historian.
     * @throws IllegalStateException if there aren't sufficient data-points present
     * to interpolate or extrapolate
     */
    SortedMap<ChronoLocalDate, Float> getValues(
            String account, ChronoLocalDate start, ChronoLocalDate end,
            ChronoUnit unit, Function<SortedMap<ChronoLocalDate, Float>,
            SortedMap<ChronoLocalDate, Float>> func);
}
