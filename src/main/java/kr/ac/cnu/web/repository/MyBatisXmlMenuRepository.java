package kr.ac.cnu.web.repository;

import kr.ac.cnu.web.model.Menu;

import java.util.List;

/**
 * Created by rokim on 2018. 5. 25..
 */
public interface MyBatisXmlMenuRepository {
    List<Menu> selectMenus(int size);
}
