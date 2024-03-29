package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDao {

   void add(User user);

   List<User> listUsers();

    void add(User user, Car car);

    @Transactional(readOnly = true)
    List<User> findUsersByCarSeries(Integer series);

    @Transactional(readOnly = true)
    List<User> findUsersByCarModelAndSeries(String model, Integer series);
}
