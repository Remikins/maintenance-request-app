package com.example.maintenancerequestserver.domain.maintenanceRequest.services;


import com.example.maintenancerequestserver.domain.core.exceptions.ResourceCreationException;
import com.example.maintenancerequestserver.domain.core.exceptions.ResourceNotFoundException;
import com.example.maintenancerequestserver.domain.maintenanceRequest.models.MaintenanceRequest;


import java.util.Date;
import java.util.List;

public interface MaintenanceRequestService {
    MaintenanceRequest create(MaintenanceRequest maintenanceRequest) throws ResourceCreationException;
    MaintenanceRequest getById(Long id) throws ResourceNotFoundException;

    MaintenanceRequest getByEmail(String email) throws ResourceNotFoundException;
    MaintenanceRequest getByAptNumber(String aptNumber) throws ResourceNotFoundException;

    List<MaintenanceRequest> getAll();
    MaintenanceRequest update(Long id, MaintenanceRequest maintenanceRequestDetail) throws ResourceNotFoundException;
    void delete(Long id);


}
