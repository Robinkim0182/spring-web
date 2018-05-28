package kr.ac.cnu.web.service;

import kr.ac.cnu.web.model.User;
import kr.ac.cnu.web.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by rokim on 2018. 5. 28..
 */
public class BankServiceTest {
    private BankService bankService = new BankService();

    @Test
    public void testTransfer() {
        UserRepository userRepository = mock(UserRepository.class);
        bankService.setUserRepository(userRepository);

        when(userRepository.findById("FROM")).thenReturn(Optional.ofNullable(new User("FROM", 10000)));
        when(userRepository.findById("TO")).thenReturn(Optional.ofNullable(new User("TO", 5000)));

        User fromUser = bankService.transferMoney("FROM", "TO", 1000);

        assertEquals(fromUser.getAccount(), 9000);
    }
}