package com.example.maintenancerequestserver.domain.maintenanceRequest.services;

import com.example.maintenancerequestserver.domain.core.exceptions.ResourceCreationException;
import com.example.maintenancerequestserver.domain.core.exceptions.ResourceNotFoundException;
import com.example.maintenancerequestserver.domain.maintenanceRequest.models.MaintenanceRequest;
import com.example.maintenancerequestserver.domain.maintenanceRequest.repos.MaintenanceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceRequestServiceImpl implements MaintenanceRequestService{
    private MaintenanceRequestRepository maintenanceRequestRepository;
    @Autowired
    public MaintenanceRequestServiceImpl(MaintenanceRequestRepository maintenanceRequestRepository) {
        this.maintenanceRequestRepository = maintenanceRequestRepository;
    }

    @Override
    public MaintenanceRequest create(MaintenanceRequest maintenanceRequest) throws ResourceCreationException {
        Optional<MaintenanceRequest> optional = maintenanceRequestRepository.findByEmail(maintenanceRequest.getEmail());
        if(optional.isPresent())
            throw new ResourceCreationException("Maintenance Request with email exists: " + maintenanceRequest.getEmail());
        maintenanceRequest = maintenanceRequestRepository.save(maintenanceRequest);
        return maintenanceRequest;
    }

    @Override
    public MaintenanceRequest getById(Long id) throws ResourceNotFoundException {
        MaintenanceRequest maintenanceRequest = maintenanceRequestRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No Maintenance Request with id: " + id));
        return maintenanceRequest;
    }

    @Override
    public MaintenanceRequest getByEmail(String email) throws ResourceNotFoundException {
        MaintenanceRequest maintenanceRequest = maintenanceRequestRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("No Maintenance Request with email: " + email));
        return maintenanceRequest;
    }

    @Override
    public MaintenanceRequest getByAptNumber(String aptNumber) throws ResourceNotFoundException {
        MaintenanceRequest maintenanceRequest = maintenanceRequestRepository.findByAptNumber(aptNumber)
                .orElseThrow(()->new ResourceNotFoundException("No Maintenance Request with AptNumber: " + aptNumber));
        return maintenanceRequest;
    }

    @Override
    public List<MaintenanceRequest> getAll() {
        return maintenanceRequestRepository.findAll();
    }

    @Override
    public MaintenanceRequest update(Long id, MaintenanceRequest maintenanceRequestDetail) throws ResourceNotFoundException {
        MaintenanceRequest maintenanceRequest = getById(id);
        maintenanceRequest.setFirstName(maintenanceRequestDetail.getFirstName());
        maintenanceRequest.setLastName(maintenanceRequestDetail.getLastName());
        maintenanceRequest.setEmail(maintenanceRequest.getEmail());
        maintenanceRequest.setAptNumber(maintenanceRequest.getAptNumber());
        maintenanceRequest.setDescription(maintenanceRequest.getDescription());
        maintenanceRequest.setCreatedAt(maintenanceRequest.getCreatedAt());
        return maintenanceRequest;
    }

    @Override
    public void delete(Long id) {
        MaintenanceRequest maintenanceRequest = getById(id);
        maintenanceRequestRepository.delete(maintenanceRequest);

    }
}
