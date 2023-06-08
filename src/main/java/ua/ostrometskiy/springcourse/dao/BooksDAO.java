package ua.ostrometskiy.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.ostrometskiy.springcourse.models.Authors;
import ua.ostrometskiy.springcourse.models.Books;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class BooksDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BooksDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Books> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select b from Books b", Books.class)
                .getResultList();
    }

    public Books show(int id) {
        Session session = sessionFactory.getCurrentSession();

/*        Books book = session.createQuery("SELECT DISTINCT b FROM Books b " +
                        "JOIN FETCH b.genre " +
                        "LEFT JOIN FETCH b.authorList " +
                        "WHERE b.id = :id", Books.class)
                .setParameter("id", id)
                .getSingleResult();

        return book;*/
        return session.createQuery("select b from Books b where id = :id", Books.class)
                .setParameter("id", id).uniqueResult();
    }

    @Transactional
    public void save(Books books){
        Session session = sessionFactory.getCurrentSession();
        session.save(books);
    }

    @Transactional
    public void update(int id, Books updatedBooks){
        Session session = sessionFactory.getCurrentSession();

        Books booksToBeUpdated = session.get(Books.class, id);

        booksToBeUpdated.setTitle(updatedBooks.getTitle());
        booksToBeUpdated.setIsbn(updatedBooks.getIsbn());
        booksToBeUpdated.setReleaseDate(updatedBooks.getReleaseDate());
        booksToBeUpdated.setGenre(updatedBooks.getGenre());
        booksToBeUpdated.setAuthorList(updatedBooks.getAuthorList());

        session.update(booksToBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Books.class, id));
    }


}
