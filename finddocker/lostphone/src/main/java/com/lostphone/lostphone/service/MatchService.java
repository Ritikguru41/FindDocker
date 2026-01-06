package com.lostphone.lostphone.service;

import com.lostphone.lostphone.entity.FoundItem;
import com.lostphone.lostphone.entity.LostItem;
import com.lostphone.lostphone.repository.FoundItemRepository;
import com.lostphone.lostphone.repository.LostItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private LostItemRepository lostItemRepository;

    @Autowired
    private EmailService emailService;

    public void matchFoundItem(LostItem foundItem) {

        List<LostItem> lostItems =
                lostItemRepository.findByImei(foundItem.getImei());

        if (!lostItems.isEmpty()) {
            for (LostItem item : lostItems) {

                item.setMatched(true);
                item.setStatus("FOUND");
                lostItemRepository.save(item);

                // send email
                emailService.sendEmail(
                        item.getOwnerEmail(),
                        "Phone Found",
                        "Your lost phone has been found!"
                );
            }
        }
    }
}


