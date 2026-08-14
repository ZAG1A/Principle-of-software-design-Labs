package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "นายอนันต์เอกก์ ใหญ่พงศกร"); //ชื่อ นามสกุล
        model.addAttribute("studentId", "673380430-9"); // รหัสนักศึกษา
        return "home";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("info", "นักศึกษาวิทยาลัยการคอมพิวเตอร์ KKU");
        return "about";
}
}