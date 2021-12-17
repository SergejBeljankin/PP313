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
                .logoutSuccessUrl("/logoutsucsess")
                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();

        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                .authorizeRequests()
                //страницы аутентификаци доступна всем
                .antMatchers("/login").anonymous()
                .antMatchers("/user/**").access("hasAnyRole('USER', 'MANAGER')")
                // защищенные URL
                .antMatchers("/select_all/**").access("hasAnyRole('ADMIN')")
                .antMatchers("/edit/**").access("hasAnyRole('ADMIN')")
                .antMatchers("/new_person/**").access("hasAnyRole('ADMIN')")
                .anyRequest().authenticated();




        /*
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/only_for_admins/**").hasRole("ADMIN")
                .and()
                .formLogin()
//                .loginProcessingUrl("/hello_login") // можно указать по какому адресу будет обрабатываться пароль
                .and()
                .logout().logoutSuccessUrl("/");


         */


    }

    // InMemory *
//    @Bean
//    public UserDetailsService users(){
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$10$LgDpXUu.37U2lhasLJ5EFu/IHge8wy/mP9YgJ46TZvzbVTbBT5y4C")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$10$LgDpXUu.37U2lhasLJ5EFu/IHge8wy/mP9YgJ46TZvzbVTbBT5y4C")
//                .roles("ADMIN","USER")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//
//    }

    // JdbcAuthentication
//    @Bean
//    public JdbcUserDetailsManager user(DataSource dataSource){
//            UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$10$LgDpXUu.37U2lhasLJ5EFu/IHge8wy/mP9YgJ46TZvzbVTbBT5y4C")
//                .roles("USER")
//                .build();
//            UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$10$LgDpXUu.37U2lhasLJ5EFu/IHge8wy/mP9YgJ46TZvzbVTbBT5y4C")
//                .roles("ADMIN","USER")
//                .build();
//
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        if(jdbcUserDetailsManager.userExists(user.getUsername())){
//            jdbcUserDetailsManager.deleteUser(user.getUsername());
//        }
//        if(jdbcUserDetailsManager.userExists(admin.getUsername())){
//            jdbcUserDetailsManager.deleteUser(admin.getUsername());
//        }
//        jdbcUserDetailsManager.createUser(user);
//        jdbcUserDetailsManager.createUser(admin);
//        return jdbcUserDetailsManager;
//    }

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
