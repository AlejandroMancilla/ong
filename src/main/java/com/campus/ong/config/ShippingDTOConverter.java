package com.campus.ong.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.campus.ong.dto.ShippingDTO;
import com.campus.ong.repositories.entities.Shipping;

@Component
public class ShippingDTOConverter {
    
    @Autowired
    private ModelMapper dbm;

    @Autowired
    private MaterialDTOConverter materialDTOConverter;

    public ShippingDTOConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm = modelMapper;
    }

    public ShippingDTO convertShippingDTO(Shipping shipping) {
        
        ShippingDTO shippingDTO = dbm.map(shipping, ShippingDTO.class);
        shippingDTO.setShelter_name(shipping.getShelter().getName());
        shippingDTO.setCampus_names(shipping.getCampuses()
                                .stream()
                                .map(campus -> campus.getCity().getName())
                                .toList());
        shippingDTO.setMaterials(shipping.getMaterialAids()
                                .stream()
                                .map(material -> materialDTOConverter.convertMaterialDTO(material))
                                .toList());                      
        return shippingDTO;

    }

    public Shipping convertShipping(ShippingDTO shippingDTO) {
        
        Shipping shipping = dbm.map(shippingDTO, Shipping.class);
        return shipping;

    }
    
}
