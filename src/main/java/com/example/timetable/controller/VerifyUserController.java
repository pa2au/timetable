package com.example.timetable.controller;


import com.example.timetable.service.VerifyUserService;
import com.example.timetable.entity.VerifyUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/verify")
@RequiredArgsConstructor
public class VerifyUserController {
    private final VerifyUserService verifyUserService;

    @GetMapping()
    public String confirm(Model model, @RequestParam(name = "code") String token) {
        Optional<VerifyUser> user = verifyUserService.findVerifyUserByToken(token);
        if (user.isPresent() && !user.get().getIsActive()) {
            user.get().setIsActive(true);
            verifyUserService.save(user.get());
            model.addAttribute("user", user.get().getIsActive());
        } else {
            model.addAttribute("user", false);
        }
        return "verifyUser";
    }
}
