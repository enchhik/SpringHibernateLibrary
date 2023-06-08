package ua.ostrometskiy.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.ostrometskiy.springcourse.dao.AuthorsDAO;
import ua.ostrometskiy.springcourse.dao.BooksDAO;
import ua.ostrometskiy.springcourse.dao.GenresDAO;
import ua.ostrometskiy.springcourse.models.Authors;
import ua.ostrometskiy.springcourse.models.Books;
import ua.ostrometskiy.springcourse.models.Genres;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksDAO booksDAO;
    private final GenresDAO genresDAO;
    private final AuthorsDAO authorsDAO;

    @Autowired
    public BooksController(BooksDAO booksDAO, GenresDAO genresDAO, AuthorsDAO authorsDAO) {
        this.booksDAO = booksDAO;
        this.genresDAO = genresDAO;
        this.authorsDAO = authorsDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("books", booksDAO.show(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model){
        List<Genres> genresList = genresDAO.index();
        List<Authors> authorList = authorsDAO.index();

        model.addAttribute("genresList", genresList);
        model.addAttribute("authorList", authorList);
        model.addAttribute("books", new Books());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("books") @Valid Books books,
                         BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return "books/new";

        books.setAuthorList(books.getAuthorList());
        books.setGenre(genresDAO.show(books.getGenre().getId()));

        booksDAO.save(books);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){

        List<Genres> genresList = genresDAO.index();
        List<Authors> authorList = authorsDAO.index();

        model.addAttribute("genresList", genresList);
        model.addAttribute("authorList", authorList);
        model.addAttribute("books", booksDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("books") @Valid Books books, BindingResult bindingResult,
                         @PathVariable("id") int id){

        if (bindingResult.hasErrors())
            return "books/edit";

        booksDAO.update(id, books);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksDAO.delete(id);
        return "redirect:/books";
    }

}

