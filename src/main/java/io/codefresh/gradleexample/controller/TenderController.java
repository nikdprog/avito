package io.codefresh.gradleexample.controller;

import io.codefresh.gradleexample.entity.Tender;
import io.codefresh.gradleexample.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TenderController {

    private final TenderService tenderService;

    @Autowired
    public TenderController(TenderService tenderService) {
        this.tenderService = tenderService;
    }

    @GetMapping("/api/tenders")
    public ResponseEntity<List<Tender>> getTenders(
            @RequestParam(required = false) String serviceType) {
        List<Tender> tenders = tenderService.getAllTenders(serviceType);
        return ResponseEntity.ok(tenders);
    }

    @RequestMapping("api/tenders/new")
    public ResponseEntity<?> createNewTender(
            @RequestParam String name, @RequestParam String description, @RequestParam String serviceType, @RequestParam String status,
            @RequestParam Long organizationId, @RequestParam String creatorUsername) {
        try {
            Tender newTender = tenderService.createTender(name, description, serviceType,status,organizationId,creatorUsername);
            return ResponseEntity.ok(newTender);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("api/tenders/my")
    public ResponseEntity<Optional<Tender>> getTendersCurrentUser(@RequestParam String username) {
        Optional<Tender> tenders = tenderService.getTendersCurrentUser(username);
        return ResponseEntity.ok(tenders);
    }

    @RequestMapping("api/tenders/{tenderId}/edit")
    public ResponseEntity<Tender> editTender(@PathVariable Long tenderId, @RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String description) {
        Optional<Tender> tender = tenderService.getTendersById(tenderId);
        Tender tenderUpdate = tender.get();
        if(name != null) {
            tenderUpdate.setName(name);
        }
        if(description != null) {
            tenderUpdate.setDescription(description);
        }
        tenderUpdate.setVersion(tenderUpdate.getVersion() + 1); // увеличиваем версию тендера
        tenderService.saveTender(tenderUpdate);
        return ResponseEntity.ok(tenderUpdate);
    }


}