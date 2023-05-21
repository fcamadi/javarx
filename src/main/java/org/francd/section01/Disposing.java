package org.francd.section01;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class Disposing {

    private static final CompositeDisposable disp = new CompositeDisposable();

    public static void main(String[] args) throws InterruptedException {

        Observable<Long> fromInterval = Observable.interval(1, TimeUnit.SECONDS);

        Disposable disposable1 = fromInterval.subscribe(e -> System.out.println("Observer1: " + e));
        Disposable disposable2 = fromInterval.subscribe(e -> System.out.println("Observer2: " + e));
        Disposable disposable3 = fromInterval.subscribe(e -> System.out.println("Observer3: " + e));

        Thread.sleep(5000);    // 1 1 2 2 3 3 ...

        disposable1.dispose();
        System.out.println("--------------");

        Thread.sleep(5000);   // only observer2 will emit ...

        disp.addAll(disposable2, disposable3);
        disp.dispose();  //nothing more is printed

        Thread.sleep(5000);
    }
}
