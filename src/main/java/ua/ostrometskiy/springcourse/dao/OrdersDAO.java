package ua.ostrometskiy.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.ostrometskiy.springcourse.models.Orders;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class OrdersDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public OrdersDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Orders> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT o FROM Orders o", Orders.class).getResultList();
    }

    public Orders show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT o FROM Orders o WHERE id = :id", Orders.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    @Transactional
    public void save(Orders orders) {
        Session session = sessionFactory.getCurrentSession();
        session.save(orders);
    }

    @Transactional
    public void update(int id, Orders updatedOrders) {
        Session session = sessionFactory.getCurrentSession();
        Orders ordersToBeUpdated = session.get(Orders.class, id);

        ordersToBeUpdated.setUser(updatedOrders.getUser());
        ordersToBeUpdated.setBooksList(updatedOrders.getBooksList());
        ordersToBeUpdated.setOrderDate(updatedOrders.getOrderDate());

        session.update(ordersToBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Orders orders = session.get(Orders.class, id);
        session.delete(orders);
    }
}
