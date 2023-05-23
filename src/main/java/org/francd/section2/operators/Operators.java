package org.francd.section2.operators;

import io.reactivex.rxjava3.core.Observable;

public class Operators {

    public static void main(String[] args) {

        Observable.just(44, 22, 55, 77, 46, 99)
                .filter( e -> e%2 == 0)
                .toSortedList()
                .subscribe(System.out::println);
    }
}
