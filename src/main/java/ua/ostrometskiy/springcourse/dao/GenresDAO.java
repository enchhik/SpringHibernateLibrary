package ua.ostrometskiy.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.ostrometskiy.springcourse.models.Books;
import ua.ostrometskiy.springcourse.models.Genres;
import ua.ostrometskiy.springcourse.models.Users;

import java.util.List;

@Component
public class GenresDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public GenresDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Genres> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select g from Genres g", Genres.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Genres show(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Genres.class, id);
    }

    @Transactional
    public void save(Genres genres){
        Session session = sessionFactory.getCurrentSession();
        session.save(genres);
    }

    @Transactional
    public void update(int id, Genres updatedGenres){
        Session session = sessionFactory.getCurrentSession();

        Genres genresToBeUpdated = session.get(Genres.class, id);

        genresToBeUpdated.setName(updatedGenres.getName());
        session.update(genresToBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Genres.class, id));
    }

/*    @Transactional(readOnly = true)
    public Genres getGenreById(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Genres.class, id);
    }*/


}
