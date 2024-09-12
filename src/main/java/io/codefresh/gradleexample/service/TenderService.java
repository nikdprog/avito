package io.codefresh.gradleexample.service;

import io.codefresh.gradleexample.entity.Employee;
import io.codefresh.gradleexample.entity.Organization_responsible;
import io.codefresh.gradleexample.entity.Tender;
import io.codefresh.gradleexample.entity.enums.service_type;
import io.codefresh.gradleexample.entity.enums.tender_status;
import io.codefresh.gradleexample.repository.EmployeeRepository;
import io.codefresh.gradleexample.repository.OrganizationRepository;
import io.codefresh.gradleexample.repository.OrganizationResponsibleRepository;
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
    private final OrganizationResponsibleRepository organizationResponsibleRepository;

    @Autowired
    public TenderService(TenderRepository tenderRepository, OrganizationRepository organizationRepository,
                         EmployeeRepository employeeRepository, OrganizationResponsibleRepository organizationResponsibleRepository) {
        this.tenderRepository = tenderRepository;
        this.organizationRepository = organizationRepository;
        this.employeeRepository = employeeRepository;
        this.organizationResponsibleRepository = organizationResponsibleRepository;
    }

    public List<Tender> getAllTenders(service_type serviceType) throws SQLException {
        if (serviceType != null) {
            return tenderRepository.findByServiceType(serviceType);
        } else {
        logDatabaseConnection();
            return tenderRepository.findAll();
        }
    }

    public Optional<Tender> getTendersCurrentUser(String username) throws Exception {
        Optional<Employee> user = employeeRepository.findByUsername(username);
        Employee employee = user.get();
        UUID idUsername = employee.getId();
        Organization_responsible currentUser = organizationResponsibleRepository.findByEmployee_id(idUsername)
                .orElseThrow(() -> new Exception("Organization responsible not found"));
        UUID idOrganization = currentUser.getOrganization().getId();
        Optional<Tender> tendersCurrentUser = tenderRepository.findByOrganizationId(idOrganization);
        return tendersCurrentUser;
    }

    public Tender createTender(String name, String description, service_type serviceType, tender_status status, UUID organizationId, String creatorUsername) throws Exception {
        // проверяем по имени, является ли пользователь ответственным за организацию
        Optional<Employee> employee = employeeRepository.findByUsername(creatorUsername);
        Employee user = employee.get();
        Optional<Organization_responsible> organization_responsible = Optional.ofNullable(organizationResponsibleRepository.findByEmployee_id(user.getId())
                .orElseThrow(() -> new Exception("Organization not found")));
        if(organization_responsible.get().getOrganization().getId().equals(organizationId)) {

            Tender tender = new Tender();
            tender.setName(name);
            tender.setDescription(description);
            tender.setServiceType(serviceType);
            tender.setStatus(status);
            tender.setOrganization(organization_responsible.get().getOrganization());
            tender.setVersion(1);
            tender.setCreatedAt(LocalDateTime.now());
            return tenderRepository.save(tender);
        }
        else return null;
        // доделать
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

    public Optional<Employee> getEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }
    public void saveTender(Tender tender) {
        tenderRepository.save(tender);
    }
}