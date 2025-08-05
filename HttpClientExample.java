package org.example;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.List;

public class HttpClientExample {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Выполняем GET-запрос
            HttpGet request = new HttpGet(BASE_URL + "todos");
            HttpResponse response = httpClient.execute(request);

            // Проверяем статус ответа
            if (response.getStatusLine().getStatusCode() == 200) {
                String responseBody = EntityUtils.toString(response.getEntity());

                // Парсим JSON в список объектов
                CollectionType listType = objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, Todo.class);
                List<Todo> todos = objectMapper.readValue(responseBody, listType);

                // Выводим первые 5 задач
                for (int i = 0; i < 5 && i < todos.size(); i++) {
                    Todo todo = todos.get(i);
                    System.out.println("ID: " + todo.getId() + ", Title: " + todo.getTitle());
                }
            } else {
                System.out.println("Ошибка при выполнении запроса: " +
                        response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
