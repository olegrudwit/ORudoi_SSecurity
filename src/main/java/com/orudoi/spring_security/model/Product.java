package com.orudoi.spring_security.model;

import javax.persistence.Table;
import java.util.Objects;

/**
 * Product model class.
 * Contains information about the name and price of the product
 *
 * @author Oleg Rudoi
 * @version 1.0 01 Mar 2023
 */
@Table(name = "product")
public class Product {
    private Long id;
    /* name of the product */
    private String name;

    /* price of the product */
    private Double cost;

    public Product(String name, String cost) {
        this.name = name;
        this.cost = Double.parseDouble(cost);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!id.equals(product.id)) return false;
        if (!Objects.equals(name, product.name)) return false;
        return Objects.equals(cost, product.cost);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
