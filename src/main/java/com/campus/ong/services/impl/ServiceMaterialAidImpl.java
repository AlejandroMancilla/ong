package com.campus.ong.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.campus.ong.config.MaterialDTOConverter;
import com.campus.ong.dto.MaterialDTO;
import com.campus.ong.repositories.RepositoryMaterialAid;
import com.campus.ong.repositories.entities.MaterialAid;
import com.campus.ong.services.ServiceMaterialAid;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceMaterialAidImpl implements ServiceMaterialAid{

    private RepositoryMaterialAid repositoryMaterialAid;
    private MaterialDTOConverter converter;

    @Override
    public List<MaterialDTO> getAllMaterialAid() {
        List<MaterialAid> materials = (List<MaterialAid>) repositoryMaterialAid.findAll();
        return materials.stream()
                        .map(material -> converter.convertMaterialDTO(material))
                        .toList();
    }

    @Override
    public MaterialDTO getMaterialAidById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaterialAidById'");
    }

    @Override
    public MaterialDTO createMaterialAid(MaterialAid material) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createMaterialAid'");
    }

    @Override
    public MaterialAid updateMaterialAid(MaterialAid material) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMaterialAid'");
    }

    @Override
    public void deleteMaterialAid(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMaterialAid'");
    }

}
