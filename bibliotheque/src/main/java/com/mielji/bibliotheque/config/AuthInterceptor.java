package com.mielji.bibliotheque.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(false);

        // Vérifie si l'utilisateur est connecté via l'attribut de session
        boolean isLoggedIn = (session != null && session.getAttribute("utilisateurConnecte") != null);

        if (!isLoggedIn) {
            // Si non connecté, on le redirige vers /login
            response.sendRedirect("/login");
            return false;
        }

        return true; // Autoriser la requête à continuer
    }
}
