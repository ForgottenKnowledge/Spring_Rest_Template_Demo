package com.example.spring_rest_template_demo.controller;

import com.example.spring_rest_template_demo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestController {
    private final RestTemplate restTemplate;
    private String URL = "http://94.198.50.185:7081/api/users";
    private StringBuilder code = new StringBuilder();

    public RestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public StringBuilder getCode() {
        return code;
    }

    /**
     * Получение всех пользователей
     *
     * @param requestEntity
     * @return куки
     */
    public List<String> getAllUsers(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,
                HttpMethod.GET,
                requestEntity,
                String.class);
        System.out.println("Статус ответа: " + responseEntity.getStatusCode());
        System.out.println("Тело ответа: " + responseEntity.getBody());
        return responseEntity.getHeaders().get("Set-Cookie");
    }

    /**
     * Добавление пользователя
     *
     * @param requestEntity
     */
    public void addUser(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,
                HttpMethod.POST,
                requestEntity,
                String.class);
        System.out.println("Статус ответа: " + responseEntity.getStatusCode());
        System.out.println("Тело ответа: " + responseEntity.getBody());
        code.append(responseEntity.getBody());
    }

    /**
     * Редактирование пользователя
     *
     * @param requestEntity
     */
    public void editUser(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,
                HttpMethod.PUT,
                requestEntity,
                String.class);
        System.out.println("Статус ответа: " + responseEntity.getStatusCode());
        System.out.println("Тело ответа: " + responseEntity.getBody());
        code.append(responseEntity.getBody());
    }

    /**
     * Удаление пользователя
     *
     * @param requestEntity
     * @param id
     */
    public void deleteUser(HttpEntity<User> requestEntity, int id) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id,
                HttpMethod.DELETE,
                requestEntity,
                String.class);
        System.out.println("Статус ответа: " + responseEntity.getStatusCode());
        System.out.println("Тело ответа: " + responseEntity.getBody());
        code.append(responseEntity.getBody());
    }
}
