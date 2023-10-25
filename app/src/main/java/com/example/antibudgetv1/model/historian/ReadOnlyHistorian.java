package com.example.antibudgetv1.model.historian;

import com.example.antibudgetv1.Utils;
import com.example.antibudgetv1.model.budget.IReadOnlyBudget;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;

import java.time.chrono.ChronoLocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReadOnlyHistorian implements IReadOnlyHistorian{
    Map<String, SortedMap<ChronoLocalDate, Float>> values;
    IReadOnlyBudget budget;


    @Override
    public SortedMap<ChronoLocalDate, Float> getKnownValues(
            String account, ChronoLocalDate start, ChronoLocalDate end) {
        checkAccount(account);
        Utils.checkDateRange(start, end);
        Predicate<ChronoLocalDate> inRange = s -> ((s.isAfter(start) || s.isEqual(start))
                        && (s.isEqual(end) || s.isBefore(end)));
        return Objects.requireNonNull(values.get(account))
                .entrySet()
                .stream()
                .filter(d -> inRange.test(d.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> b, TreeMap::new));
    }

    @Override
    public void updateAccounts() {
        for (String s : budget.getAccountNames()) {
            if (!this.values.containsKey(s)) {
                this.values.put(s, new TreeMap<>());
            }
        }
    }

    @Override
    public UnivariateFunction getValues(String account, UnivariateInterpolator interpolator) {
        checkAccount(account);
        Utils.checkNull(interpolator);

        SortedMap<ChronoLocalDate, Float> actualized = this.values.get(account);
        assert actualized != null;
        int size = actualized.size();
        double[] datesAsDouble = new double[size];
        double[] valuesAsDouble = new double[size];

        int current = 0;
        for (Map.Entry<ChronoLocalDate, Float> e : actualized.entrySet()) {
            datesAsDouble[current] = e.getKey().toEpochDay();
            valuesAsDouble[current] = e.getValue();
        }
        return interpolator.interpolate(datesAsDouble, valuesAsDouble);
    }


    /**
     * Check if the account exists.
     * @param account the name of the account to verify
     * @return true if the historian has the account, false otherwise
     * @throws IllegalArgumentException if the account is null
     * @throws IllegalStateException if the historian doesn't have an account but the budget does.
     */
    private boolean hasAccount(String account) {
        Utils.checkNull(account);
        if (this.values.containsKey(account)) {return true;}
        else if (this.budget.hasAccountWithName(account)) {
            throw new IllegalStateException(String.format(
                            "Historian does not have account: %s, but budget %s does",
                            account, budget.getName()));
        }
        else {return false;}
    }

    /**
     * Throw appropriate error if an invalid account name is provided.
     * @param account the name of the account.
     */
    private void checkAccount(String account) {
        if (!hasAccount(account)) {
            throw new IllegalArgumentException(String.format(
                    "Does not have account of name: %s", account));
        }
    }
}
