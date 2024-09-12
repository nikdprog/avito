package io.codefresh.gradleexample.service;

import io.codefresh.gradleexample.entity.*;
import io.codefresh.gradleexample.entity.enums.*;
import io.codefresh.gradleexample.repository.BidRepository;
import io.codefresh.gradleexample.repository.EmployeeRepository;
import io.codefresh.gradleexample.repository.OrganizationRepository;
import io.codefresh.gradleexample.repository.OrganizationResponsibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class BidService {

    private final EmployeeRepository employeeRepository;
    private final OrganizationResponsibleRepository organizationResponsibleRepository;
    private final TenderService tenderService;
    private final OrganizationRepository organizationRepository;
    private final BidRepository bidRepository;

    @Autowired
    public BidService(EmployeeRepository employeeRepository, OrganizationResponsibleRepository organizationResponsibleRepository,
                      TenderService tenderService, OrganizationRepository organizationRepository, BidRepository bidRepository) {
        this.employeeRepository = employeeRepository;
        this.organizationResponsibleRepository = organizationResponsibleRepository;
        this.tenderService = tenderService;
        this.organizationRepository = organizationRepository;
        this.bidRepository = bidRepository;
    }

    public Bid createBid(String name, String description, UUID tenderId, author_type authorType, UUID authorId) throws Exception {
        // выясняем, является ли пользователь ответственным за организацию
        // проверяем по имени, является ли пользователь ответственным за организацию
        Employee currentEmployee = null;
        Tender resultTender = null;
        Optional<Organization_responsible> organization_responsible = organizationResponsibleRepository.findByEmployee_id(authorId);

        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findById(authorId))
                .orElseThrow(() -> new Exception("User not found"));
        if (employee.isPresent()) {
            currentEmployee = employee.get();
        } else System.out.println("User not exist");

        Optional<Tender> tender = tenderService.getTendersById(tenderId);
        if(tender.isPresent()) {
           resultTender = tender.get();
        } else System.out.println("tender not found");
        // проверить в зависимости от типа автора, существует ли организация или пользователь
        if (currentEmployee != null && organization_responsible.isPresent()) {
            Bid bid = new Bid();
            bid.setName(name);
            bid.setDescription(description);
            bid.setStatus(bid_status.Created);
            bid.setTender(resultTender);
            bid.setAuthor_type(authorType);
            bid.setAuthor(currentEmployee);
            bid.setVersion(1);
            bid.setCreated_at(LocalDateTime.now());
            return bidRepository.save(bid);
        }
        else return null;
    }

    public Optional<Bid> getBidsCurrentUser(String username) {
        //Optional<Bid> bids = bidRepository.f
        Optional<Employee> employee = employeeRepository.findByUsername(username);
        Employee user = employee.get();
        Optional<Bid> bids = bidRepository.findByAuthorId(user.getId());
        return bids;
    }

    public Optional<Bid> getBidsOfTender(UUID tenderId, String username) {
        return bidRepository.findByTenderId(tenderId);
    }

    public Optional<Bid> getBidById(UUID bidId) {
        return bidRepository.findById(bidId);
    }

    public void saveBid(Bid bid) {
        bidRepository.save(bid);
    }
}
