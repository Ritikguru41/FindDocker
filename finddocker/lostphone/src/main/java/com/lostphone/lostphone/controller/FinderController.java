package com.lostphone.lostphone.controller;

import com.lostphone.lostphone.entity.FoundItem;
import com.lostphone.lostphone.repository.FoundItemRepository;
import com.lostphone.lostphone.service.FileStorageService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/finder")
public class FinderController {

    @Autowired
    private FoundItemRepository foundRepo;

    @Autowired
    private FileStorageService fileService;

    // ✅ Finder Dashboard
    @GetMapping("/dashboard")
    public String finderDashboard(Authentication auth, Model model) {

        String finderUsername = auth.getName(); // logged-in finder
        List<FoundItem> items = foundRepo.findByFinderUsername(finderUsername);

        model.addAttribute("items", items);
        return "finder_dashboard";
    }

    // ✅ Open upload form
    @GetMapping("/finder/upload")
    public String uploadForm() {
        return "finder_upload";
    }
    @GetMapping("/upload")
public String blockGetUpload() {
    return "finder_upload";
}


    // ✅ Handle upload
    @PostMapping("/upload")
    public String submitFoundItem(
            @RequestParam String itemType,
            @RequestParam String brand,
            @RequestParam String imei,
            @RequestParam String documentType,
            @RequestParam MultipartFile document,
            Authentication authentication
    ) throws IOException {

        // Logged-in finder
        String finderUsername = authentication.getName();

        // Save file using service
        String documentPath = fileService.saveFile(document);

        // Save data
        FoundItem item = new FoundItem();
        item.setItemType(itemType);
        item.setBrand(brand);
        item.setImei(imei);
        item.setDocumentType(documentType);
        item.setDocumentPath(documentPath);
        item.setFinderUsername(finderUsername);
        item.setStatus("FOUND");

        foundRepo.save(item);

        return "redirect:/finder/dashboard";
    }
}
