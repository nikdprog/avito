package io.codefresh.gradleexample.service;

import io.codefresh.gradleexample.dto.TenderDTO;
import io.codefresh.gradleexample.entity.Employee;
import io.codefresh.gradleexample.entity.Organization;
import io.codefresh.gradleexample.entity.Tender;
import io.codefresh.gradleexample.repository.EmployeeRepository;
import io.codefresh.gradleexample.repository.OrganizationRepository;
import io.codefresh.gradleexample.repository.TenderRepository;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Tender> getAllTenders(String serviceType) {
        if (serviceType != null && !serviceType.isEmpty()) {
            return tenderRepository.findByServiceType(serviceType);
        } else {
            return tenderRepository.findAll();
        }
    }

    public Optional<Tender> getTendersCurrentUser(String username) {
        Optional<Employee> user = employeeRepository.findByUsername(username);

        //List<Tender> lst = new ArrayList<>();

        Employee employee = user.get();
        Long idUsername = employee.getId();
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
        tender.setCreator(creator);
        tender.setCreatedAt(LocalDateTime.now());
        tender.setUpdatedAt(LocalDateTime.now());

        return tenderRepository.save(tender);
    }

    public Optional<Tender> getTendersById(Long id) {
        return tenderRepository.findById(id);
    }

    public void saveTender(Tender tender) {
        tenderRepository.save(tender);
    }
}