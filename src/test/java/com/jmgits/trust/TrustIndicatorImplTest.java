package com.jmgits.trust;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * Created by javmg on 27/03/18.
 */
@RunWith(Parameterized.class)
public class TrustIndicatorImplTest {

    //
    // in a more real scenario I'd use a mock for NodeExtractor (Mockito)

    private TrustIndicator trustIndicator = new TrustIndicatorImpl();

    private List<Transaction> transactions;
    private String from;
    private String to;
    private int expected;

    public TrustIndicatorImplTest(List<Transaction> transactions, String from, String to, int expected) {
        this.transactions = transactions;
        this.from = from;
        this.to = to;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Transaction transaction1 = new Transaction("A", "B");
        Transaction transaction2 = new Transaction("B", "C");
        Transaction transaction3 = new Transaction("C", "D");

        List<Transaction> transactions = asList(transaction1, transaction2, transaction3);

        return asList(new Object[][]{
                {transactions, "A", "B", 1},
                {transactions, "B", "A", 1},
                {transactions, "A", "C", 2},
                {transactions, "A", "D", 3},
                {transactions, "X", "D", 0},
                {transactions, "D", "X", 0},
        });
    }

    @Test
    public void testCalculateTrust() {

        int result = trustIndicator.calculateTrust(from, to, transactions);

        assertEquals(result, expected);
    }
}
