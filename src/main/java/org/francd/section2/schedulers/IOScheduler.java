package org.francd.section2.schedulers;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class IOScheduler {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Number of available cores: "+
            Runtime.getRuntime().availableProcessors());

        Observable<String> source = Observable.just("anda ya", "vamos hombre", "que dices tia", "ni loco", "me tomas el pelo")
                        .subscribeOn(Schedulers.io());

        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());

        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());

        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());

        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());
        source.subscribe( e -> ioOperation());
        // here we get as many threads as we want!

        Thread.sleep(5000);
    }

    public static void ioOperation() throws InterruptedException {
        Thread.sleep(1000); //to simulate the duration of the computation
        System.out.println("Computation done: " + Thread.currentThread().getName());
    }
}
