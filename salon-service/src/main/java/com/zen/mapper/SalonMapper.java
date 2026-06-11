package com.zen.mapper;

import com.zen.model.Salon;
import com.zen.payload.dto.SalonDTO;

public class SalonMapper {

    public static SalonDTO mapToDTO(Salon salon){
        SalonDTO salonDTO=new SalonDTO();
        salonDTO.setId(salon.getId());
        salonDTO.setName(salon.getName());
        salonDTO.setCity(salon.getCity());
        salonDTO.setAddress(salon.getAddress());
        salonDTO.setEmail(salon.getEmail());
        salonDTO.setImages(salon.getImages());
        salonDTO.setOpenTime(salon.getOpenTime());
        salonDTO.setCloseTime(salon.getCloseTime());
        salonDTO.setPhoneNumber(salon.getPhoneNumber());
        salonDTO.setOwnerId(salon.getOwnerId());
        salonDTO.setEmail(salon.getEmail());
        return salonDTO;
    }
}
