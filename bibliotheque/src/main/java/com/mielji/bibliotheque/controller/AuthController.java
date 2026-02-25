package com.mielji.bibliotheque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    // Redirection de la racine vers /login
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    // Page login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Traitement du login
    @PostMapping("/login")
    public String doLogin(@RequestParam("username") String username,
            @RequestParam("password") String password,
            jakarta.servlet.http.HttpSession session,
            Model model) {
        // Vérification codée en dur
        if ("admin".equals(username) && "admin123".equals(password)) {
            session.setAttribute("utilisateurConnecte", true);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }

    // Déconnexion
    @GetMapping("/logout")
    public String logout(jakarta.servlet.http.HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // Page dashboard
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    // Page livres
    @GetMapping("/livres")
    public String livres() {
        return "livres";
    }

    // Page magazines
    @GetMapping("/magazines")
    public String magazines() {
        return "magazines";
    }

    // Page utilisateurs
    @GetMapping("/utilisateurs")
    public String utilisateurs() {
        return "utilisateurs";
    }

    // Page emprunts
    @GetMapping("/emprunts")
    public String emprunts() {
        return "emprunts";
    }
}