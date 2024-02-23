package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Override
   public void add(User user, Car car) {
      userDao.add(user, car);
   }

   @Override
   public List<User> findUsersByCarSeries(Integer series) {
      return userDao.findUsersByCarSeries(series);
   }

   @Override
   public List<User> findUsersByCarModelAndSeries(String model, Integer series) {
      return userDao.findUsersByCarModelAndSeries(model, series);
   }
}