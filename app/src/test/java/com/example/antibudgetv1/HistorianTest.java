package com.example.antibudgetv1;

import com.example.antibudgetv1.model.budget.IAccount;
import com.example.antibudgetv1.model.budget.IBudget;
import com.example.antibudgetv1.model.budget.ITransaction;
import com.example.antibudgetv1.model.budget.SimpleAccount;
import com.example.antibudgetv1.model.budget.SimpleBudget;
import com.example.antibudgetv1.model.budget.SimpleTransaction;
import com.example.antibudgetv1.model.historian.Historian;
import com.example.antibudgetv1.model.historian.IHistorian;
import com.example.antibudgetv1.model.historian.IReadOnlyHistorian;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistorianTest {

    IBudget b1;
    IBudget b2;
    List<IAccount> LoA1;
    IAccount a1;
    IAccount a2;
    List<ITransaction> LoT1;
    List<ITransaction> LoT2;
    ITransaction t1;
    ITransaction t2;
    ITransaction t3;
    IReadOnlyHistorian h1;

    @Before
    public void start() {
        t1 = new SimpleTransaction(
                "first transaction", -10000f, "first description");
        t2 = new SimpleTransaction(
                "second transaction", 5f, "second description");
        t3 = new SimpleTransaction("third", 65f);
        LoT1 = new ArrayList<>(Arrays.asList(t1, t2));
        LoT2 = new ArrayList<>(Arrays.asList(t3));
        a1 = new SimpleAccount("Savings Account", "High Yield Savings", LoT1);
        a2 = new SimpleAccount("Credit Card");
        LoA1 = new ArrayList<>(Arrays.asList(a1, a2));
        b1 = new SimpleBudget("test", "test budget", LoA1);
        b2 = new SimpleBudget("second", "second");
        //TODO: Initialize historian
    }

    @After
    public void end() {
        start();
    }

    //TEST IREADONLYHISTORIAN METHODS

    @Test
    void GetKnownValuesTest(){

    }

    @Test (expected = IllegalArgumentException.class)
    void GetKnownValuesTestInvalid(){
        h1.getKnownValues("Nonsense account");
    }



}
