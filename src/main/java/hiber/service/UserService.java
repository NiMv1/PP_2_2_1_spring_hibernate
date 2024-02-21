package hiber.service;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();

    void add(User user, Car car);

    @Transactional(readOnly = true)
    List<User> findUsersByCarModelOrSeries(String model, Integer series);
}
