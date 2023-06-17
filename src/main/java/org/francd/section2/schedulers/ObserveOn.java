package org.francd.section2.schedulers;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ObserveOn {
    public static void main(String[] args) throws InterruptedException {

        Observable.just("Pasta", "Fries", "Pizza", "Currywurst", "Burger")
                .subscribeOn(Schedulers.computation())
                .doOnNext( e -> System.out.println(Thread.currentThread().getName()))
                .map(e -> e.toUpperCase())
                .observeOn(Schedulers.newThread())
                .doOnNext( e -> System.out.println(Thread.currentThread().getName()))
                .filter(e -> e.startsWith("P"))
                .observeOn(Schedulers.io())
                .doOnNext( e -> System.out.println(Thread.currentThread().getName()))
                .subscribe( e -> print(e));

        Thread.sleep(5000);
    }

    private static void print(String e) {
        System.out.println(e + " printed by: " + Thread.currentThread().getName());
    }
}
