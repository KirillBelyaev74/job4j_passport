
package ru.job4j.job4j_passport.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.job4j_passport.model.Passport;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Repository
public class PassportRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public PassportRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private <T> T action(Function<Session, T> action) {
        T t;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        t = action.apply(session);
        session.getTransaction().commit();
        return t;
    }

    public Passport saveOrUpdate(Passport passport) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(passport);
        session.getTransaction().commit();
        return passport;
    }

    public List<Passport> findAll() {
        return action(session -> session.getNamedQuery("findAll").getResultList());
    }

    public List<Passport> findBySeries(int series) {
        return action(session -> session.getNamedQuery("findBySeries").setParameter("series", series).getResultList());
    }

    public List<Passport> findUnavailable() {
        return action(session -> session.getNamedQuery("findUnavailable").setParameter("date", new Date()).getResultList());
    }

    public void deleteById(int id) {
        action(session -> session.getNamedQuery("deleteById").setParameter("id", id).executeUpdate());
    }
}
