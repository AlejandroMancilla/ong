package com.campus.ong.config;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.campus.ong.dto.RequerimentHDTO;
import com.campus.ong.dto.ShippingDTO;
import com.campus.ong.repositories.entities.Shipping;

import lombok.extern.slf4j.Slf4j;

@Component
public class ShippingDTOConverter {
    
    @Autowired
    private ModelMapper dbm;

    @Autowired
    private MaterialDTOConverter materialDTOConverter;
    private RequerimentHDTOConverter requerimentHDTOConverter;

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
                                
        // Verificar si shipping.getRequeriments() es nulo
        List<RequerimentHDTO> requerimentsDTOList = shipping.getRequeriments().isEmpty() ?
        shipping.getRequeriments()
                .stream()
                .map(requeriment -> requerimentHDTOConverter.convertRequerimentDTO(requeriment))
                .toList() :
        Collections.emptyList(); // Si es nulo, asignar una lista vacía

        shippingDTO.setRequeriments(requerimentsDTOList);
                                                      
        return shippingDTO;

    }

    public Shipping convertShipping(ShippingDTO shippingDTO) {
        
        Shipping shipping = dbm.map(shippingDTO, Shipping.class);
        return shipping;

    }
    
}
