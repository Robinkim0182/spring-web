package kr.ac.cnu.web.repository;

import kr.ac.cnu.web.model.Menu;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rokim on 2018. 5. 21..
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByToday(String today);
}
