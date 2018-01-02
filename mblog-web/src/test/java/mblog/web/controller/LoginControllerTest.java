package mblog.web.controller;

import mblog.core.data.AccountProfile;
import mblog.shiro.authc.AccountSubject;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import shiro.AbstractShiroTest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import static org.junit.Assert.*;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/24 21:23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class LoginControllerTest extends AbstractShiroTest {

    @Autowired
    private DefaultWebSecurityManager securityManager;

    private AccountSubject accountSubject;

    @Before
    public void setupMockMvc() {
        PrincipalCollection principalCollection = Mockito.mock(PrincipalCollection.class);
        Session session = Mockito.mock(Session.class);
        ServletRequest request = Mockito.mock(ServletRequest.class);
        ServletResponse response = Mockito.mock(ServletResponse.class);
        accountSubject = new AccountSubject(principalCollection, true, "localhost", session, true, request, response, securityManager, new AccountProfile(1, "z"));
        setSubject(accountSubject);
        setSecurityManager(securityManager);
    }

    @Test
    public void view() throws Exception {
        LoginController loginController = new LoginController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.view().name("themes/default/login"));
    }

    @Test
    public void login() throws Exception {
       /* LoginController loginController = new LoginController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username", "zhuzhaolin")
                .param("password", "123456")
                .param("rememberMe", "0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));*/
    }

}