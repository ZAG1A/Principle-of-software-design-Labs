package com.example.lab7.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.lab7.model.Game;
import com.example.lab7.service.GameService;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // หน้าแสดงรายการเกมทั้งหมด (Read)
    @GetMapping
    public String listGames(Model model) {
        List<Game> games = gameService.getAllGames();
        
        // คำนวณราคาสุทธิหลังหักส่วนลดของแต่ละเกมไว้ส่งไปหน้า HTML
        Map<Long, Double> finalPrices = new HashMap<>();
        for (Game game : games) {
            finalPrices.put(game.getId(), game.calculateFinalPrice() != 0 ? game.calculateFinalPrice() : gameService.calculateFinalPrice(game));
        }

        model.addAttribute("games", games);
        model.addAttribute("finalPrices", finalPrices);
        model.addAttribute("gameService", gameService);
        return "games/list";
    }

    @PostMapping("/update/{id}")
    public String updateGame(@PathVariable("id") Long id, @ModelAttribute("game") Game game, RedirectAttributes redirectAttributes) {
        game.setId(id); // กำหนด ID ให้ตรงกับรายการที่ต้องการอัปเดต
        gameService.saveGame(game); // หรือ gameService.save(game) ตามชื่อ method ใน Service 
        redirectAttributes.addFlashAttribute("message", "แก้ไขข้อมูลเกมสำเร็จ!");
        return "redirect:/games";
    }

    // หน้าฟอร์มเพิ่มเกมใหม่ (Create Form)
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("game", new Game());
        return "games/add";
    }

    // บันทึกเกมลง Database (รองรับทั้ง /add และ /save)
    @PostMapping("/save")
    public String saveGame(@ModelAttribute("game") Game game, RedirectAttributes redirectAttributes) {
        gameService.saveGame(game);
        redirectAttributes.addFlashAttribute("message", "เพิ่มเกม \"" + game.getTitle() + "\" สำเร็จ!");
        return "redirect:/games";
    }

    // หน้าฟอร์มแก้ไขเกม (Update Form)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Game game = gameService.getGameById(id).orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
        model.addAttribute("game", game);
        return "games/edit";
    }

    // บันทึกการแก้ไขเกม
    @PostMapping("/edit/{id}")
    public String updateGame(@PathVariable("id") Long id, @ModelAttribute("game") Game game) {
        game.setId(id);
        gameService.saveGame(game);
        return "redirect:/games";
    }

    // หน้ายืนยันการลบเกม (Delete Confirmation)
    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
        Game game = gameService.getGameById(id).orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
        model.addAttribute("game", game);
        return "games/delete";
    }

    // ยืนยันลบเกมออกจาก Database
    @PostMapping("/delete/{id}") // หรือ @GetMapping("/delete/{id}") ตามที่โปรเจกต์คุณใช้
    public String deleteGame(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        gameService.deleteGame(id);
        redirectAttributes.addFlashAttribute("message", "ลบข้อมูลเกมสำเร็จ!");
        return "redirect:/games";
    }
}