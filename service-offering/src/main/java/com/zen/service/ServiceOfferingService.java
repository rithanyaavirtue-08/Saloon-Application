package com.zen.service;

import com.zen.dto.CategoryDTO;
import com.zen.dto.SalonDTO;
import com.zen.dto.ServiceDTO;
import com.zen.model.ServiceOffering;

import java.util.Set;


public interface ServiceOfferingService {

    ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO);

    ServiceOffering updateService(Long serviceId,ServiceOffering service) throws Exception;

    Set<ServiceOffering> getAllServiceBySalonId(Long salonId,Long CategoryId);

    Set<ServiceOffering> getServiceByIds(Set<Long> ids);


    ServiceOffering getServiceId(Long id) throws Exception;
}
