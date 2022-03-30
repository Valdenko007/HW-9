package com.goit.Task3;

/*
 Напишите метод, который будет подсчитывать частоту каждого слова в файле words.txt.
 Предпалагаем, что:
 words.txt содержит только слова в нижнем регистре, разделенные пробелом
 Каждое слово содержит только символы-буквы в нижнем регистре.
 Слова разделены одим или несколькими пробелами, либо переносом строки.
 Пример:
 Для файла words.txt со следующим содержанием:
 the day is sunny the the
 the sunny is is
 Метод должен вернуть частоту:
 the 4
 is 3
 sunny 2
 day 1
 Обратите внимание! Вывод на консоль должен быть отсортирован на частоте слов (от наибольшей к наименьшей)
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TaskRunner3 {
    public static void main(String[] args) {
        String Word = "src/main/java/com/goit/txt/Task3/word.txt";

        System.out.println("Word:");
        new TaskRunner3().countWords(new TaskRunner3().readFile(Word));
    }

    public void countWords(String text){
        Map<String, Integer> taskMap = new HashMap<>();
        String[] words = text.split(" ");

        List<String> list = Arrays.asList(words.clone());

        for(String word: list){
            if (!taskMap.containsKey(word)) {
                taskMap.put(word, Collections.frequency(list, word));
            }
        }

        taskMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }

    public String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        File file = new File(filePath);
        try (FileReader reader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(reader)){
            String temp = bufferedReader.readLine();

            while (temp != null){
                sb.append(temp).append(" ");
                temp = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }
}
