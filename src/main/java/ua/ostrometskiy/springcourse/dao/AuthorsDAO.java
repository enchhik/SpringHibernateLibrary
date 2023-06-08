package ua.ostrometskiy.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.ostrometskiy.springcourse.models.Authors;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class AuthorsDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public AuthorsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Authors> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select a from Authors a", Authors.class)
                .getResultList();
    }

    public Authors show(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Authors.class, id);
    }

    @Transactional
    public void save(Authors authors) {
        Session session = sessionFactory.getCurrentSession();
        session.save(authors);
    }

    @Transactional
    public void update(int id, Authors updatedAuthors) {
        Session session = sessionFactory.getCurrentSession();

        Authors authorsToBeUpdated = session.get(Authors.class, id);

        authorsToBeUpdated.setName(updatedAuthors.getName());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Authors authors  = session.get(Authors.class, id);

        session.delete(authors);
    }


}
