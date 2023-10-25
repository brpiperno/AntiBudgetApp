package com.example.antibudgetv1.model.historian;

import com.example.antibudgetv1.Utils;
import com.example.antibudgetv1.model.budget.IReadOnlyBudget;

import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Simple Representation of a data historian with read/write capabilities.
 * In this implementation, data is stored only in a treemap data structure.
 */
public class Historian extends ReadOnlyHistorian implements IHistorian{
    SortedMap<String, SortedMap<ChronoLocalDate, Float>> values;
    IReadOnlyBudget budget;

    public Historian(IReadOnlyBudget budget){
        this.budget = budget;
        this.values = new TreeMap<>();
        updateAccounts();
    }

    @Override
    public void addValue(String account, float value, ChronoLocalDate date) {
        checkAccount(account);
        Utils.checkNull(date);
        if (this.values.get(account).containsKey(date)) {
            throw new IllegalArgumentException(String.format(
                    "Account: %s already has value on %s", account, date));
        }
        this.values.get(account).put(date, value);
    }

    @Override
    public void deleteValue(String account, ChronoLocalDate date) {
        checkAccount(account);
        Utils.checkNull(date);
        if (!this.values.get(account).containsKey(date)) {
            throw new IllegalArgumentException(String.format(
                    "Account %s does not have a value on date: %s", account, date));
        }
        this.values.get(account).remove(date);
    }

    @Override
    public void updateAccounts(boolean deleteUnused) {
        updateAccounts();
        List<String> budgetAccounts = budget.getAccountNames();
        if (deleteUnused) {
            for (String s : this.values.keySet()) {
                if (!budgetAccounts.contains(s)) {
                    this.values.remove(s);
                }
            }
        }
    }

    /**
     * Throw appropriate error if an invalid account name is provided.
     * @param account the name of the account.
     */
    private void checkAccount(String account) {
        Utils.checkNull(account);
        if (this.values.containsKey(account)) {}
        else if (this.budget.hasAccountWithName(account)) {
            throw new IllegalStateException(String.format(
                    "Historian does not have account: %s, but budget %s does",
                    account, budget.getName()));
        }
        else {
            throw new IllegalArgumentException(String.format(
                    "Does not have account of name: %s", account));
        }
    }
}
