package com.example.antibudgetv1.model;

import com.example.antibudgetv1.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleAntiBudget implements IAntiBudget{
    private Map<String, IAccount> accounts;
    private String name;
    private String description;

    public SimpleAntiBudget(String name, String description, List<IAccount> accounts) {
        this(name, description);
        this.accounts = new HashMap<>();
        for (IAccount a : accounts) {
            Utils.checkNull(a);
            this.addAccount(a);
        }
    }
    public SimpleAntiBudget(String name, String description) {
        Utils.checkNull(name);
        Utils.checkNull(description);
        this.name = name;
        this.description = description;
        this.accounts = new HashMap<>();
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
    public void addAccount(IAccount account) {
        Utils.checkNull(account);
        safeGetAccount(account, false);
        this.accounts.put(account.getName(), account);
    }

    @Override
    public void deleteAccount(IAccount account) {
        Utils.checkNull(account);
        this.accounts.remove(safeGetAccount(account, true).getName());
    }

    @Override
    public IAccount getAccountCopy(String name) {
        Utils.checkNull(name);
        if (hasAccountWithName(name)) {
            return accounts.get(name).copy();
        }
        throw new IllegalArgumentException(
                String.format("Does not contain account with name: %s", name));
    }

    @Override
    public IAccount getAccount(IAccount account) {
        return safeGetAccount(account, true);
    }

    @Override
    public boolean hasAccountWithName(String account) {
        Utils.checkNull(account);
        return this.accounts.containsKey(account);
    }

    @Override
    public boolean hasAccount(IAccount account) {
        return this.accounts.containsKey(account.getName())
                && this.accounts.get(account.getName()).equals(account);
    }

    @Override
    public List<IAccount> getCopyOfAccounts() {
        return new ArrayList<>(this.accounts.values());
    }

    @Override
    public IAntiBudget copy() {
        return new SimpleAntiBudget(this.name, this.description, this.getCopyOfAccounts());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleAntiBudget that = (SimpleAntiBudget) o;

        if (!accounts.equals(that.accounts)) return false;
        if (!name.equals(that.name)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = accounts.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    private IAccount safeGetAccount(IAccount account, boolean shouldHave) {
        Utils.checkNull(account);
        if (hasAccount(account) && !shouldHave) {
            throw new IllegalArgumentException(String.format(
                    "This budget already has this account: %s", account.getName()));
        }
        else if (!hasAccount(account) && shouldHave) {
            throw new IllegalArgumentException(String.format(
                    "This budget doesn't have this account: %s", account.getName()));
        }
        else if (shouldHave) {
            return this.accounts.get(account.getName());
        }
        else return null;
    }

    private IAccount safeGetAccount(String name, boolean shouldHave) {
        Utils.checkNull(name);
        boolean hasAccount = hasAccountWithName(name);
        if (hasAccount && shouldHave) {
            return this.getAccountCopy(name);
        }
        else if (!hasAccount && shouldHave) {
            throw new IllegalArgumentException(
                    String.format("Budget doesn't have account: %s", name));
        }
        else if (hasAccount && !shouldHave) {
            throw new IllegalArgumentException(
                    String.format("Budget already has account: %s", name));
        }
        else {
            return null;
        }
    }
}
