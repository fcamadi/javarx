package org.francd.section2.operators;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class ZipOperatorVsCombineLatestOperator {

    public static void main(String[] args) throws InterruptedException {

        Observable<String> obs1 = Observable.interval(1, TimeUnit.SECONDS).take(10)
                .map(e -> "Obs1: " + e);
        Observable<String> obs2 = Observable.interval(500, TimeUnit.MILLISECONDS).take(10)
                .map(e -> "Obs2 :" + e);

        Observable.zip(obs1, obs2, (a, b) -> a + " - " + b )
                .subscribe(System.out::println);    // Obs1: 0 - Obs2 :0
                                                    // Obs1: 1 - Obs2 :1
                                                    // ... (the faster waits for the slower one)

        Thread.sleep(11000);
        System.out.println("-----------");

        Observable<String> obs3 = Observable.interval(1, TimeUnit.SECONDS).take(10)
                .map(e -> "Obs3: " + e);
        Observable<String> obs4 = Observable.interval(500, TimeUnit.MILLISECONDS).take(10)
                .map(e -> "Obs4 :" + e);

        Observable.combineLatest(obs3, obs4, (a, b) -> a + " - " + b )
                .subscribe(System.out::println);    // Obs3: 0 - Obs4 :1
                                                    // Obs3: 0 - Obs4 :2
                                                    // Obs3: 1 - Obs4 :2
                                                    // Obs3: 1 - Obs4 :3
                                                    // ... (the faster takes the latest elem. from the slower)

        Thread.sleep(14000);  // interval executes in separated threads,  so in order to allow
        // obs1 and obs2 to print, we have to pause the main thread
    }

}
