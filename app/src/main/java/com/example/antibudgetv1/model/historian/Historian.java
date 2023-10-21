package com.example.antibudgetv1.model.historian;

import com.example.antibudgetv1.Utils;
import com.example.antibudgetv1.model.budget.IReadOnlyBudget;

import java.io.File;
import java.time.chrono.ChronoLocalDate;
import java.util.HashMap;
import java.util.Map;

public class Historian extends ReadOnlyHistorian implements IHistorian{
    Map<String, Map<ChronoLocalDate, Float>> values;
    IReadOnlyBudget budget;

    public Historian(IReadOnlyBudget budget){
        this.budget = budget;
        this.values = new HashMap<>();
    }

    public Historian(IReadOnlyBudget budget, File loadFile) {
        //TODO: create builder from .csv file
    }

    @Override
    public void addValue(String account, float value, ChronoLocalDate date) {
        checkAccount(account);
        Utils.checkNull(date);
        if (hasValue(account, date)) {
            throw new IllegalArgumentException(String.format(
                    "Account: %s already has value on %s", account, date));
        }
        this.values.get(account).put(date, value);
    }

    @Override
    public void deleteValue(String account, ChronoLocalDate date) {
        checkAccount(account);
        Utils.checkNull(date);
        if (!hasValue(account, date)) {
            throw new IllegalArgumentException(String.format(
                    "Account %s does not have a value on date: %s", account, date));
        }
        this.values.get(account).remove(date);
    }
}
