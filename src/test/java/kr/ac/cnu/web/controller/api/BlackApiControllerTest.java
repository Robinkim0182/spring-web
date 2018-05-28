package kr.ac.cnu.web.controller.api;

import kr.ac.cnu.web.model.User;
import kr.ac.cnu.web.repository.UserRepository;
import kr.ac.cnu.web.service.BlackjackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rokim on 2018. 5. 28..
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BlackApiController.class)
public class BlackApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlackjackService blackjackService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void test_create_room() throws Exception {
        when(userRepository.findById("Robin")).thenReturn(Optional.ofNullable(new User("Robin", 10000)));

        mockMvc.perform(
                post("/api/black-jack/rooms")
                        .header("name", "Robin")
        ).andExpect(status().isOk());
    }
}