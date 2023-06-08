package ua.ostrometskiy.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.ostrometskiy.springcourse.converter.BooksConverter;
import ua.ostrometskiy.springcourse.dao.BooksDAO;
import ua.ostrometskiy.springcourse.dao.OrdersDAO;
import ua.ostrometskiy.springcourse.dao.PersonDAO;
import ua.ostrometskiy.springcourse.models.Books;
import ua.ostrometskiy.springcourse.models.Orders;
import ua.ostrometskiy.springcourse.models.Users;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersDAO ordersDAO;
    private final PersonDAO personDAO;
    private final BooksDAO booksDAO;

    private final BooksConverter booksConverter;

    @Autowired
    public OrdersController(OrdersDAO ordersDAO, PersonDAO personDAO, BooksDAO booksDAO, BooksConverter booksConverter) {
        this.ordersDAO = ordersDAO;
        this.personDAO = personDAO;
        this.booksDAO = booksDAO;
        this.booksConverter = booksConverter;
    }

    @GetMapping()
    public String index(Model model) {
       model.addAttribute("orders", ordersDAO.index());
        return "orders/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

            model.addAttribute("orders", ordersDAO.show(id));
            return "orders/show";
    }


    @GetMapping("/new")
    public String newOrder(Model model) {
        List<Users> usersList = personDAO.index();
        List<Books> booksList = booksDAO.index();

        model.addAttribute("usersList", usersList);
        model.addAttribute("booksList", booksList);
        model.addAttribute("orders", new Orders());
        return "orders/new";
    }

    @PostMapping("")
    public String create(@ModelAttribute("orders") @Valid Orders orders,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "orders/new";

        //orders.setBooksList(booksConverter.convert(orders.getBooksList().toString()));
        orders.setBooksList(orders.getBooksList());
        orders.setUser(personDAO.show(orders.getUser().getId()));

        ordersDAO.save(orders);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        List<Users> usersList = personDAO.index();
        List<Books> booksList = booksDAO.index();

        model.addAttribute("usersList", usersList);
        model.addAttribute("booksList", booksList);
        model.addAttribute("orders", ordersDAO.show(id));
        return "orders/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("orders") @Valid Orders orders, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "orders/edit";

        ordersDAO.update(id, orders);
        return "redirect:/orders";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ordersDAO.delete(id);
        return "redirect:/orders";
    }

}
