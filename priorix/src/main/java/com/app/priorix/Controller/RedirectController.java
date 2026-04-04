package com.app.priorix.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class RedirectController {

    @GetMapping("/redirectByRole")
    public String redirectAfterLogin(Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            return "redirect:/admin/dashboard";
        } else if (roles.contains("ROLE_ENFERMERO")) {
            return "redirect:/enfermero/triaje";
        } else if (roles.contains("ROLE_MEDICO")) {
            return "redirect:/medico/lista-espera";
        } else {
            return "redirect:/login?error";
        }
    }
}
