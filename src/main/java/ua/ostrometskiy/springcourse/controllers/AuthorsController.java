package ua.ostrometskiy.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.ostrometskiy.springcourse.dao.AuthorsDAO;
import ua.ostrometskiy.springcourse.models.Authors;
import javax.validation.Valid;


@Controller
@RequestMapping("/authors")
public class AuthorsController {
    private final AuthorsDAO authorsDAO;

    @Autowired
    public AuthorsController(AuthorsDAO authorsDAO) {
        this.authorsDAO = authorsDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("authors", authorsDAO.index());
        return "authors/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("authors", authorsDAO.show(id));
        return "authors/show";
    }

    @GetMapping("/new")
    public String newAuthor(Model model) {

        model.addAttribute("authors", new Authors());
        return "authors/new";
    }

    @PostMapping("")
    public String create(@ModelAttribute("authors") @Valid Authors authors,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "authors/new";

        authorsDAO.save(authors);
        return "redirect:/authors";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {

        model.addAttribute("authors", authorsDAO.show(id));
        return "authors/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("authors") @Valid Authors authors, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "authors/edit";

        authorsDAO.update(id, authors);
        return "redirect:/authors";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        authorsDAO.delete(id);
        return "redirect:/authors";
    }

}
