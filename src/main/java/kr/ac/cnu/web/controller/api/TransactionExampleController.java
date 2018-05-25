package kr.ac.cnu.web.controller.api;

import kr.ac.cnu.web.model.User;
import kr.ac.cnu.web.repository.UserRepository;
import kr.ac.cnu.web.service.BankService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by rokim on 2018. 5. 25..
 */
@RestController
@RequestMapping("/api/transaction-example")
public class TransactionExampleController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BankService bankService;

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @PostMapping("/transfers")
    public void transferMoney(@RequestBody TransferDto transferDto) {
        bankService.transferMoney(transferDto.getFromName(), transferDto.getToName(), transferDto.getMoney());
    }

    @Data
    static class TransferDto {
        private String fromName;
        private String toName;
        private long money;
    }
}
