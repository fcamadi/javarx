package org.francd.section2.operators;

import io.reactivex.rxjava3.core.Observable;

import java.util.List;

public class OperatorsInAction {

    public static void main(String[] args) {

        Observable<Employee> observable = Observable.just(
          new Employee(1, "Fulano",120.000, 9.9),
          new Employee(2, "Mengana",20.000, 4.0),
          new Employee(10, "Zutano",10.000, 3.0),
          new Employee(20, "Fofo",90.000, 7.9),
          new Employee(21, "Fulvia",124.000, 9.0),
          new Employee(90, "Astra",180.000, 8.0)
        );

        // take the 3 best rated employees (their rating must be > 5.0)
        observable
                .filter( e -> e.getRating() > 5.0)
                .sorted( (e1,e2) -> Double.compare(e2.getRating(), e1.getRating()) )
                .map( e -> e.getName())
                .take(3)
                .subscribe(System.out::println);   // -> Fulano Fulvia Astra


        // get the total expenses until a certain month
        List<Integer> expensesList = List.of(200,500,300,340,129,234,999,1030,3400,890,990,790);

        int resultFor4 = calculateExpensesForMonthN(expensesList, 4);
        System.out.println("Expenses for first 4 months: "+resultFor4);     // -> 1340
        int resultFor6 = calculateExpensesForMonthN(expensesList, 6);
        System.out.println("Expenses for first 6 months: "+resultFor6);     // -> 1703

        // calculate the total expenses
        Observable.fromIterable(expensesList)
                .reduce((a,b) -> a+b)
                .subscribe(System.out::println);  // -> 9802

        int resultTotal = calculateExpensesForMonthN(expensesList, 12);
        System.out.println("Expenses for whole year: "+resultTotal);     // -> 9802
    }

    private static int calculateExpensesForMonthN(List<Integer> expensesList, int n) {
        Observable<Integer> scan = Observable.fromIterable(expensesList)
                .take(n)
                .scan((a, b) -> a + b);//.subscribe(System.out::println);   // 200 700 1000 1340 ...

        return scan.blockingLast();
    }


}
