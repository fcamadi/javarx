package org.francd.section01;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class ObserverVariants {

    public static void main(String[] args) {

        // Single observer
        Observable<String> source = Observable.just("Fulano", "Mengano", "Zutano");

        source.first("Name")
                .subscribe(System.out::println);   // -> Fulano

        Single.just("Mengano")
                .subscribe(System.out::println);   // -> Mengano


        // Maybe observer

        source.firstElement()  // -> public final @NonNull io.reactivex.rxjava3.core.Maybe<T> firstElement()
                .subscribe(System.out::println);   // -> Fulano

        Observable<String> emptyObservable = Observable.empty();

        emptyObservable.firstElement()
                .subscribe(System.out::println,
                        e -> e.getLocalizedMessage(),
                        () -> System.out.println("Completed"));     // -> Completed


        // Completable observer
        // (it does not emit anything, it is only concerned about the success or failure
        // of the executed action).

        Completable completable = Completable.complete();

        completable.subscribe( () -> System.out.println("Nothing done!")); // -> Nothing done

        // another example a bit more complicated

        Completable.fromRunnable( () -> System.out.println("Some process executing"))
                .subscribe( () -> System.out.println("Process executed ok"));

    }
}
