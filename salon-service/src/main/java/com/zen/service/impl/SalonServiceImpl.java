package com.zen.service.impl;

import com.zen.model.Salon;
import com.zen.payload.dto.SalonDTO;
import com.zen.payload.dto.UserDTO;
import com.zen.repositary.SalonRepository;
import com.zen.service.SalonService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;


    @Override
    public Salon createSalon(SalonDTO req, UserDTO user) {
        Salon salon=new Salon();
        salon.setName(req.getName());
        salon.setAddress(req.getAddress());
        salon.setCity(req.getCity());
        salon.setEmail(req.getEmail());
        salon.setCity(req.getCity());
        salon.setImages(req.getImages());
        salon.setOwnerId(req.getOwnerId());
        salon.setOpenTime(req.getOpenTime());
        salon.setCloseTime(req.getCloseTime());
        salon.setPhoneNumber(req.getPhoneNumber());

        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception {
        System.out.println("ID received = " + salonId);
        Salon existingsalon = salonRepository.findById(salonId)
                .orElseThrow(() -> new Exception("Salon does not exist"));

        if(salon.getOwnerId().equals(user.getId())){
             existingsalon.setCity(salon.getCity());
                existingsalon.setName(salon.getName());
                existingsalon.setAddress(salon.getAddress());
                existingsalon.setEmail(salon.getEmail());
                existingsalon.setImages(salon.getImages());
                existingsalon.setOpenTime(salon.getOpenTime());
                existingsalon.setCloseTime(salon.getCloseTime());
                existingsalon.setPhoneNumber(salon.getPhoneNumber());
                existingsalon.setOwnerId(salon.getOwnerId());

                salonRepository.save(existingsalon);


                return salonRepository.save(existingsalon);


        }
       throw new Exception("Salon does not exist");

    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) throws Exception {
        Salon salon=salonRepository.findById(salonId).orElse(null);
        if(salon==null){
            throw new Exception("Salon not exist");
        }
        return salon;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return salonRepository.searchSalons(city);
    }
}
