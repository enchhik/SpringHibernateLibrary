package ua.ostrometskiy.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.ostrometskiy.springcourse.dao.GenresDAO;
import ua.ostrometskiy.springcourse.models.Genres;

import javax.validation.Valid;

@Controller
@RequestMapping("/genres")
public class GenresController {

    private final GenresDAO genresDAO;

    @Autowired
    public GenresController(GenresDAO genresDAO) {
        this.genresDAO = genresDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("genres", genresDAO.index());
        return "genres/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("genres", genresDAO.show(id));
        return "genres/show";
    }

    @GetMapping("/new")
    public String newGenre(Model model){
        model.addAttribute("genres",new Genres());
        return "genres/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("genres") @Valid Genres genres,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "genres/new";

        genresDAO.save(genres);
        return "redirect:/genres";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){

        model.addAttribute("genres", genresDAO.show(id));
        return "genres/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("genres") @Valid Genres genres, BindingResult bindingResult,
                         @PathVariable("id") int id){

        if (bindingResult.hasErrors())
            return "genres/edit";

        genresDAO.update(id, genres);
        return "redirect:/genres";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        genresDAO.delete(id);
        return "redirect:/genres";
    }
}

