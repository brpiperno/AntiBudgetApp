package com.example.antibudgetv1.model;

import java.util.List;

/**
 * This is a representation of a budget to be used to represent the regular transactions
 * (withdrawals and deposits) that occur on a monthly basis for a given user.
 * Transactions are grouped according to the Account they occur in.
 *
 */
public interface IAntiBudget {

    void addAccount(IAccount account);

    void deleteAccount(IAccount account);

    IAccount getAccount(String name);

    IAccount getAccount(IAccount account);

    boolean hasAccount(String name);

    boolean hasAccount(IAccount account);

    List<IAccount> getAccounts();

}
