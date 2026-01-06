package com.lostphone.lostphone.controller;

import com.lostphone.lostphone.entity.FoundItem;
import com.lostphone.lostphone.entity.LostItem;
import com.lostphone.lostphone.repository.FoundItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/police")
public class PoliceController {

    @Autowired
    private FoundItemRepository foundRepo;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("items", foundRepo.findAll());
        return "police_dashboard";
    }


    // 👀 View Finder Uploaded Items
    @GetMapping("/finder/items")
    public String viewFinderItems(Model model) {

        List<FoundItem> items = foundRepo.findAll();
        model.addAttribute("items", items);

        return "police_finder_items";
    }

    // ✅ Verify item
    @GetMapping("/verify/{id}")
    public String verifyItem(@PathVariable Long id) {

        FoundItem item = foundRepo.findById(id).orElse(null);
        if (item != null) {
            item.setStatus("VERIFIED");
            foundRepo.save(item);
        }

        return "redirect:/police/finder/items";
    }
    //   @GetMapping("/lost/form")
    // public String lostPhoneForm(Model model) {
    //     model.addAttribute("lostItem", new LostItem());
    //     return "police_lost_form";
    // }

    // // ===================== SAVE LOST PHONE =====================
  
    // private final FoundItemRepository lostRepo;

    // public PoliceController(FoundItemRepository lostRepo) {
    //     this.lostRepo = lostRepo;
    // }

    // // ✅ EXACT MATCH
    // @PostMapping("/lost/save")
    // public String saveLostPhone(@ModelAttribute LostItem lostItem) {
    //     lostRepo.save(lostItem);
    //     return "redirect:/police/dashboard";
    // }

    // 📦 Mark Returned
    @GetMapping("/return/{id}")
    public String markReturned(@PathVariable Long id) {

        FoundItem item = foundRepo.findById(id).orElse(null);
        if (item != null) {
            item.setStatus("RETURNED");
            item.setRecovered(true);
            foundRepo.save(item);
        }

        return "redirect:/police/finder/items";
    }
}
