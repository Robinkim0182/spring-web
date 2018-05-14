package kr.ac.cnu.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by rokim on 2018. 5. 14..
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private long id;
    private String title;
    private String content;
    private String writerId;
}
