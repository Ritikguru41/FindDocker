package com.lostphone.lostphone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lostphone.lostphone.entity.LostItem;
import com.lostphone.lostphone.repository.LostItemRepository;
import com.lostphone.lostphone.service.MatchService;

    
@Controller
@RequestMapping("/lost")
public class LostItemController {

    @Autowired
    private LostItemRepository lostRepo;

    @Autowired
    private MatchService matchService;

    // Show form
   @GetMapping("/lost/form")
public String lostPhoneForm(Model model) {
    model.addAttribute("lostItem", new LostItem());
    return "police_lost_form";
}

    // Save lost phone
    @PostMapping("/save")
    public String saveLostItem(LostItem item) {
        item.setRecovered(false);
        lostRepo.save(item);
        return "redirect:/police/dashboard";
    }
}


