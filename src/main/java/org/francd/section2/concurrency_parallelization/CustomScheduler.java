package org.francd.section2.concurrency_parallelization;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomScheduler {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Number of available cores: "+
            Runtime.getRuntime().availableProcessors());

        ExecutorService executorService = Executors.newFixedThreadPool(4); // 4 < 5

        Scheduler scheduler = Schedulers.from(executorService);

        Observable<String> source = Observable.just("anda ya", "vamos hombre", "que dices tia", "ni loco", "me tomas el pelo")
                .subscribeOn(scheduler)
                .doFinally(executorService::shutdown);

        source.subscribe( e -> computeSomething());
        source.subscribe( e -> computeSomething());
        source.subscribe( e -> computeSomething());
        source.subscribe( e -> computeSomething());
        source.subscribe( e -> computeSomething());

        //Thread.sleep(5000);  the execution goes and goes and goes ...
        // we have to call doFinally on the observable above to finish the execution

    }

    public static void computeSomething() throws InterruptedException {
        Thread.sleep(1000); //to simulate the duration of the computation
        System.out.println("Computation done: " + Thread.currentThread().getName());
    }
}
