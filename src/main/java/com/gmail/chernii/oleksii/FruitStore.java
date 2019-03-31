package com.gmail.chernii.oleksii;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Space on 27.03.2019.
 */

public class FruitStore {
    @JsonIgnore
    private static final Logger logger = Logger.getLogger(FruitStore.class);
    private List<Fruit> fruits;

    public FruitStore() {
        fruits = new ArrayList<>();
    }

    public void add(Fruit fruit) {
        fruits.add(fruit);
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(List<Fruit> fruitList) {
        this.fruits = fruitList;
    }

    public void addFruits(String pathToJsonFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToJsonFile))) {
            ObjectMapper objectMapper = getMapper();
            FruitStore fruitsToAdd = objectMapper.readValue(bufferedReader, FruitStore.class);
            fruits.addAll(fruitsToAdd.getFruits());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void save(String pathToJsonFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToJsonFile))) {
            ObjectMapper objectMapper = getMapper();
            objectMapper.writeValue(bufferedWriter, fruits);
            bufferedWriter.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void load(String pathToJsonFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToJsonFile))) {
            ObjectMapper objectMapper = getMapper();
            this.fruits = objectMapper.readValue(bufferedReader, new TypeReference<List<Fruit>>() {
            });
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public List<Fruit> getAvailableFruits(Date date) {
        return fruits.stream().filter(e -> spoiledFruitDate(e).after(date)).collect(Collectors.toList());
    }

    public List<Fruit> getSpoiledFruits(Date date) {
        return fruits.stream().filter(e -> spoiledFruitDate(e).before(date)).collect(Collectors.toList());
    }

    public List<Fruit> getSpoiledFruits(Date date, FruitType type) {
        return getSpoiledFruits(date).stream().filter(e -> e.getFruitType() == type).collect(Collectors.toList());
    }


    public List<Fruit> getAddedFruits(Date date) {
        return fruits.stream().filter(e -> e.getDate().equals(date)).collect(Collectors.toList());
    }

    public List<Fruit> getAddedFruits(Date date, FruitType type) {
        return getSpoiledFruits(date).stream().filter(e -> e.getFruitType() == type).collect(Collectors.toList());
    }

    private Date spoiledFruitDate(Fruit fruit) {
        Date date = fruit.getDate();
        int shelfLife = fruit.getShelfLife();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, shelfLife);
        return calendar.getTime();
    }

    private ObjectMapper getMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        objectMapper.setDateFormat(dateFormat);
        return objectMapper;
    }
}
