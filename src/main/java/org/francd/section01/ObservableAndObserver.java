package org.francd.section01;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableCreate;

public class ObservableAndObserver {

    public static void main(String[] args) {

        Observable<Integer> source = ObservableCreate.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                try {
                    System.out.println("source onNext");
                    emitter.onNext(10);
                    System.out.println("source onNext");
                    emitter.onNext(100);
                    System.out.println("source completed");
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("Subscribed!");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("onNext: "+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("Exception: "+e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Completed!");
            }
        };

        source.subscribe(observer);
    }

}
