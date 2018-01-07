package mblog.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import mblog.shiro.authc.AccountSubjectFactory;
import mblog.shiro.realm.AccountRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuzhaolin on 2017/10/23.
 */
@Configuration
public class ShiroConfig {



    @Bean
    public EhCacheManager getEhCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
    }



/*    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        return autoProxyCreator;
    }*/

   @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


    /**
     * 配置shiro拦截器链
     *
     * anon  不需要认证
     * authc 需要认证
     * user  验证通过或RememberMe登录的都可以
     *
     * 当应用开启了rememberMe时,用户下次访问时可以是一个user,但不会是authc,因为authc是需要重新认证的
     *
     * 顺序从上到下,优先级依次降低
     *
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        Map<String , String> filterChainDefinitionMap = new HashMap<>();
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setLoginUrl("/login");
        filterFactoryBean.setSuccessUrl("/hello");
        filterFactoryBean.setUnauthorizedUrl("/unauthorizedUrl");

        filterChainDefinitionMap.put("/login" , "anon");
        filterChainDefinitionMap.put("/home*", "user");
        filterChainDefinitionMap.put("/home/**" , "user");

        filterChainDefinitionMap.put("/admin", "authc,perms[admin]");
        filterChainDefinitionMap.put("/admin/", "authc,perms[admin]");
        filterChainDefinitionMap.put("/admin/index", "authc,perms[admin]");

        filterChainDefinitionMap.put("/admin/posts/**", "authc,perms[posts:view]");

        filterChainDefinitionMap.put("/admin/comments/**", "authc,perms[comments:view]");

        filterChainDefinitionMap.put("/admin/users/**", "authc,perms[users:view]");

        filterChainDefinitionMap.put("/admin/config/**", "authc,perms[config:view]");

        filterChainDefinitionMap.put("/admin/roles/list", "authc,perms[roles:view]");

        filterChainDefinitionMap.put("/admin/authMenus/list", "authc,perms[authMenus:view]");

        filterChainDefinitionMap.put("/admin/friendLink/list", "authc,perms[friendLink:edit]");

        filterChainDefinitionMap.put("/**" , "anon");
        filterChainDefinitionMap.put("/logout" , "logout");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filterFactoryBean;
    }

    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetBeanName("shiroFilter");
        proxy.setTargetFilterLifecycle(true);
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }


    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(EhCacheManager ehCacheManager , AccountRealm shiroRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSubjectFactory(new AccountSubjectFactory());
        securityManager.setCacheManager(ehCacheManager);
        securityManager.setRealm(shiroRealm);
        return securityManager;
    }

    @Bean
    public AccountRealm getShiroRealm(){
        AccountRealm shiroRealm = new AccountRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        shiroRealm.setCredentialsMatcher(credentialsMatcher);
        return shiroRealm;
    }

    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor(){
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
        return lifecycleBeanPostProcessor;
    }

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

}
