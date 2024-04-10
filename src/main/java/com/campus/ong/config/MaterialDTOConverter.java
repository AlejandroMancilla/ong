package com.campus.ong.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.campus.ong.dto.MaterialDTO;
import com.campus.ong.repositories.entities.MaterialAid;

@Component
public class MaterialDTOConverter {
    
    @Autowired
    private ModelMapper dbm;

    public MaterialDTOConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm = modelMapper;
    }

    public MaterialDTO convertMaterialDTO(MaterialAid materialAid) {
        
        MaterialDTO materialDTO = dbm.map(materialAid, MaterialDTO.class);
        materialDTO.setProduct_name(materialAid.getProduct().getName());
        return materialDTO;

    }

    public MaterialAid convertMaterialAid(MaterialDTO materialDTO) {
        
        MaterialAid materialAid = dbm.map(materialDTO, MaterialAid.class);
        return materialAid;

    }

}
