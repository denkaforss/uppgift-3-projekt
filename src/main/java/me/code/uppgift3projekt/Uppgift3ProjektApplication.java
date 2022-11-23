package me.code.uppgift3projekt;

import me.code.uppgift3projekt.data.User;
import me.code.uppgift3projekt.repository.UserRepository;
import me.code.uppgift3projekt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Uppgift3ProjektApplication {

    public static void main(String[] args) {
        SpringApplication.run(Uppgift3ProjektApplication.class, args);
    }
    @Bean
    CommandLineRunner initUserMap(UserService service) {
        return args -> {
            service.register("AllanBallan", "password4");
            service.getAll().forEach((user) -> System.out.println(user.getUsername()));
        };
    }

}
