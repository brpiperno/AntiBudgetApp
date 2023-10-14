package com.example.antibudgetv1;

import com.example.antibudgetv1.model.ITransaction;
import com.example.antibudgetv1.model.SimpleTransaction;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class SimpleTransactionTest {
    ITransaction t1;
    ITransaction t2;
    ITransaction t3;

    float TEST_DELTA = 0f;

    @Before
    public void start() {
        t1 = new SimpleTransaction("Rent", -500f, "Paid on the first");
        t2 = new SimpleTransaction("Gas", -30f);
        t3 = new SimpleTransaction("Gas", -30f);
    }

    @Test
    public void testConstructorsValid() {
        assertEquals(t1.getName(), "Rent");
        assertEquals(t1.getValue(), -500f, TEST_DELTA);
        assertEquals(t1.getDescription(), "Paid on the first");
        assertEquals(t2.getDescription(),"");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorsInvalidName() {
        ITransaction t = new SimpleTransaction(null, 40f, "");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorsInvalidDescription() {
        ITransaction t = new SimpleTransaction("t", 0f, null);
    }

    @Test
    public void testSetName() {
        t1.setName("New Name");
        assertEquals(t1.getName(), "New Name");
    }

    @Test
    public void testSetValue() {
        t1.setValue(0f);
        assertEquals(t1.getValue(), 0f, TEST_DELTA);
    }

    @Test
    public void testSetDescription() {
        t1.setDescription("New Description");
        assertEquals(t1.getDescription(), "New Description");
    }

    @Test
    public void testEquals() {
        assertNotEquals(t1, t2);
        assertEquals(t2, t3);
    }

    @Test
    public void testCopy() {
        t3 = t1.copy();
        assertEquals(t1, t3);

    }
}
