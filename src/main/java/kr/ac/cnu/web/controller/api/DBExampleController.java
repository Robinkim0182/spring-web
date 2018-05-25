package kr.ac.cnu.web.controller.api;

import kr.ac.cnu.web.model.Meal;
import kr.ac.cnu.web.model.Menu;
import kr.ac.cnu.web.repository.MenuRepository;
import kr.ac.cnu.web.repository.MyBatisXmlMenuRepository;
import kr.ac.cnu.web.repository.MybatisMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 25..
 */
@RestController
@RequestMapping("/api/db-example")
public class DBExampleController {
    /**
     * <JDBC를 직접 이용한다.>
     * 직접 Connection 을 열고,
     * SQL 쿼리를 이용하여 PreparedStatement를 만들고,
     * executeQuery 메서드를 호출해서 결과를 받아온다.
     *
     * 결과는 ResultSet 에 저장되고,
     * 개발자는 DB의 구조를 파악해서 Domain 객체에 직접 매핑하여준다.
     *
     * @return
     * @throws SQLException
     */
    @GetMapping(value = "/old")
    public List<Menu> old(@RequestParam int size) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        PreparedStatement pstmt = conn.prepareStatement("SELECT id, today AS menu_day, meal, menu_name FROM menu LIMIT ?");
        pstmt.setInt(1, size);
        ResultSet rs = pstmt.executeQuery();

        List<Menu> menus = new ArrayList<>();
        while (rs.next()) {
            Menu menu = new Menu();
            menu.setId(rs.getLong("id"));
            menu.setToday(rs.getString("menu_day"));
            menu.setMeal(Meal.valueOf(rs.getString("meal")));
            menu.setMenuName(rs.getString("menu_name"));
            menus.add(menu);
        }

        return menus;
    }


    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * <Spring JDBC 추상화 : jdbcTemplate 사용>
     * 복잡하고 반복적인 JDBC의 저수준 API를 Spring 이 관리하여 준다
     * 전형적인 Spring의 JDBC 접근방법이다.
     *
     * @param size
     * @return
     */
    @GetMapping(value = "/jdbc-template")
    public List<Menu> jdbctemplate(@RequestParam int size) {
        RowMapper<Menu> rowMapper = new RowMapper() {
            @Override
            public Menu mapRow(ResultSet rs, int i) throws SQLException {
                Menu menu = new Menu();
                menu.setId(rs.getLong("id"));
                menu.setToday(rs.getString("menu_day"));
                menu.setMeal(Meal.valueOf(rs.getString("meal")));
                menu.setMenuName(rs.getString("menu_name"));

                return menu;
            }
        };

        List<Menu> menus = jdbcTemplate.query("SELECT id, today AS menu_day, meal, menu_name FROM menu LIMIT ?", rowMapper, size);

        return menus;
    }

    @Autowired
    private MyBatisXmlMenuRepository myBatisXmlMenuRepository;

    /**
     * <Mybatis : persistence(영속성) 프레임워크> : MVC 패턴을 이해 하였는가?
     * 마이바티스는 JDBC로 처리하는 상당부분의 코드와 파라미터 설정및 결과 매핑을 대신해준다.
     *
     * @param size
     * @return
     */
    @GetMapping(value = "/mybatis-xml")
    public List<Menu> mybatisXml(@RequestParam int size) {
        List<Menu> menus = myBatisXmlMenuRepository.selectMenus(size);

        return menus;
    }


    @Autowired
    private MybatisMenuRepository mybatisMenuRepository;

    /**
     * <Mybatis> Spring boot 가 부러워지는..
     *
     * 쿼리 설정을 XML 이 아니라 Annotation 으로 한다.
     * 점점 설정파일을 없애는 최근 추세를 반영한다.
     *
     * @param size
     * @return
     */
    @RequestMapping(value = "/mybatis", method = RequestMethod.GET)
    public List<Menu> mybatis(@RequestParam int size) {

        List<Menu> menus = mybatisMenuRepository.findMenus(size);

        return menus;
    }

    @Autowired
    private MenuRepository menuRepository;
    /**
     * <JPA with Hibernate!> : ORM 매핑 프레임워크
     *
     * @param size
     * @return
     */
    @RequestMapping(value = "/jpa", method = RequestMethod.GET)
    public Page<Menu> jpa(@RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(0, size);

        Page<Menu> page = menuRepository.findAll(pageRequest);

        return page;
    }

}
