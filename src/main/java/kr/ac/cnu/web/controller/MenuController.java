package kr.ac.cnu.web.controller;

import kr.ac.cnu.web.model.Meal;
import kr.ac.cnu.web.model.Menu;
import kr.ac.cnu.web.model.TodayMenu;
import kr.ac.cnu.web.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 21..
 */
@Controller
@Slf4j
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/menus/insert")
    public String insert() {
        return "/menu/insert";
    }

    @GetMapping("/menus/{today}")
    public String menu(@PathVariable String today, Model model) {
        List<Menu> menuList = menuRepository.findAllByToday(today);
        List<String> breakfastList = new ArrayList<>();
        List<String> launchList = new ArrayList<>();
        List<String> dinnerList = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getMeal() == Meal.BREAKFAST) {
                breakfastList.add(menu.getMenuName());
            } else if (menu.getMeal() == Meal.LAUNCH) {
                launchList.add(menu.getMenuName());
            } else if (menu.getMeal() == Meal.DINNER) {
                dinnerList.add(menu.getMenuName());
            }
        }
        TodayMenu todayMenu = new TodayMenu();
        todayMenu.setToday(today);
        todayMenu.setBreakfastList(breakfastList);
        todayMenu.setLaunchList(launchList);
        todayMenu.setDinnerList(dinnerList);

        model.addAttribute("todayMenu", todayMenu);
        return "/menu/menu";
    }
}
