package org.francd.section2.operators;

import io.reactivex.rxjava3.core.Observable;

public class Grouping {


    public static void main(String[] args) {

        Observable<Employee> observable = Observable.just(
                new Employee(1, "Fulano", 120.000, 9.0),
                new Employee(2, "Mengana", 20.000, 4.0),
                new Employee(10, "Zutano", 10.000, 4.0),
                new Employee(20, "Fofo", 90.000, 7.9),
                new Employee(21, "Fulvia", 124.000, 9.0),
                new Employee(90, "Astra", 180.000, 7.9),
                new Employee(20, "Filfa", 90.000, 4.9),
                new Employee(21, "Fasta", 24.000, 9.0),
                new Employee(90, "Landra", 80.000, 7.9)
        );

        /*
        observable.groupBy(e -> e.getRating())
                .flatMapSingle(e -> e.toMultimap(k -> e.getKey(), emp -> emp.getName()))
                .subscribe(System.out::println);            // {4.0=[Mengana, Zutano]}
                                                            // {9.0=[Fulano, Fulvia, Fasta]}
                                                            // {7.9=[Fofo, Astra, Landra]}
                                                            // {4.9=[Filfa]}
        */

        observable.groupBy(e -> e.getRating())
                .flatMapSingle(e -> e.toList())
                .subscribe(System.out::println);
        // [Employee[id=2, name='Mengana', salary=20.0, rating=4.0], Employee[id=10, name='Zutano', salary=10.0, rating=4.0]]
        //...
    }
}
