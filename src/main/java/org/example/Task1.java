package org.example;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;

/*Програма повинна містити методи для реалізації наступного функціоналу:

створення нового об'єкта в https://jsonplaceholder.typicode.com/users. Можливо, ви не побачите одразу змін на сайті. Метод працює правильно, якщо у відповідь на JSON з об'єктом повернувся такий самий JSON, але зі значенням id більшим на 1, ніж найбільший id на сайті.

оновлення об'єкту в https://jsonplaceholder.typicode.com/users. Можливо, ви не побачите одразу змін на сайті. Вважаємо, що метод працює правильно, якщо у відповідь ви отримаєте оновлений JSON (він повинен бути таким самим, що ви відправили).

видалення об'єкта з https://jsonplaceholder.typicode.com/users. Тут будемо вважати коректним результат - статус відповіді з групи 2xx (наприклад, 200).

отримання інформації про всіх користувачів https://jsonplaceholder.typicode.com/users

отримання інформації про користувача за id https://jsonplaceholder.typicode.com/users/{id}*/

public class Task1 {
    private static final String TEST_URL = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args)throws IOException {

        //створення обьекту
        sendPOST();
        //оновлення об'єкту

    }
    private static void sendPOST() throws IOException {
        URL url = new URL(TEST_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        os.write(Files.readAllBytes(new File("D:\\Practice\\java_goit\\Module13\\src\\main\\resources\\users.json").toPath()));
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("POST response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }
    private static void sendPUT() throws IOException {
        URL url = new URL(TEST_URL);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("PUT");
        httpCon.connect();

    }
    private static void sendDELETE() throws IOException {
        URL url = new URL(TEST_URL);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestProperty(
                "Content-Type", "application/x-www-form-urlencoded" );
        httpCon.setRequestMethod("DELETE");
        httpCon.connect();
    }
}
