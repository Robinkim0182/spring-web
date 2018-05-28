package kr.ac.cnu.web.service;

import kr.ac.cnu.web.model.Meal;
import kr.ac.cnu.web.model.Menu;
import kr.ac.cnu.web.model.TodayMenu;
import kr.ac.cnu.web.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 28..
 */
@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public TodayMenu getTodayMenu(String today) {
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

        return todayMenu;
    }
}
