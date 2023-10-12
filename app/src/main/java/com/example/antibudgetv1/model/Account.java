package com.example.antibudgetv1.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Simple implementation of an account that holds transactions
 * Needs to be able to get and set transaction data, according to both the name and value itself,
 * using a map implementation
 */
public class Account implements IAccount{
    private String name;
    private String description;
    private Map<String, ITransaction> transactions;

    public Account(String name, String description, List<ITransaction> transactions) {
        this.name = name;
        this.description = description;
        this.transactions = new HashMap<>();
        for (ITransaction t : transactions) {
            this.transactions.put(t.getName(), t);
        }
    }

    public Account(String name) {
        this.name = name;
        this.description = "";
        this.transactions = new HashMap<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        checkNull(name);
        this.name = name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        checkNull(description);
        this.description = description;
    }

    @Override
    public void addTransaction(ITransaction transaction) {
        checkNull(transaction);
        if (hasTransaction(transaction)) {
            throw new IllegalArgumentException(String.format(
                    "Account: %s already contains transaction: %s",
                    this.name, name));
        }
    }

    @Override
    public void addTransactions(List<ITransaction> transactions) {
        for (ITransaction t : transactions) {
            addTransaction(t);
        }
    }

    @Override
    public void removeTransaction(ITransaction transaction) {
        checkTransaction(transaction.getName());
        this.transactions.remove(transaction.getName());
    }

    @Override
    public ITransaction getTransaction(String name) {
        checkTransaction(name);
        return this.transactions.get(name);
    }

    @Override
    public ITransaction getTransaction(ITransaction transaction) {
        checkTransaction(transaction.getName());
        return this.transactions.get(transaction.getName());
    }

    @Override
    public boolean hasTransaction(String name) {
        checkNull(name);
        return this.transactions.containsKey(name);
    }

    @Override
    public boolean hasTransaction(ITransaction transaction) {
        checkNull(transaction);
        String name = transaction.getName();
        return hasTransaction(name) && this.transactions.get(name).equals(transaction);
    }

    @Override
    public List<ITransaction> getTransactions() {
        return this.transactions.values().stream().collect(Collectors.toList());
    }

    private void checkTransaction(String name) {
        checkNull(name);
        if (!hasTransaction(name)) {
            throw new IllegalArgumentException(String.format(
                    "Account: %s does not contain transaction: %s",
                    this.name, name));
        }
    }

    private void checkNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Null object provided");
        }
    }
}
