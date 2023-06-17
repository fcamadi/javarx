package org.francd.section2.concurrency_parallelization;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ComputationalScheduler {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Number of available cores: "+
            Runtime.getRuntime().availableProcessors());

        Observable<String> source = Observable.just("anda ya", "vamos hombre", "que dices tia", "ni loco", "me tomas el pelo")
                        .subscribeOn(Schedulers.computation());

        source.subscribe( e -> computeSomething());
        source.subscribe( e -> computeSomething());
        source.subscribe( e -> computeSomething());
        source.subscribe( e -> computeSomething());
        source.subscribe( e -> computeSomething());

        Thread.sleep(5000);
    }

    public static void computeSomething() throws InterruptedException {
        Thread.sleep(1000); //to simulate the duration of the computation
        System.out.println("Computation done: " + Thread.currentThread().getName());
    }
}
