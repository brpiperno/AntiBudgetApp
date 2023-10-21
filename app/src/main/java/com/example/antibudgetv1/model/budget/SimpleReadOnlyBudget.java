package com.example.antibudgetv1.model.budget;

import com.example.antibudgetv1.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleReadOnlyBudget implements IReadOnlyBudget{

    private Map<String, IAccount> accounts;
    private String name;
    private String description;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
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
    public boolean hasAccount(IAccount account) {
        return this.accounts.containsKey(account.getName())
                && this.accounts.get(account.getName()).equals(account);
    }

    @Override
    public List<IAccount> getCopyOfAccounts() {
        return new ArrayList<>(this.accounts.values());
    }

    @Override
    public boolean hasAccountWithName(String account) {
        Utils.checkNull(account);
        return this.accounts.containsKey(account);
    }

    @Override
    public IBudget copy() {
        return new SimpleBudget(this.name, this.description, this.getCopyOfAccounts());
    }

    @Override
    public List<String> getAccountNames() {
        return new ArrayList<>(this.accounts.keySet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IBudget that = (IBudget) o;

        if (!this.getCopyOfAccounts().equals(that.getCopyOfAccounts())) return false;
        if (!name.equals(that.getName())) return false;
        return description.equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        int result = accounts.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
