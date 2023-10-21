package com.example.antibudgetv1.model.budget;

import com.example.antibudgetv1.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Utils.checkNull(name);
        Utils.checkNull(description);
        this.transactions = new HashMap<>();
        for (ITransaction t : transactions) {
            Utils.checkNull(t);
            this.transactions.put(t.getName(), t);
        }
        this.name = name;
        this.description = description;
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
        Utils.checkNull(name);
        this.name = name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        Utils.checkNull(description);
        this.description = description;
    }

    @Override
    public void addTransaction(ITransaction transaction) {
        Utils.checkNull(transaction);
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
        Utils.checkNull(transaction);
        safeGetTransaction(transaction.getName(), true);
        this.transactions.remove(transaction.getName());
    }

    @Override
    public ITransaction getTransactionCopy(String name) {
        Utils.checkNull(name);
        return safeGetTransaction(name, true).copy();
    }

    @Override
    public ITransaction getTransaction(ITransaction transaction) {
        Utils.checkNull(transaction);
        return safeGetTransaction(transaction.getName(), true);
    }

    @Override
    public boolean hasTransactionOfName(String name) {
        Utils.checkNull(name);
        return this.transactions.containsKey(name);
    }

    @Override
    public boolean hasTransaction(ITransaction transaction) {
        Utils.checkNull(transaction);
        String name = transaction.getName();
        return this.transactions.containsKey(name) &&
                this.transactions.get(name).equals(transaction);
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

    /**
     * Get a transaction specified, if it is contained.
     * @param name the name of the transaction to search for.
     * @param shouldHave whether it is expected for the account to have the transaction
     * @return the transaction specified
     * @throws IllegalArgumentException if the transaction was expected but not found
     * or not expected but found.
     * @throws IllegalStateException if the transaction was not expected and not found.
     */
    private ITransaction safeGetTransaction(String name, boolean shouldHave) {
        Utils.checkNull(name);
        boolean hasT = hasTransactionOfName(name);
        if (!shouldHave && hasT) {
            throw new IllegalArgumentException(
                    String.format("Account already contains transaction: ", name));
        }
        else if (shouldHave && !hasT) {
            throw new IllegalArgumentException(
                    String.format("Account doesn't have transaction: ", name));
        }
        else if (shouldHave && hasT) {
            return this.transactions.get(name);
        }
        else {
            throw new IllegalStateException("tried to get a transaction user was not expecting");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleAccount that = (SimpleAccount) o;

        if (!name.equals(that.name)) return false;
        if (!description.equals(that.description)) return false;
        return transactions.equals(that.transactions);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + transactions.hashCode();
        return result;
    }
}