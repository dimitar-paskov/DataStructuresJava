package core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import interfaces.Buffer;
import model.Invoice;
import model.User;

public class PerformanceTest {

    @Test
    public void createComputer_with_10000_entries() {
        Buffer buffer = new Loader();
        for (int i = 0; i < 10000; i++) {
            buffer.add(new Invoice(i, i + 10));
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000 - 10; i++) {
            buffer.swap(new Invoice(i, i + 10), new User(i + 1, i + 0));
        }
        long stop = System.currentTimeMillis();
        long elapsedTime = stop - start;
        System.out.println(elapsedTime);
        assertTrue(elapsedTime <= 450);

    }
}
