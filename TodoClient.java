package org.example;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class TodoClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void createTodo() throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Создаем новую задачу
            Todo newTodo = new Todo("New task", false);

            // Преобразуем объект в JSON
            String jsonBody = objectMapper.writeValueAsString(newTodo);

            // Создаем POST-запрос
            HttpPost request = new HttpPost(BASE_URL + "todos");
            request.setEntity(new StringEntity(jsonBody));
            request.setHeader("Content-Type", "application/json");

            // Выполняем запрос
            HttpResponse response = httpClient.execute(request);

            // Проверяем статус
            if (response.getStatusLine().getStatusCode() == 201) {
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println("Задача успешно создана:");
                System.out.println(responseBody);
            } else {
                System.out.println("Ошибка создания задачи: " +
                        response.getStatusLine().getStatusCode());
            }
        }
    }

    public static void updateTodo(long todoId, String title, boolean completed) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Создаем обновленную задачу
            Todo updatedTodo = new Todo(title, completed);

            // Преобразуем объект в JSON
            String jsonBody = objectMapper.writeValueAsString(updatedTodo);
            System.out.println("Updating data: " + jsonBody);

            // Создаем PUT-запрос
            HttpPut request = new HttpPut(BASE_URL + "todos/" + todoId);
            request.setEntity(new StringEntity(jsonBody));
            request.setHeader("Content-Type", "application/json");

            // Выполняем запрос
            HttpResponse response = httpClient.execute(request);

            // Проверяем статус
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println("Task sucsessfuly update:");
                System.out.println(responseBody);
            } else {
                System.err.println("Delete error. Status: " + statusCode);
                if (response.getEntity() != null) {
                    String errorBody = EntityUtils.toString(response.getEntity());
                    System.err.println("Server response: " + errorBody);
                }
            }
        } catch (IOException e) {
            System.err.println("Произошла ошибка при выполнении запроса: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void deleteTodo(long todoId) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Создаем DELETE-запрос
            HttpDelete request = new HttpDelete(BASE_URL + "todos/" + todoId);

            // Выполняем запрос
            HttpResponse response = httpClient.execute(request);

            // Проверяем статус
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("Task with ID " + todoId + " deleted sucssesfully!");
            } else {
                System.err.println("Ошибка удаления задачи. Статус: " + statusCode);
                if (response.getEntity() != null) {
                    String errorBody = EntityUtils.toString(response.getEntity());
                    System.err.println("Server response: " + errorBody);
                }
            }
        } catch (IOException e) {
            System.err.println("Произошла ошибка при выполнении запроса: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            createTodo();

            updateTodo(1, "Updated task", true);

            deleteTodo(1);
        } catch (IOException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());;
        }
    }
}
