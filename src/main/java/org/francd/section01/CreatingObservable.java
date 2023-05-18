package org.francd.section01;

import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.List;

public class CreatingObservable {

    public static void main(String[] args) throws InterruptedException {

        // create
        Observable<Integer> source = Observable.create(
                e -> {
                    e.onNext(10);
                    e.onNext(100);
                    e.onNext(1000);
                    e.onComplete();
                }
        );

        source.subscribe(System.out::println);    // 10 100 1000

        // just
        Observable<Integer> just = Observable.just(33, 66, 99);   //from 1 to 9 varargs
        just.subscribe(System.out::println);      // 33 66 99

        //  from iterable
        List<String> list1 = List.of("blabla", "fof", "mec");

        Observable<String> fromIterable = Observable.fromIterable(list1);
        fromIterable.subscribe(System.out::println);      // blabla fof mec

        // from a range
        Observable<Integer> fromRange = Observable.range(5,5);
        fromRange.subscribe(System.out::println);      // 5 6 7 8 9

        // from interval

        // from future

        // ...

        // defer
        List<String> deferList = new ArrayList<>();
        deferList.add("A1");
        deferList.add("B2");
        Observable<String> deferObservable = Observable.defer(
                () -> Observable.fromIterable(deferList)
        );
        deferObservable.subscribe(System.out::println);  // A1 B2

        deferList.add("C3");
        deferObservable.subscribe(System.out::println);  // A1 B2 C3  <- everything!


    }

}
