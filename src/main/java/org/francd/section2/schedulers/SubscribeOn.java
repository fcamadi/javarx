package org.francd.section2.schedulers;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SubscribeOn {

    public static void main(String[] args) throws InterruptedException {

        Observable.just("Pasta", "Fries", "Pizza", "Currywurst", "Burger")
                .subscribeOn(Schedulers.computation())    // this scheduler gets all
                .map(e -> e.toUpperCase())
                .subscribeOn(Schedulers.newThread())
                .filter(e -> e.startsWith("P"))
                .subscribe( e -> print(e));

        Thread.sleep(5000);
    }

    private static void print(String e) {
        System.out.println(e + " printed by: " + Thread.currentThread().getName());
    }
}
