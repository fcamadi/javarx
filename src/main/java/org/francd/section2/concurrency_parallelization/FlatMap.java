package org.francd.section2.concurrency_parallelization;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.time.LocalTime;

public class FlatMap {
    public static void main(String[] args) throws InterruptedException {

        Observable.just("Pasta", "Fries", "Pizza", "Currywurst", "Burger")
                        .flatMap( e -> Observable.just(e)
                                .subscribeOn(Schedulers.computation())
                                .map( r -> computeIntensive(r)) )
                .subscribe((System.out::println));

        Thread.sleep(5000);
    }

    // Imagine this method takes a long time, so we create an observable for each element in the above list of fast food
    // by using flatMap on the original observable (out of its emissions)
    public static String computeIntensive(String e) {
        return (e + " printed by: " + Thread.currentThread().getName() + " at: "+ LocalTime.now());
    }
}
