package com.example.spring_rest_template_demo;

import com.example.spring_rest_template_demo.controller.RestController;
import com.example.spring_rest_template_demo.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //Создание ApplicationContext
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        //Получение бина restController'а
        RestController restController = context.getBean("restController", RestController.class);
        //Создание http заголовков
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        // Получение куков из первого запроса
        List<String> cookies = restController.getAllUsers(new HttpEntity<>(httpHeaders));
        System.out.println("Куки: " + cookies);
        // Вставка полученных куков в http заголовок
        httpHeaders.set("Cookie", String.join(";", cookies));

        //Новый пользователь
        User newUser = new User(3L, "James", "Brown", (byte) 30);
        //Создание нового httpEntity с пользователем и куками
        HttpEntity<User> httpEntity = new HttpEntity<>(newUser, httpHeaders);
        //Добавление нового пользователя
        restController.addUser(httpEntity);

        //Замена параметров пользователя
        newUser.setName("Thomas");
        newUser.setLastName("Shelby");
        //Редактирование пользователя
        restController.editUser(httpEntity);

        //Удаление пользователя
        restController.deleteUser(httpEntity, 3);

        //Полученный результат
        System.out.println("Полученный результат: " + restController.getCode());
    }
}