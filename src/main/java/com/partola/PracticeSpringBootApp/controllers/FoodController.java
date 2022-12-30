package com.partola.PracticeSpringBootApp.controllers;

import com.partola.PracticeSpringBootApp.dao.DAO;
import com.partola.PracticeSpringBootApp.dao.DAOFactory;
import com.partola.PracticeSpringBootApp.dao.DataSource;
import com.partola.PracticeSpringBootApp.entities.Food;
import com.partola.PracticeSpringBootApp.entities.FoodFactory;
import com.partola.PracticeSpringBootApp.forms.AddFoodForm;
import com.partola.PracticeSpringBootApp.forms.DeleteSpoiledFoodForm;
import com.partola.PracticeSpringBootApp.forms.FindFoodForm;
import com.partola.PracticeSpringBootApp.services.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author Ivan Partola
 * @date 07.12.2022
 */
@Controller
public class FoodController {

    private final DAO dao = DAOFactory.getDaoInstance(DataSource.COLLECTION);

    @GetMapping("/")
    public String food (Model model) {
        model.addAttribute("foods", dao.show());
        return "/food";
    }

    @GetMapping("/add")
    public String add (Model model) {
        model.addAttribute("addFoodForm", new AddFoodForm());
        return "/add";
    }

    @PostMapping("/add")
    public String addSubmit (@ModelAttribute AddFoodForm addFoodForm) {
        Food food = FoodFactory.getFoodInstance(addFoodForm.getFoodType());
        FoodService.fillFoodFromForm(food, addFoodForm);

        dao.addFood(food);

        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("deleteSpoiledFoodForm", new DeleteSpoiledFoodForm());
        return "/delete";
    }

    @PostMapping("/delete")
    public String deleteSubmit(@ModelAttribute DeleteSpoiledFoodForm deleteSpoiledFoodForm) {
        dao.deleteSpoiledFood(deleteSpoiledFoodForm.getFoodType());

        return "redirect:/";
    }

    @GetMapping("/find")
    public String find(Model model) {
        model.addAttribute("findFoodForm", new FindFoodForm());
        return "/find";
    }

    @PostMapping("/find")
    public String findSubmit(@ModelAttribute FindFoodForm findFoodForm, RedirectAttributes redirectAttributes) {
        List<Food> searchResults = dao.findFoodWhereVariableIs(findFoodForm.getFoodType(),
                findFoodForm.getVariable(),
                findFoodForm.getValue());

        redirectAttributes.addFlashAttribute("searchResults", searchResults);
        return "redirect:/searchResults";
    }

    @GetMapping("/searchResults")
    public String searchResults() {
        return "/searchResults";
    }
}
