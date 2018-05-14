package kr.ac.cnu.web.repository;

import kr.ac.cnu.web.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by rokim on 2018. 5. 14..
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
