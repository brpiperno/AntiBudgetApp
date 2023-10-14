package com.example.antibudgetv1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Simple implementation of an account that holds transactions
 * Needs to be able to get and set transaction data, according to both the name and value itself,
 * using a map implementation
 */
public class SimpleAccount implements IAccount{
    private String name;
    private String description;
    private Map<String, ITransaction> transactions;

    public SimpleAccount(String name, String description, List<ITransaction> transactions) {
        checkNull(name);
        this.name = name;
        checkNull(description);
        this.description = description;
        this.transactions = new HashMap<>();
        for (ITransaction t : transactions) {
            checkNull(t);
            this.transactions.put(t.getName(), t);
        }
    }

    public SimpleAccount(String name) {
        this(name, "", new ArrayList<ITransaction>());
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
        this.transactions.put(transaction.getName(), transaction);
    }

    @Override
    public void addTransactions(List<ITransaction> transactions) {
        for (ITransaction t : transactions) {
            addTransaction(t);
        }
    }

    @Override
    public void removeTransaction(ITransaction transaction) {
        checkNull(transaction);
        checkTransaction(transaction.getName());
        this.transactions.remove(transaction.getName());
    }

    @Override
    public ITransaction getTransactionCopy(String name) {
        checkTransaction(name);
        return this.transactions.get(name).copy();
    }

    @Override
    public ITransaction getTransaction(ITransaction transaction) {
        checkNull(transaction);
        checkTransaction(transaction.getName());
        return this.transactions.get(transaction.getName());
    }

    @Override
    public boolean hasTransactionOfName(String name) {
        checkNull(name);
        return this.transactions.containsKey(name);
    }

    @Override
    public boolean hasTransaction(ITransaction transaction) {
        checkNull(transaction);
        String name = transaction.getName();
        return hasTransactionOfName(name) && this.transactions.get(name).equals(transaction);
    }

    @Override
    public List<ITransaction> getCopyTransactions() {
        List<ITransaction> copies = new ArrayList<>();
        for (ITransaction t : this.transactions.values()) {
            copies.add(t.copy());
        }
        return copies;
    }

    @Override
    public IAccount copy() {
        return new SimpleAccount(this.name, this.description, this.getCopyTransactions());
    }

    private void checkTransaction(String name) {
        checkNull(name);
        if (!hasTransactionOfName(name)) {
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
