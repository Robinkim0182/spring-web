package kr.ac.cnu.web.repository;

import kr.ac.cnu.web.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by rokim on 2018. 5. 25..
 */
@Mapper
public interface MybatisMenuRepository {
    @Select("SELECT id, today, meal, menu_name AS menuName FROM menu LIMIT #{size}")
    List<Menu> findMenus(@Param("size") int size);
}
