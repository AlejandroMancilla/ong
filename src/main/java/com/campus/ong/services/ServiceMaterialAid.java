package com.campus.ong.services;

import java.util.List;

import com.campus.ong.dto.MaterialDTO;
import com.campus.ong.repositories.entities.MaterialAid;

public interface ServiceMaterialAid {
    
    List<MaterialDTO> getAllMaterialAid();

    MaterialDTO getMaterialAidById(Long id);

    MaterialDTO createMaterialAid(MaterialAid material);

    MaterialAid updateMaterialAid(MaterialAid material);

    void deleteMaterialAid(Long id);

}
