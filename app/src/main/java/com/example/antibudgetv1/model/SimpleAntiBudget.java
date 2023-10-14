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
        Utils.checkNull(name);
        Utils.checkNull(description);
        for (IAccount a : accounts) {
            Utils.checkNull(a);
        }
        this.name = name;
        this.description = description;
        this.accounts = new HashMap<>();
        for (IAccount a : accounts) {
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

    public SimpleAntiBudget(String name) {
        this(name, "");
    }

    @Override
    public void addAccount(IAccount account) {
        safeGetAccount(account, false);
        this.accounts.put(account.getName(), account);
    }

    @Override
    public void deleteAccount(IAccount account) {
        this.accounts.remove(safeGetAccount(account, true).getName());
    }

    @Override
    public IAccount getAccountCopy(String name) {
        Utils.checkNull(name);
        if (this.accounts.containsKey(name)) {
            IAccount copy;
            return accounts.get(name);
        }
        throw new IllegalArgumentException(
                String.format("Does not contain account with name: %s", name));
    }

    @Override
    public IAccount getAccount(IAccount account) {
        return safeGetAccount(account, true);
    }

    @Override
    public boolean hasAccount(String account) {
        try {
            getAccountCopy(account);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean hasAccount(IAccount account) {
        return this.accounts.containsKey(account.getName())
                && this.accounts.get(account.getName()).equals(account);
    }

    @Override
    public List<IAccount> getAccounts() {
        return new ArrayList<>(this.accounts.values());
    }

    @Override
    public IAntiBudget copy() {
        return new SimpleAntiBudget(this.name, this.description, this.getAccounts());
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
        return this.accounts.get(account.getName());
    }
}
