package jambo.config;

import jambo.security.AdminAuthenticationFailureHandler;
import jambo.security.UserAuthenticationFailureHandler;
import jambo.security.UserAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserAuthenticationProvider userAuthenticationProvider;
    private final UserAuthenticationFailureHandler userAuthenticationFailureHandler;

    private final AdminAuthenticationFailureHandler adminAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/board/write")
                .hasRole("USER")
                .antMatchers("/StudyBoard/StudyBoardWrite")
                .hasRole("USER")
                .antMatchers("/icon/shop")
                .hasRole("USER")
                .antMatchers("/icon/register")
                .hasRole("ADMIN")
                .antMatchers("/admin/*")
                .hasRole("ADMIN")
                .antMatchers("/user/myPage")
                .hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/user/loginForm")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureHandler(userAuthenticationFailureHandler)
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(userAuthenticationProvider);
    }

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
