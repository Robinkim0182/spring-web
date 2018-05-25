package kr.ac.cnu.web.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by rokim on 2018. 5. 25..
 */
@Data
@Entity
public class User {
    @Id
    private String name;
    private long account;
}
