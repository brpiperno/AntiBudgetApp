package com.example.antibudgetv1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.antibudgetv1.model.budget.IAccount;
import com.example.antibudgetv1.model.budget.IBudget;
import com.example.antibudgetv1.model.budget.ITransaction;
import com.example.antibudgetv1.model.budget.SimpleAccount;
import com.example.antibudgetv1.model.budget.SimpleBudget;
import com.example.antibudgetv1.model.budget.SimpleTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleBudgetTest {

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
    }

    @After
    public void end() {
        start();
    }

    @Test
    public void testConstructorValid() {
        assertEquals(b1.getName(),"test");
        assertEquals(b1.getDescription(), "test budget");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorNullName() {
        b1 = new SimpleBudget(null, "test description");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorNullDescription() {
        b1 = new SimpleBudget("name", null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorNullAccount() {
        LoA1.add(null);
        b1 = new SimpleBudget("name", "description", LoA1);
    }

    @Test
    public void testSetName() {
        assertEquals(b1.getName(), "test");
        b1.setName("new");
        assertEquals(b1.getName(), "new");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetNameNull() {
        b1.setName(null);
    }

    @Test
    public void testSetDescription() {
        assertEquals(b1.getDescription(), "test budget");
        b1.setDescription("new");
        assertEquals("new", b1.getDescription());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetDescriptionNull() {
        b1.setDescription(null);
    }

    @Test
    public void testAddAccount(){
        IAccount a = new SimpleAccount("third account");
        b1.addAccount(a);
        assertTrue(b1.hasAccount(a));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddAccountNull() {
        b1.addAccount(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddAccountPresent(){
        b1.addAccount(a1);
    }

    @Test
    public void testDeleteAccount(){
        assertTrue(b1.hasAccount(a1));
        b1.deleteAccount(a1);
        assertFalse(b1.hasAccount(a1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDeleteAccountNull(){
        b1.deleteAccount(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDeleteAccountNotPresent(){
        b1.deleteAccount(new SimpleAccount("brand new account"));
    }

    @Test
    public void testGetAccountCopy(){
        IAccount a3 = b1.getAccountCopy(a1.getName());
        assertEquals(a3, a1);
        a3.setName("new name");
        assertNotEquals(a3, a1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetAccountCopyNull() {
        b1.getAccountCopy(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetAccountCopyNotPresent() {
        b1.getAccountCopy("not present in this budget");
    }

    @Test
    public void testGetAccount() {
        IAccount a3 = b1.getAccount(a1);
        assertEquals(a3, a1);
        a3.setName("new name");
        assertEquals(a3, a1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetAccountNull() {
        b1.getAccount(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetAccountNotPresent() {
        b1.getAccount(new SimpleAccount(""));
    }

    @Test
    public void testHasAccountWithName(){
        assertTrue(b1.hasAccountWithName(a1.getName()));
        assertFalse(b1.hasAccountWithName("Account not in use"));
    }

    @Test
    public void testHasAccount(){
        assertTrue(b1.hasAccount(a1));
        assertFalse(b1.hasAccount(new SimpleAccount("Account not in use")));
    }

    @Test
    public void testGetCopyOfAccounts(){
        List<IAccount> copies = b1.getCopyOfAccounts();
        for (IAccount a : copies) {
            assertTrue(b1.hasAccount(a));
        }
        for (IAccount a : LoA1) {
            assertTrue(b1.hasAccount(a));
        }
        assertTrue(b2.getCopyOfAccounts().isEmpty());
    }

    @Test
    public void testCopy() {
        IBudget b3 = b1.copy();
        assertEquals(b3, b1);
        b3.setName("new name");
        assertNotEquals(b3, b1);
    }

    @Test
    public void testGetAccountNames() {
        List<String> l = b1.getAccountNames();
        assertEquals(2, l.size());
        assertTrue(l.contains("Savings Account"));
        assertTrue( l.contains("Credit Card"));
    }
}
