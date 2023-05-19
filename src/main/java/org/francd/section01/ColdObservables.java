package org.francd.section01;

import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.List;

public class ColdObservables {

    public static void main(String[] args) {


        List<Integer> list1 = new ArrayList<>();
        list1.add(20);
        list1.add(30);
        list1.add(40);

        Observable<Integer> source = Observable.fromIterable(list1);

        source.subscribe(e -> System.out.println(e));  // 20 30 40

        System.out.println("----- Data modified ------");

        getModifiedData(list1);

        source.subscribe(e -> System.out.println(e),   // 20 30 40 50
                        Throwable::printStackTrace);    // just to show very clearly it is another observer
    }

    private static void getModifiedData(List<Integer> list) {
        list.add(50);

    }
}
