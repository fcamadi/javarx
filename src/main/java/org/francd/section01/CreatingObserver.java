package org.francd.section01;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class CreatingObserver {

    public static void main(String[] args) {

        // as an anonymous inner class implementing the 4 methods of the interface (ObservableAndObserver example)

        // passing lambdas for the 3 event methods of Observer interface
        Observable<String> source = Observable.just("bla", "blabla", "blablabla");

        Disposable disposable = source.subscribe(
                i -> System.out.println(i),
                Throwable::printStackTrace,
                () -> System.out.println("Completed"));    // bla blabla blablabla Completed

        System.out.println("Disposable disposed?: " + disposable.isDisposed());  // true

        // passing 2 lambdas
        source.subscribe(
                i -> System.out.println(i),
                Throwable::printStackTrace);    // bla blabla blablabla

        // passing 1 lambda
        source.subscribe(
                i -> System.out.println(i));    // bla blabla blablabla
    }
}
