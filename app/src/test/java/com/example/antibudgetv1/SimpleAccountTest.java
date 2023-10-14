package com.example.antibudgetv1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.antibudgetv1.model.IAccount;
import com.example.antibudgetv1.model.ITransaction;
import com.example.antibudgetv1.model.SimpleAccount;
import com.example.antibudgetv1.model.SimpleTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleAccountTest {

    IAccount a1;
    IAccount a2;
    List<ITransaction> LoT1;
    List<ITransaction> LoT2;
    ITransaction t1;
    ITransaction t2;
    ITransaction t3;

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
    }

    @After
    public void end() {
        start();
    }

    @Test
    public void testSimpleAccountConstructorValid() {
        assertEquals(a1.getName(), "Savings Account");
        assertEquals(a1.getDescription(), "High Yield Savings");
        assertEquals(a2.getName(), "Credit Card");
        assertEquals(a2.getDescription(), "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleAccountConstructorNullName(){
        IAccount a3 = new SimpleAccount(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleAccountConstructorNullDescription(){
        IAccount a3 = new SimpleAccount("name", null, LoT1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleAccountConstructorNullTransactions(){
        LoT1.add(null);
        IAccount a3 = new SimpleAccount("name", "description", LoT1);
    }

    @Test
    public void testSetName() {
        a1.setName("World Bank Savings");
        assertEquals(a1.getName(),"World Bank Savings");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNameInvalid() {
        a1.setName(null);
    }

    @Test
    public void testSetDescription() {
        a1.setDescription("New Description");
        assertEquals(a1.getDescription(), "New Description");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetDescriptionNull() {
        a1.setDescription(null);
    }

    @Test
    public void testAddTransaction() {
        a1.addTransaction(t3);
        assertTrue(a1.hasTransactionOfName(t3.getName()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddTransactionNull() {
        a1.addTransaction(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddTransactionAlreadyIncluded() {
        a1.addTransaction(t1);
    }

    @Test
    public void testAddTransactions() {
        a1.addTransactions(LoT2);
        assertTrue(a1.hasTransaction(t3));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddTransactionsContainsNull() {
        LoT2.add(null);
        a1.addTransactions(LoT2);
    }

    @Test
    public void testRemoveTransaction() {
        assertTrue(a1.hasTransaction(t1));
        a1.removeTransaction(t1);
        assertFalse(a1.hasTransaction(t1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemoveTransactionNull() {
        a1.removeTransaction(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemoveTransactionNotFound() {
        a1.removeTransaction(t3);
    }

    @Test
    public void testGetTransactionCopy(){
        ITransaction t4 = a1.getTransactionCopy("first transaction");
        assertEquals(t4, a1.getTransaction(t1));
        t4.setName("New Name");
        assertNotEquals(t4, a1.getTransaction(t1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetTransactionCopyInvalid() {
        a1.getTransactionCopy("invalid transaction");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetTransactionCopyNull() {
        a1.getTransactionCopy((String) null);
    }

    @Test
    public void testGetTransaction() {
        ITransaction t4 = a1.getTransaction(t1);
        assertEquals(t4, t1);
        t4.setName("New Name");
        assertEquals(t4, t1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetTransactionInvalid() {
        a1.getTransaction(t3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetTransactionNull() {
        a1.getTransaction(null);
    }

    @Test
    public void testHasTransactionOfName() {
        assertTrue(a1.hasTransactionOfName(t1.getName()));
        assertFalse(a1.hasTransactionOfName(t3.getName()));
    }

    @Test
    public void testHasTransaction() {
        assertTrue(a1.hasTransaction(t1));
        assertFalse(a1.hasTransaction(t3));
    }

    @Test
    public void testGetTransactions() {
        List<ITransaction> actual = a1.getTransactions();
        for (ITransaction t: LoT1) {
            assertTrue(actual.contains(t));
        }
        for (ITransaction t: actual) {
            assertTrue(LoT1.contains(t));
        }
        IAccount emptyAccount = new SimpleAccount("new account");
        assertTrue(emptyAccount.getTransactions().isEmpty());
    }
}
