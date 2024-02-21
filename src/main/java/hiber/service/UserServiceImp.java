package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @PersistenceContext
   private EntityManager entityManager;

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
   @Transactional(readOnly = true)
   public List<User> findUsersByCarModelOrSeries(String model, Integer series) {
      String hql = "select distinct u from User u join u.car c where";
      Map<String, Object> parameters = new HashMap<>();

      if (model != null && series != null) {
         hql += " c.model = :model and c.series = :series";
         parameters.put("model", model);
         parameters.put("series", series);
      } else if (model != null) {
         hql += " c.model = :model";
         parameters.put("model", model);
      } else if (series != null) {
         hql += " c.series = :series";
         parameters.put("series", series);
      } else {
         // Если не указаны ни модель, ни серия, возвращаем пустой список
         return Collections.emptyList();
      }

      Query query = entityManager.createQuery(hql);
      for (Map.Entry<String, Object> entry : parameters.entrySet()) {
         query.setParameter(entry.getKey(), entry.getValue());
      }

      return query.getResultList();
   }
}