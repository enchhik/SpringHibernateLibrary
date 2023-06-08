package ua.ostrometskiy.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.ostrometskiy.springcourse.models.Passport;
import ua.ostrometskiy.springcourse.models.Users;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Users> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select u from Users u", Users.class)
                .getResultList();

    }

    public Users show(int id) {
        Session session = sessionFactory.getCurrentSession();

        /*Users id1 = session.createQuery("SELECT u FROM Users u LEFT JOIN FETCH u.passport WHERE u.id = :id", Users.class)
                .setParameter("id", id)
                .getSingleResult();

        return id1;*/
        return session.get(Users.class, id);
    }

    @Transactional
    public void save(Users users){
        Session session = sessionFactory.getCurrentSession();
        session.save(users);
    }

    @Transactional
    public void update(int id, Users updatedUsers){
        Session session = sessionFactory.getCurrentSession();

        Users userToBeUpdated = session.get(Users.class, id);

        userToBeUpdated.setName(updatedUsers.getName());
        userToBeUpdated.setEmail(updatedUsers.getEmail());
        userToBeUpdated.setPassword(updatedUsers.getPassword());
        userToBeUpdated.getPassport().setPassportNumber(updatedUsers.getPassport().getPassportNumber()); // установка нового номера паспорта у пользователя
        session.update(userToBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Users.class, id));
    }
}
