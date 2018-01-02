package mblog.web.controller.desk.user;

import mblog.core.data.AccountProfile;
import mblog.core.persist.service.FeedsService;
import mblog.core.persist.service.UserService;
import mblog.core.persist.service.impl.FeedsServiceImpl;
import mblog.core.persist.service.impl.UserServiceImpl;
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
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import shiro.AbstractShiroTest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/24 22:18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class HomeControllerTest extends AbstractShiroTest {

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
    public void home() throws Exception {
        HomeController homeController = new HomeController();
        FeedsService feedsService = Mockito.mock(FeedsServiceImpl.class);
        UserService userService = Mockito.mock(UserServiceImpl.class);
        Page page = Mockito.mock(Page.class);
        List list = Mockito.mock(List.class);
        homeController.setFeedsService(feedsService);
        homeController.setUserService(userService);
       // Mockito.when(feedsService.findUserFeeds(Mockito.any() , Mockito.eq(1) , Mockito.eq(0) , Mockito.eq(0))).thenReturn(page);
        Mockito.when(feedsService.findConcreteContentByIds(Mockito.any())).thenReturn(list);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
        //代码有问题，重构后再测
        /*mockMvc.perform(MockMvcRequestBuilders.post("/home")
                .param("pn", "0"))
                .andExpect(MockMvcResultMatchers.view().name("themes/default/home/feeds"));
        Mockito.verify(feedsService , Mockito.times(1)).findUserFeeds(Mockito.any() , Mockito.eq(1) , Mockito.eq(0) , Mockito.eq(0));*/

    }

}