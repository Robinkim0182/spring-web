package kr.ac.cnu.web.model;

import lombok.Data;

import java.util.List;

/**
 * Created by rokim on 2018. 5. 21..
 */
@Data
public class TodayMenu {
    private String today;
    private List<String> breakfastList;
    private List<String> launchList;
    private List<String> dinnerList;
}
