package com.example.springcrud.configs.handler;

import com.example.springcrud.entities.Person;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
//        httpServletResponse.sendRedirect("/admin");
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/select_all");
        } else if (roles.contains("ROLE_USER") || roles.contains("ROLE_MANAGER")) {
         Person person = (Person) authentication.getPrincipal();
//            ((Person) authentication.getPrincipal()).getId()
            httpServletResponse.sendRedirect("/user/" + person.getId());
        }

    }
}