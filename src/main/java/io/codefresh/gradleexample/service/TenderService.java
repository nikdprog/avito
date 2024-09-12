package io.codefresh.gradleexample.service;

import io.codefresh.gradleexample.entity.Employee;
import io.codefresh.gradleexample.entity.Organization;
import io.codefresh.gradleexample.entity.Tender;
import io.codefresh.gradleexample.repository.EmployeeRepository;
import io.codefresh.gradleexample.repository.OrganizationRepository;
import io.codefresh.gradleexample.repository.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TenderService {

    private final TenderRepository tenderRepository;
    private final OrganizationRepository organizationRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public TenderService(TenderRepository tenderRepository, OrganizationRepository organizationRepository, EmployeeRepository employeeRepository) {
        this.tenderRepository = tenderRepository;
        this.organizationRepository = organizationRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Tender> getAllTenders(String serviceType) throws SQLException {
        if (serviceType != null && !serviceType.isEmpty()) {
            return tenderRepository.findByServiceType(serviceType);
        } else {
        logDatabaseConnection();
            return tenderRepository.findAll();
        }
    }

    public Optional<Tender> getTendersCurrentUser(String username) {
        Optional<Employee> user = employeeRepository.findByUsername(username);

        //List<Tender> lst = new ArrayList<>();

        Employee employee = user.get();
        UUID idUsername = employee.getId();
        Optional<Tender> tendersCurrentUser = tenderRepository.findById(idUsername);

        return tenderRepository.findById(idUsername);
    }

    public Tender createTender(String name, String description, String serviceType, String status, Long organizationId, String creatorUsername) throws Exception {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new Exception("Organization not found"));

        Employee creator = employeeRepository.findByUsername(creatorUsername)
                .orElseThrow(() -> new Exception("User not found"));

        Tender tender = new Tender();
        tender.setName(name);
        tender.setDescription(description);
        tender.setServiceType(serviceType);
        tender.setStatus(status);
        tender.setOrganization(organization);

        tender.setCreatedAt(LocalDateTime.now());
        //tender.setUpdatedAt(LocalDateTime.now());

        return tenderRepository.save(tender);
    }
    @Autowired
    private DataSource dataSource;

    @EventListener(ApplicationReadyEvent.class)
    public void logDatabaseConnection() throws SQLException {
        System.out.println("Connected to: " + dataSource.getConnection().getMetaData().getURL());
    }
    public Optional<Tender> getTendersById(UUID id) {
        return tenderRepository.findById(id);
    }

    public void saveTender(Tender tender) {
        tenderRepository.save(tender);
    }
}