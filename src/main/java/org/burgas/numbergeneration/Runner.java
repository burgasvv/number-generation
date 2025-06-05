package org.burgas.numbergeneration;

import java.util.*;
import java.util.stream.Collectors;

public final class Runner {

    public static void main(String[] args) {

        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        // Группировка по продуктам и нахождение общей стоимости по группам
        Map<String, Optional<Double>> collect = orders.stream()
                .collect(
                        Collectors.groupingBy(
                                Order::product,
                                Collectors.mapping(Order::cost, Collectors.reducing(Double::sum))
                        )
                );

        System.out.println(collect);

        // Сортировка по убыванию общей стоимости
        List<Map.Entry<String, Optional<Double>>> list = collect.entrySet().stream()
                .sorted(
                        (o1, o2) ->
                                o2.getValue().orElseThrow().intValue() - o1.getValue().orElseThrow().intValue()
                )
                .toList();

        System.out.println(list);

        List<Order> tempList = orders.stream()
                .sorted((o1, o2) -> (int) (o2.cost() - o1.cost()))
                .limit(3)
                .toList();

        // Выбрать три самых дорогих продукта
        List<String> expensiveProducts = tempList
                .stream()
                .map(Order::product)
                .toList();

        System.out.println(expensiveProducts);

        // Самые дорогие продукты и их стоимость
        Double sum = tempList.stream()
                .map(Order::cost)
                .reduce(Double::sum)
                .orElseThrow();

        Map<List<String>, Double> expensiveProductsAndCost = new HashMap<>(
                Map.of(expensiveProducts, sum)
        );
        System.out.println(expensiveProductsAndCost);
    }
}
