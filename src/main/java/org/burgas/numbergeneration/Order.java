package org.burgas.numbergeneration;

public record Order(String product, double cost) {

    @Override
    public String toString() {
        return "Order{" +
               "product='" + product + '\'' +
               ", cost=" + cost +
               '}';
    }
}
