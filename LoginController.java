package controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import service.UserService;
import model.users; // Correct User class import

@Controller
public class LoginController {

    private static final String USER2 = "user";
	@Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute(USER2, new users()); // Ensure this is your custom User class
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(USER2) users user, Model model) {
        // Authenticate using your custom UserService
        Optional<users> authenticatedUser = userService.authenticate(user.getUsername(), user.getPassword());
        if (authenticatedUser.isPresent()) {
            switch (authenticatedUser.get().getRole()) { // Now getRole() should work
                case "admin":
                    return "redirect:/admin";
                case "production":
                    return "redirect:/production";
                case "maintenance":
                    return "redirect:/maintenance";
            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }
}
