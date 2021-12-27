package com.example.springcrud.configs;


import com.example.springcrud.configs.handler.LoginSuccessHandler;
import com.example.springcrud.services.UserDetailsServiceIpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsServiceIpml userDetailsServiceIpml;
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    public void setUserService(UserDetailsServiceIpml userDetailsServiceIpml){
        this.userDetailsServiceIpml = userDetailsServiceIpml;
    }

    @Autowired
    public void setLoginSuccessHandler(LoginSuccessHandler loginSuccessHandler){
        this.loginSuccessHandler = loginSuccessHandler;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin() // форма логирования по умолчанию
                .successHandler(loginSuccessHandler) // раскидываем по страницам в зависимости от ролей
                .permitAll();

        http.logout()
                .permitAll()
                // URL логаута
                .logoutUrl("/logout")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login")
                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();

        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                .authorizeRequests()
                //страницы аутентификаци доступна всем
//                .antMatchers("/api/**").anonymous()
                .antMatchers("/login").anonymous()
//                .antMatchers("/**").access("hasAnyRole('ADMIN')")
                .antMatchers("/user/**").access("hasAnyRole('USER', 'MANAGER','ADMIN')")
                // защищенные URL
                .antMatchers("/select_all/**").access("hasAnyRole('ADMIN')")
                .antMatchers("/api/**").access("hasAnyRole('ADMIN')")
                .antMatchers("/new_person/**").access("hasAnyRole('ADMIN')")
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsServiceIpml);

        return authenticationProvider;
    }

}
