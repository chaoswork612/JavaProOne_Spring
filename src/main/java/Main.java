import config.AppConfig;
import model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.clearUsers();

        User user1 = new User(1L, "john1_doe1");
        System.out.println(userService.createUser(user1));

        User user2 = new User(2L, "john2_doe2");
        System.out.println(userService.createUser(user2));

        User user3 = new User(3L, "john3_doe3");
        System.out.println(userService.createUser(user3));

        User retrievedUser = userService.getUserById(user2.getId());
        System.out.println(retrievedUser);

        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(System.out::println);

        userService.deleteUser(1L);
    }
}