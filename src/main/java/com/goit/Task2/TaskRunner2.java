package com.goit.Task2;

/**
 Дан текстовый файл file.txt, необходимо считать файл в список объектов User и создать новый файл user.json.
 Предполагаем, что каждая строка содержит одинаковое количество "колонок", разделенный пробелом.
 Пример:
 Для файла file.txt со следующим содержанием:

 name age
 alice 21
 ryan 30

 Новый файл должен иметь следующий вид:

 [
 {
 "name": "alice",
 "age":21
 },
 {
 "name": "ryan",
 "age":30
 }
 ]
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRunner2 {
    private static File inputFile = new File("src/main/java/com/goit/txt/Task2/file.txt");
    private static File resultFile = new File("src/main/java/com/goit/Task2/user.json");


    public static void main(String[] args) {
        List<String> personsStringList = new ArrayList<>();
        personsStringList = personReader(inputFile);
        List<User> personsList= personMaker(personsStringList);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(personsList);
        fileWriter(resultFile, json);
    }

    private static List<String> personReader(File file) {
        List<String> persons = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader((new FileReader(file)))) {
            persons.add(bufferedReader.readLine());
            String number = new String();
            while ((number = bufferedReader.readLine()) != null) {
                persons.add(number);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return persons;
    }

    private static List<User> personMaker (List<String> personsStringList) {
        List<User> personsList = new ArrayList<>();
        for (int i = 1; i < personsStringList.size(); i++) {
            String[] personString = personsStringList.get(i).split(" ");
            personsList.add(new User(personString[0], Integer.parseInt(personString[1])));
        }

        return personsList;
    }

    private static void fileWriter (File file, String string) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(string);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}