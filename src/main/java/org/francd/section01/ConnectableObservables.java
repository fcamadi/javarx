package org.francd.section01;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class ConnectableObservables {

    public static void main(String[] args) throws InterruptedException {

        @NonNull
        ConnectableObservable<Long> fromInterval = Observable.interval(1, TimeUnit.SECONDS).publish();

        fromInterval.connect();

        fromInterval.subscribe(System.out::println);  // the first observer starts getting the data: 0 1 2

        Thread.sleep(3000);

        fromInterval.subscribe(System.out::println);  // now the 2 observers write to the output: 3 3 4 4 5 5 6 6

        Thread.sleep(4000);
    }

}
