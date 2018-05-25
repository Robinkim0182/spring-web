package kr.ac.cnu.web.service;

import kr.ac.cnu.web.model.User;
import kr.ac.cnu.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rokim on 2018. 5. 25..
 */
@Service
public class BankService {
    @Autowired
    private UserRepository userRepository;

    public void transferMoney(String fromUserName, String toUserName, long money) {
        User from = userRepository.findById(fromUserName).get();
        User to = userRepository.findById(toUserName).get();


        from.setAccount(from.getAccount() - money);
        userRepository.save(from);

        to.setAccount(to.getAccount() + money);
        userRepository.save(to);
    }
}
