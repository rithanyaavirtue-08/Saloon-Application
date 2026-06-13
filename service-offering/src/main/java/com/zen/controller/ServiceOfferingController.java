package com.zen.controller;


import com.zen.model.ServiceOffering;
import com.zen.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.function.LongFunction;

@RestController
@RequestMapping("/api/service-offering")
@RequiredArgsConstructor
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServicesBySalonId(
            @PathVariable Long salonId,
            @RequestParam(required = false)Long categoryId
            ){
        Set<ServiceOffering> serviceOfferings=serviceOfferingService.getAllServiceBySalonId(salonId,categoryId);
        return ResponseEntity.ok(serviceOfferings);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServicesById(
            @PathVariable Long id
    ) throws Exception {
        ServiceOffering serviceOffering=serviceOfferingService
                .getServiceId(id);
        return ResponseEntity.ok(serviceOffering);

    }
    @GetMapping("/list{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesBySalonIds(
            @PathVariable Set<Long> ids

    ){
        Set<ServiceOffering> serviceOfferings=serviceOfferingService.getServiceByIds(ids);
        return ResponseEntity.ok(serviceOfferings);

    }
}
