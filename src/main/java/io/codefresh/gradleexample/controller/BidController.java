package io.codefresh.gradleexample.controller;

import io.codefresh.gradleexample.entity.Bid;
import io.codefresh.gradleexample.entity.Tender;
import io.codefresh.gradleexample.entity.enums.*;
import io.codefresh.gradleexample.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class BidController {

    private final BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @RequestMapping("api/bids/new")
    public ResponseEntity<?> createNewBid(@RequestParam String name, @RequestParam String description, @RequestParam UUID tenderId,
                                          @RequestParam author_type authorType, @RequestParam UUID authorId) {
        try {
            Bid newBid = bidService.createBid(name, description, tenderId, authorType, authorId);
            return ResponseEntity.ok(newBid);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("api/bids/my")
    public ResponseEntity<?> getBidsCurrentUser(@RequestParam String username) {
        Optional<Bid> bids = bidService.getBidsCurrentUser(username);
        return ResponseEntity.ok(bids);
    }

    @RequestMapping("api/bids/{tenderId}/list")
    public ResponseEntity<?> getBidsOfTenders(@PathVariable UUID tenderId, @RequestParam(required = false) String username) {
        Optional<Bid> bids = bidService.getBidsOfTender(tenderId, username);

        return ResponseEntity.ok(bids);
    }

    @GetMapping("api/bids/{bidId}/status")
    public ResponseEntity<bid_status> getStatusTender(@PathVariable UUID bidId, @RequestParam(required = false) String username) {
        Optional<Bid> bid = bidService.getBidById(bidId);
        Bid currentBid = bid.get();
        return ResponseEntity.ok(currentBid.getStatus());
    }

    @PutMapping("api/bids/{bidId}/status")
    public ResponseEntity<Bid> editStatusTender(@PathVariable UUID bidId,
                                                   @RequestParam bid_status bidStatus,
                                                   @RequestParam(required = false) String username) {
        System.out.println("Username: " + username);

        Optional<Bid> bid = bidService.getBidById(bidId);
        System.out.println(bid.get());
        Bid targetBid = bid.get();
        System.out.println("ID :" + targetBid.getId());
        targetBid.setStatus(bidStatus);

        return ResponseEntity.ok(targetBid);
    }

    @RequestMapping("api/bids/{bidId}/edit")
    public ResponseEntity<Bid> editTender(@PathVariable UUID bidId, @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String description,
                                             @RequestParam(required = false) String username) {
        Optional<Bid> bid = bidService.getBidById(bidId);
        Bid bidUpdate = bid.get();

        if(name != null) {
            bidUpdate.setName(name);
        }
        if(description != null) {
            bidUpdate.setDescription(description);
        }

        bidUpdate.setVersion(bidUpdate.getVersion() + 1); // увеличиваем версию тендера
        bidService.saveBid(bidUpdate);
        return ResponseEntity.ok(bidUpdate);
    }
}
