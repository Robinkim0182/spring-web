package kr.ac.cnu.web.repository;

import kr.ac.cnu.web.model.Meal;
import kr.ac.cnu.web.model.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rokim on 2018. 5. 28..
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MenuRepositoryTest {
    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void test() {
        Menu menu = new Menu();
        menu.setToday("0503");
        menu.setMeal(Meal.BREAKFAST);
        menu.setMenuName("국밥");

        Menu menu2 = new Menu();
        menu2.setToday("0503");
        menu2.setMeal(Meal.BREAKFAST);
        menu2.setMenuName("국밥");

        menuRepository.save(menu);
        menuRepository.save(menu2);

        List<Menu> menus = menuRepository.findAllByToday("0503");
        assertEquals(menus.size(), 2);
    }
}