package kr.ac.cnu.web.service;

import kr.ac.cnu.web.aspect.TimeLogger;
import kr.ac.cnu.web.model.User;
import kr.ac.cnu.web.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rokim on 2018. 5. 25..
 */
@Service
public class BankService {
    @Autowired
    @Setter
    private UserRepository userRepository;

    public User transferMoney(String fromUserName, String toUserName, long money) {
        User from = userRepository.findById(fromUserName).orElse(new User());
        User to = userRepository.findById(toUserName).orElse(new User());


        from.setAccount(from.getAccount() - money);
        userRepository.save(from);

        to.setAccount(to.getAccount() + money);
        userRepository.save(to);

        return from;
    }
}
