package org.francd.section2.operators;

import io.reactivex.rxjava3.core.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmbiguousOperator {

    public static void main(String[] args) throws InterruptedException {

        Observable<String> obs1 = Observable.interval(1, TimeUnit.SECONDS).take(10)
                .map(e -> "Obs1: "+e);
        Observable<String> obs2 = Observable.interval(800, TimeUnit.MILLISECONDS).take(15)
                .map(e -> "Obs2 :"+e);

        Observable.amb(List.of(obs1,obs2))
                .subscribe(System.out::println);

        Thread.sleep(14000);  // interval executes in separated threads,  so in order to allow
                                 // obs1 and obs2 to print, we have to pause the main thread
    }

}
