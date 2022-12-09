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
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Order(1)
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
                .antMatchers("/icon/register")
                .hasRole("USER")
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
                .failureHandler(adminAuthenticationFailureHandler)
                .and()
                .logout()
                .logoutUrl("/member/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and();
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
