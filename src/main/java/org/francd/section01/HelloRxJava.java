package org.francd.section01;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

public class HelloRxJava {

    public static void main(String[] args) {

        // public static <@NonNull T> @NonNull Observable<T> create(@NonNull ObservableOnSubscribe<T> source) {
        Observable<String> source = Observable.create(
                e -> {
                    e.onNext("Hello");      // generate streams of events
                    e.onNext("RxJava");
                }
        );

        source.subscribe(e -> System.out.println("Observer 1: "+e));  // it subscribes to itself (2 times .. what we want),
        source.subscribe(e -> System.out.println("Observer 2: "+e));  // and take each element and print it

    }
}
