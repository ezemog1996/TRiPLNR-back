package com.lti.triplnr20.ControllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.lti.triplnr20.controllers.UserController;
import com.lti.triplnr20.models.User;
import com.lti.triplnr20.services.AddressService;
import com.lti.triplnr20.services.S3Service;
import com.lti.triplnr20.services.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @MockBean
    private UserService us;
    @MockBean
    private S3Service s3;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUserBySubExists() throws Exception {
        when(us.getUserBySub("")).thenReturn(new User());

        mockMvc.perform(get("/users/sub")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", ""))
            .andExpect(status().isOk());
    }
    @Test
    void getUserBySubNotExists() throws Exception {
        when(us.getUserBySub("")).thenReturn(null);

        mockMvc.perform(get("/users/sub")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", ""))
            .andExpect(status().isOk());
    }
    
    @Test
    void getByUserExists() throws Exception {
        when(us.getUserBySub("")).thenReturn(new User());

        mockMvc.perform(get("/users/user")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", ""))
            .andExpect(status().isOk());
    }
    @Test
    void getByUserNotExists() throws Exception {
        when(us.getUserBySub("")).thenReturn(null);

        mockMvc.perform(get("/users/user")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", ""))
            .andExpect(status().isOk());
    }
    
//    @Test
//    void getFriendsExists() throws Exception {
//    	List<User> friends = 
//        when(us.getFriends("")).thenReturn(new List<User>());
//
//        mockMvc.perform(get("/users/myfriends")
//            .contentType(MediaType.APPLICATION_JSON)
//            .header("Authorization", ""))
//            .andExpect(status().isOk());
//    }
}
