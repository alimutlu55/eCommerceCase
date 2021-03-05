package com.trendyol.eCommerceCase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.eCommerceCase.dao.UserRepository;
import com.trendyol.eCommerceCase.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    private  ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    public void signUp() throws Exception {

        User user = new User();
        user.setId(1);
        user.setEmail("altml@gmail.com");
        user.setName("Ali");
        user.setSurname("Mutlu");
        user.setPassword("Mutlu98.!");
        user.setUsername("aLmtl5529");

        given(userRepository.findByUsername(user.getName())).willReturn(null);
        given(userRepository.save(user)).willReturn(user);

        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void login() throws Exception {
        User fetchedUser = new User();
        fetchedUser.setId(1);
        fetchedUser.setEmail("altml@gmail.com");
        fetchedUser.setName("Ali");
        fetchedUser.setSurname("Mutlu");
        fetchedUser.setPassword(bCryptPasswordEncoder.encode("Mutlu98.!"));
        fetchedUser.setUsername("aLmtl5529");

        User loginAttemptUser = new User();
        loginAttemptUser.setId(1);
        loginAttemptUser.setEmail("altml@gmail.com");
        loginAttemptUser.setName("Ali");
        loginAttemptUser.setSurname("Mutlu");
        loginAttemptUser.setPassword("Mutlu98.!");
        loginAttemptUser.setUsername("aLmtl5529");

        given(userRepository.findByUsername(loginAttemptUser.getUsername())).willReturn(fetchedUser);

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginAttemptUser)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

}
