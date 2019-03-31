package com.gmail.chernii.oleksii;

import java.util.Date;
/**
 * Created by Space on 27.03.2019.
 */
public class Fruit {
    private FruitType type;
    private int shelfLife;
    private Date date;
    private int price;

    public Fruit() {
    }

    public Fruit(FruitType type, int shelfLife, Date date, int price) {
        this.type = type;
        this.shelfLife = shelfLife;
        this.date = date;
        this.price = price;
    }

    public FruitType getFruitType() {
        return type;
    }

    public void setFruitType(FruitType fruitType) {
        this.type = fruitType;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "type=" + type +
                ", shelfLife=" + shelfLife +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
