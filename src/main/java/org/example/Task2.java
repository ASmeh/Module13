package org.example;
/*Завдання 2
Доповніть програму методом, що буде виводити всі коментарі до останнього поста певного користувача і записувати їх у файл.

https://jsonplaceholder.typicode.com/users/1/posts Останнім вважаємо пост з найбільшим id.

https://jsonplaceholder.typicode.com/posts/10/comments

Файл повинен називатись user-X-post-Y-comments.json, де Х - id користувача, Y - номер посту.*/
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.FileWriter;
import java.io.IOException;

public class Task2 {
    public static void main(String[] args) {
        int userId = 1;
        try {
            JSONArray posts = getPostsByUser(userId);
            if(posts.length() > 0) {
                JSONObject lastPost = posts.getJSONObject(posts.length() - 1);
                int postId = lastPost.getInt("id");
                JSONArray comments = getCommentsByPostId(postId);
                writeCommentsToFile(postId,userId,comments);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private static JSONArray getPostsByUser(int userId) throws IOException {
        String url = "https://jsonplaceholder.typicode.com/users/" + userId + "/posts";
        //String url2 = "https://jsonplaceholder.typicode.com/posts?userId=1";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        String json = EntityUtils.toString(httpEntity);

        /*HttpGet httpGet2 = new HttpGet(url2);
        HttpResponse httpResponse2 = httpClient.execute(httpGet);
        HttpEntity httpEntity2 = httpResponse2.getEntity();
        String json2 = EntityUtils.toString(httpEntity2);
        if(json.equals(json2)) {
            int a = 3;
        }*/
        return new JSONArray(json);
    }

    private static JSONArray getCommentsByPostId(int postId) throws IOException {
        String url = "https://jsonplaceholder.typicode.com/posts/" + postId + "/comments";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        String json = EntityUtils.toString(httpEntity);
        return new JSONArray(json);
    }
    private static void writeCommentsToFile(int postID, int userId, JSONArray jsonArray) throws IOException {
        String fileName = "user-" + userId + "-post-" + postID + "-comments.json";
        FileWriter fileWriter = new FileWriter(fileName);
        jsonArray.write(fileWriter);
        //fileWriter.write(jsonArray.toString());
        fileWriter.close();
    }
}
