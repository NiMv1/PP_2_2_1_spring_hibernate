package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    
    void add(User user);
    
    List<User> listUsers();

    void add(User user, Car car);
    
    List<User> findUsersByCarSeries(Integer series);
    
    List<User> findUsersByCarModelAndSeries(String model, Integer series);
}
