package com.goit.Task1;
/**

 Дан текстовый файл file.txt, который содержит список номеров телефоном (один на линии). Необъодимо написать метод, который будет считывать файл и выводить в консоль все валидные номера телефонов.
 Предполагаем, что "валидный" номер телефона - это строка в одном из двух форматов: (xxx) xxx-xxxx или xxx-xxx-xxxx (х обозначает цифру).
 Пример:
 Для файла file.txt со следующим содержанием:

 987-123-4567

 123 456 7890

 (123) 456-7890

 Метод должен вывести на экран

 987-123-4567

 (123) 456-7890

 */

import java.io.FileReader;
import java.io.IOException;

public class TaskRunner1 {
    public static void main(String[] args) {
        try(FileReader reader = new FileReader("src/main/java/com/goit/txt/Task1/file.txt")) {
            int c;
            String data = "";

            while((c =reader.read())!=-1){
                data += String.valueOf((char) c);
            }
            String[] inputData = data.split("\\n");

            for(int i = 0; i < inputData.length; i++){
                String string = inputData[i].strip();
                String regex1 = "\\d{3}[-.]?\\d{3}[-.]?\\d{4}";
                String regex2 = "[(.]\\d{3}[).]\\s\\d{3}[-.]\\d{4}";
                if (string.matches(regex1)) {
                    System.out.println(string);
                }
                if (string.matches(regex2)) {
                    System.out.println(string);
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
}
