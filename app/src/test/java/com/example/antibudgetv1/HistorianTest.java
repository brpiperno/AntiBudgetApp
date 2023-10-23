package com.example.antibudgetv1;

import static org.junit.Assert.assertTrue;

import com.example.antibudgetv1.model.budget.IAccount;
import com.example.antibudgetv1.model.budget.IBudget;
import com.example.antibudgetv1.model.budget.ITransaction;
import com.example.antibudgetv1.model.budget.SimpleAccount;
import com.example.antibudgetv1.model.budget.SimpleBudget;
import com.example.antibudgetv1.model.budget.SimpleTransaction;
import com.example.antibudgetv1.model.historian.Historian;
import com.example.antibudgetv1.model.historian.IHistorian;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistorianTest {

    float DELTA = 0.0f;

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
    IHistorian h1;

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
        h1 = new Historian(b1);
        h1.addValue("Savings Account", 4321.50f, LocalDate.parse("2007-12-03"));
        h1.addValue("Credit Card", 123456.78f, LocalDate.parse("2007-12-03"));
    }

    @After
    public void end() {
        start();
    }

    //TEST IREADONLYHISTORIAN METHODS

    @Test
    public void ConstructorTest() {
        IHistorian h2 = new Historian(b1);

    }

    @Test
    public void GetKnownValuesTest(){
        assertTrue(h1.getKnownValues(
                "Savings Account",
                LocalDate.parse("2007-12-04"),
                LocalDate.parse("2007-12-05")).isEmpty());
        assertTrue(h1.getKnownValues(
                "Credit Card",
                LocalDate.parse("2007-12-03"),
                LocalDate.parse("2007-12-03")).containsValue(123456.78f));
    }

    @Test (expected = IllegalArgumentException.class)
    public void GetKnownValuesTestNullStartDate() {
        h1.getKnownValues("string", null, LocalDate.parse("2007-12-03"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void GetKnownValuesTestNullEndDate() {
        h1.getKnownValues("string", LocalDate.parse("2007-12-03"), null);
    }

    @Test
    public void AddValueTest() {
        h1.addValue("Credit Card", 5004.34f, LocalDate.parse("2007-12-08"));
        //TODO: write assert test to compare
    }

    @Test (expected = IllegalArgumentException.class)
    public void AddValueNullAccount() {
        h1.addValue(null, 5004.34f, LocalDate.parse("2007-12-08"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void AddValueNullDate() {
        h1.addValue("Credit Card", 5004.34f, null);
    }



}
