package mblog.web.controller.desk.account;

import mblog.MblogApplication;
import mblog.base.email.EmailSender;
import mblog.core.data.AccountProfile;
import mblog.core.data.User;
import mblog.core.persist.service.UserService;
import mblog.core.persist.service.VerifyService;
import mblog.core.persist.service.impl.UserServiceImpl;
import mblog.shiro.authc.AccountSubject;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.thymeleaf.context.Context;
import shiro.AbstractShiroTest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * Created by zhuzhaolin .
 * Created in2017/12/23 11:10.
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = MblogApplication.class)
@WebAppConfiguration //由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上
public class UserSettingControllerTest extends AbstractShiroTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private DefaultWebSecurityManager securityManager;

    private AccountSubject accountSubject;

    @Before
    public void setupMockMvc() {
        // mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        //securityManager.createSubject( new DefaultWebSubjectContext());
        //  setSubject(new DefaultWebSecurityManager());
        PrincipalCollection principalCollection = Mockito.mock(PrincipalCollection.class);
        Session session = Mockito.mock(Session.class);
        ServletRequest request = Mockito.mock(ServletRequest.class);
        ServletResponse response = Mockito.mock(ServletResponse.class);
        //Subject subjectUnderTest = new WebSubject.Builder(securityManager, new MockHttpServletRequest(), new MockHttpServletResponse()).buildSubject();
        // AccountSubject accountSubject = Mockito.mock(AccountSubject.class);
       //AccountProfile accountProfile = Mockito.mock(AccountProfile.class);
        accountSubject = new AccountSubject(principalCollection, true, "localhost", session, true, request, response, securityManager, new AccountProfile(1, "z"));
        //2. Bind the subject to the current thread:
        setSubject(accountSubject);
        setSecurityManager(securityManager);
    }

    @Test
    public void view() throws Exception {
        UserService userService = Mockito.mock(UserServiceImpl.class);
        UserSettingController userSettingController = new UserSettingController();
        User expectUser = new User();
        userSettingController.setUserService(userService);
        Mockito.when(userService.get(1)).thenReturn(expectUser);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userSettingController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/account/profile"))
                .andExpect(MockMvcResultMatchers.view().name("themes/default/account/usersetting"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("view"))
                .andExpect(MockMvcResultMatchers.model().attribute("view", expectUser));
        Mockito.verify(userService, Mockito.only()).get(1);
    }

    @Test
    public void baseSetting() throws Exception {

        UserService userService = Mockito.mock(UserServiceImpl.class);

        UserSettingController userSettingController = new UserSettingController();
        userSettingController.setUserService(userService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userSettingController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/account/profile")
                .param("name", "zhuzhaolin")
                .param("signature", "zhuzhaolin")
                .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":{\"code\":0,\"message\":\"操作成功\",\"data\":[]}}"));
        //Mockito.when(userService.update(expectUser)).thenReturn(accountProfil);
        Mockito.verify(userService, Mockito.times(1)).get(1);
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userService, Mockito.times(1)).update(argumentCaptor.capture());
        Assert.assertEquals("zhuzhaolin", argumentCaptor.getValue().getName());
        Assert.assertEquals("zhuzhaolin", argumentCaptor.getValue().getSignature());
        Assert.assertEquals(1, argumentCaptor.getValue().getId());

    }

    @Test
    public void passwordSetting() throws Exception {
        UserService userService = Mockito.mock(UserServiceImpl.class);
        UserSettingController userSettingController = new UserSettingController();
        userSettingController.setUserService(userService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userSettingController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/account/password")
                .param("oldPassword", "123456")
                .param("password", "654321"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":{\"code\":0,\"message\":\"操作成功\",\"data\":[]}}"));
        Mockito.verify(userService , Mockito.times(1)).updatePassword(1 , "123456" , "654321");
    }

    @Test
    public void emailPost() throws Exception {
        UserService userService = Mockito.mock(UserServiceImpl.class);
        EmailSender emailSender = Mockito.mock(EmailSender.class);
        VerifyService verifyService = Mockito.mock(VerifyService.class);
        UserSettingController userSettingController = new UserSettingController();
        userSettingController.setUserService(userService);
        userSettingController.setEmailSender(emailSender);
        userSettingController.setVerifyService(verifyService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userSettingController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/account/email")
        .param("email" , "123456@qq.com"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"message\":\"操作成功,已经发送验证邮件，请前往邮箱验证\",\"data\":[]}"));
        Mockito.verify(userService , Mockito.times(1)).updateEmail(1 , "123456@qq.com");
        Mockito.verify(verifyService , Mockito.times(1)).generateCode(1 , 1 ,"123456@qq.com" );
        Context context = Mockito.mock(Context.class);
        Mockito.verify(emailSender , Mockito.times(1)).sendTemplate(Mockito.eq("123456@qq.com") , Mockito.eq("邮箱绑定验证") , Mockito.eq("emailVerify") ,Mockito.any(Context.class));
    }

    @Test
    public void post() throws Exception {
    }


    @After
    public void tearDownSubject() {
        clearSubject();
    }

}