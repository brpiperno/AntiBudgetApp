package com.example.antibudgetv1.model.historian;

import com.example.antibudgetv1.Utils;
import com.example.antibudgetv1.model.budget.IReadOnlyBudget;

import java.time.chrono.ChronoLocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReadOnlyHistorian implements IReadOnlyHistorian{
    Map<String, Map<ChronoLocalDate, Float>> values;
    IReadOnlyBudget budget;


    @Override
    public Map<ChronoLocalDate, Float> getKnownValues(String account) {
        checkAccount(account);
        return Objects.requireNonNull(values.get(account)).entrySet().stream().collect(Collectors.toMap
                (Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<ChronoLocalDate, Float> getKnownValues(
            String account, ChronoLocalDate start, ChronoLocalDate end) {
        checkAccount(account);
        checkDateRange(start, end);
        return Objects.requireNonNull(values.get(account)).entrySet().stream()
                .filter(s -> (s.getKey().isEqual(start) || s.getKey().isAfter(start)) &&
                        (s.getKey().isEqual(end) || s.getKey().isBefore(end)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Float getValue(String account, ChronoLocalDate date) {
        checkAccount(account);
        if (!hasValue(account, date)) {
            throw new IllegalArgumentException(String.format(
                    "Tried to get a value that does not exist for account: %s, date: %s",
                    account, date));
        }
        return Objects.requireNonNull(this.values.get(account)).get(date);
    }

    @Override
    public IReadOnlyBudget getBudget() {
        return this.budget;
    }

    @Override
    public boolean hasValue(ChronoLocalDate date) {
        Utils.checkNull(date);
        for (Map<ChronoLocalDate, Float> m : this.values.values()) {
            if (m.containsKey(date)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasValue(String account, ChronoLocalDate date) {
        checkAccount(account);
        Utils.checkNull(date);
        return Objects.requireNonNull(this.values.get(account)).containsKey(date);
    }

    /**
     * Check if the account exists.
     * @param account the name of the account to verify
     * @return true if the historian has the account, false otherwise
     * @throws IllegalArgumentException if the account is null
     * @throws IllegalStateException if the historian doesn't have an account but the budget does.
     */
    protected boolean hasAccount(String account) {
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
    protected void checkAccount(String account) {
        if (!hasAccount(account)) {
            throw new IllegalArgumentException(String.format(
                    "Does not have account of name: %s", account));
        }
    }

    protected void checkDateRange(ChronoLocalDate start, ChronoLocalDate end){
        Utils.checkNull(start);
        Utils.checkNull(end);
        if (start.isAfter(end)) {
            throw new IllegalArgumentException(
                    String.format("provided start date %s is after end date %s", start, end));
        }
    }
}
