package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void add(User user, Car car) {
      user.setCar(car);
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @Transactional(readOnly = true)
   public List<User> findUsersByCarSeries(Integer series) {
      String hql = "select distinct u from User u join u.car c where c.series = :series";

      return entityManager.createQuery(hql)
              .setParameter("series", series)
              .getResultList();
   }

   @Override
   @Transactional(readOnly = true)
   public List<User> findUsersByCarModelAndSeries(String model, Integer series) {
      String hql = "select distinct u from User u join u.car c where 1=1";
      Map<String, Object> parameters = new HashMap<>();

      if (model != null) {
         hql += " and c.model = :model";
         parameters.put("model", model);
      }
      if (series != null) {
         hql += " and c.series = :series";
         parameters.put("series", series);
      }

      Query query = entityManager.createQuery(hql);
      for (Map.Entry<String, Object> entry : parameters.entrySet()) {
         query.setParameter(entry.getKey(), entry.getValue());
      }

      return query.getResultList();
   }
}
