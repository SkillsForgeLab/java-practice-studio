package com.sree.concurrency;

import com.sree.common.DemoSupport;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class ConcurrencyMain {
    private ConcurrencyMain() {
    }

    public static void main(String[] args) throws Exception {
        DemoSupport.printHeader("Concurrency");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Future<Integer> answer = executor.submit(() -> 6 * 7);
            System.out.println("Result from worker: " + answer.get());
        } finally {
            executor.shutdown();
        }
    }
}
