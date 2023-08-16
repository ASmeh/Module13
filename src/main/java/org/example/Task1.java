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
        try {
            // Створення нового об'єкта
            String newUserJson = "{\"name\": \"John Doe\", \"username\": \"johndoe\"}";
            String createdUserJson = createObject("https://jsonplaceholder.typicode.com/users", newUserJson);
            System.out.println("Створено об'єкт: " + createdUserJson);

            // Оновлення об'єкту
            String updatedUserJson = "{\"id\": 1, \"name\": \"Updated Name\", \"username\": \"updatedusername\"}";
            String updatedUserResponse = updateObject("https://jsonplaceholder.typicode.com/users/1", updatedUserJson);
            System.out.println("Оновлено об'єкт: " + updatedUserResponse);

            // Видалення об'єкту
            int deleteStatus = deleteObject("https://jsonplaceholder.typicode.com/users/1");
            System.out.println("Статус видалення об'єкту: " + deleteStatus);

            // Отримання інформації про всіх користувачів
            String usersJson = getUsers("https://jsonplaceholder.typicode.com/users");
            System.out.println("Всі користувачі: " + usersJson);

            // Отримання інформації про користувача за id
            String userJson = getUserById("https://jsonplaceholder.typicode.com/users/1");
            System.out.println("Користувач з id 1: " + userJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String createObject(String url, String json) throws Exception {
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.getOutputStream().write(json.getBytes());

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } else {
            throw new Exception("Створення об'єкту не вдалося. Код відповіді: " + responseCode);
        }
    }

    public static String updateObject(String url, String json) throws Exception {
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.getOutputStream().write(json.getBytes());

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } else {
            throw new Exception("Оновлення об'єкту не вдалося. Код відповіді: " + responseCode);
        }
    }

    public static int deleteObject(String url) throws Exception {
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("DELETE");

        int responseCode = connection.getResponseCode();
        return responseCode;
    }

    public static String getUsers(String url) throws Exception {
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } else {
            throw new Exception("Помилка при отриманні інформації про всіх користувачів. Код відповіді: " + responseCode);
        }
    }

    public static String getUserById(String url) throws Exception {
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } else {
            throw new Exception("Помилка при отриманні інформації про користувача. Код відповіді: " + responseCode);
        }
    }
}
