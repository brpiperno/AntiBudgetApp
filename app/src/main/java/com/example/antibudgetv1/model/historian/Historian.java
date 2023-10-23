package com.example.antibudgetv1.model.historian;

import com.example.antibudgetv1.Utils;
import com.example.antibudgetv1.model.budget.IReadOnlyBudget;

import java.time.chrono.ChronoLocalDate;
import java.util.HashMap;
import java.util.Map;

public class Historian extends ReadOnlyHistorian implements IHistorian{

    //TODO: create builder from .csv file
    Map<String, Map<ChronoLocalDate, Float>> values;
    IReadOnlyBudget budget;

    public Historian(IReadOnlyBudget budget){
        this.budget = budget;
        this.values = new HashMap<>();
        updateAccounts();
    }

    @Override
    public void addValue(String account, float value, ChronoLocalDate date) {
        if (hasValue(account, date)) {
            throw new IllegalArgumentException(String.format(
                    "Account: %s already has value on %s", account, date));
        }
        this.values.get(account).put(date, value);
    }

    @Override
    public void deleteValue(String account, ChronoLocalDate date) {
        if (!hasValue(account, date)) {
            throw new IllegalArgumentException(String.format(
                    "Account %s does not have a value on date: %s", account, date));
        }
        this.values.get(account).remove(date);
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

    private boolean hasValue(String account, ChronoLocalDate date) {
        checkAccount(account);
        Utils.checkNull(date);
        return this.values.get(account).containsKey(date);
    }
}
