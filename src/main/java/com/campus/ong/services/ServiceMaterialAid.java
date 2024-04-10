package com.campus.ong.services;

import java.util.List;

import com.campus.ong.dto.MaterialDTO;
import com.campus.ong.repositories.entities.MaterialAid;

public interface ServiceMaterialAid {
    
    List<MaterialDTO> getAllMaterialAid();

    MaterialDTO findById(Long id);

    MaterialDTO save(MaterialAid material);

    MaterialAid update(MaterialAid material);

    void delete(Long id);

}
