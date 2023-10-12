package com.example.antibudgetv1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SimpleAntiBudget implements IAntiBudget{
    private List<IAccount> accountList;
    private String name;
    private String description;

    public SimpleAntiBudget(String name, String description, List<IAccount> accounts) {
        this.name = name;
        this.description = description;
        this.accountList = accounts;
    }
    public SimpleAntiBudget(String name, String description) {
        new SimpleAntiBudget(name, description, new ArrayList<IAccount>());
    }

    public SimpleAntiBudget(String name) {
        new SimpleAntiBudget(name, "", new ArrayList<IAccount>());
    }

    @Override
    public void addAccount(IAccount account) {
        checkAccount(account, false);
        this.accountList.add(account);
    }

    @Override
    public void deleteAccount(IAccount account) {
        checkAccount(account, true);
        this.accountList.remove(account);
    }

    @Override
    public IAccount getAccount(String name) {
        for (IAccount a : this.accountList) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        throw new IllegalArgumentException(String.format(
                "Could not find account with name: %s", name));
    }

    @Override
    public IAccount getAccount(IAccount account) {
        return checkAccount(account, true);
    }

    @Override
    public boolean hasAccount(String account) {
        try {
            getAccount(account);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean hasAccount(IAccount account) {
        return this.accountList.contains(account);
    }

    @Override
    public List<IAccount> getAccounts() {
        return this.accountList;
    }

    private IAccount checkAccount(IAccount account, boolean shouldHave) {
        if (hasAccount(account) && !shouldHave) {
            throw new IllegalArgumentException(String.format(
                    "This budget already has this account: %s", account.getName()));
        }
        else if (!hasAccount(account) && shouldHave) {
            throw new IllegalArgumentException(String.format(
                    "This budget doesn't have this account: %s", account.getName()));
        }
        return this.accountList.get(this.accountList.indexOf(account));
    }
}
