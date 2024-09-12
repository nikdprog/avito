package io.codefresh.gradleexample.controller;

import io.codefresh.gradleexample.entity.Employee;
import io.codefresh.gradleexample.entity.Tender;
import io.codefresh.gradleexample.entity.enums.*;
import io.codefresh.gradleexample.repository.EmployeeRepository;
import io.codefresh.gradleexample.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TenderController {

    private final TenderService tenderService;

    @Autowired
    public TenderController(TenderService tenderService) {
        this.tenderService = tenderService;
    }

    @GetMapping("/api/tenders")
    public ResponseEntity<List<Tender>> getTenders(
            @RequestParam(required = false) service_type serviceType) throws SQLException {
        List<Tender> tenders = tenderService.getAllTenders(serviceType);
        return ResponseEntity.ok(tenders);
    }

    @Transactional
    @RequestMapping("api/tenders/new")
    public ResponseEntity<?> createNewTender(
            @RequestParam String name, @RequestParam String description, @RequestParam service_type serviceType, @RequestParam tender_status status,
            @RequestParam UUID organizationId, @RequestParam String creatorUsername) {

        try {
            Tender newTender = tenderService.createTender(name, description, serviceType, status, organizationId, creatorUsername);
            return ResponseEntity.ok(newTender);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("api/tenders/my")
    public ResponseEntity<Optional<Tender>> getTendersCurrentUser(@RequestParam String username) throws Exception {

        Optional<Tender> tenders = tenderService.getTendersCurrentUser(username);
        return ResponseEntity.ok(tenders);
    }

    @RequestMapping("api/tenders/{tenderId}/edit")
    public ResponseEntity<Tender> editTender(@PathVariable UUID tenderId, @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String description,
                                             @RequestParam(required = false) service_type serviceType) {
        Optional<Tender> tender = tenderService.getTendersById(tenderId);
        Tender tenderUpdate = tender.get();
        if(name != null) {
            tenderUpdate.setName(name);
        }
        if(description != null) {
            tenderUpdate.setDescription(description);
        }
        if(serviceType != null) {
            tenderUpdate.setServiceType(serviceType);
        }
        tenderUpdate.setVersion(tenderUpdate.getVersion() + 1); // увеличиваем версию тендера
        tenderService.saveTender(tenderUpdate);
        return ResponseEntity.ok(tenderUpdate);
    }

    @GetMapping("api/tenders/{tenderId}/status")
    public ResponseEntity<tender_status> getStatusTender(@PathVariable UUID tenderId, @RequestParam String username) {
        Optional<Tender> tender = tenderService.getTendersById(tenderId);
        Tender currentTender = tender.get();
        return ResponseEntity.ok(currentTender.getStatus());
    }

    @PutMapping("api/tenders/{tenderId}/status")
    public ResponseEntity<Tender> editStatusTender(@PathVariable UUID tenderId,
                                                   @RequestParam tender_status tenderStatus,
                                                   @RequestParam(required = false) String username) {
        System.out.println("Username: " + username);

        Optional<Tender> tender = tenderService.getTendersById(tenderId);
        Tender currentTender = tender.get();


        currentTender.setStatus(tenderStatus);

        return ResponseEntity.ok(currentTender);
    }
}