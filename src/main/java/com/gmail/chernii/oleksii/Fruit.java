package com.gmail.chernii.oleksii;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Space on 27.03.2019.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fruit {
    private FruitType type;
    private int shelfLife;
    private Date date;
    private int price;
}
