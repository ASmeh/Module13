package org.example;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Task3 {
    public static void main(String[] args) throws IOException {
        JSONArray tasks = getAllopenedTasks(1);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tasks.length(); i++) {
            boolean completed = tasks.getJSONObject(i).getBoolean("completed");
            if(!completed) {
                sb.append(tasks.getJSONObject(i).toString());
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }
    private static JSONArray getAllopenedTasks(int userId) throws IOException {
        String apiUrl = "https://jsonplaceholder.typicode.com/users/" + userId + "/todos";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(apiUrl);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        String json = EntityUtils.toString(httpEntity);
        return new JSONArray(json);
    }
}
