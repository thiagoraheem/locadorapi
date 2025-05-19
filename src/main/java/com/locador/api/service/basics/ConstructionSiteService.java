package com.locador.api.service.basics;

import com.locador.api.model.basics.ConstructionSite;
import com.locador.api.repository.basics.ConstructionSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConstructionSiteService {

    @Autowired
    private ConstructionSiteRepository constructionSiteRepository;

    public List<ConstructionSite> findAll(){
        return constructionSiteRepository.findAll();
    }

    public Optional<ConstructionSite> findById(Integer id){
        return constructionSiteRepository.findById(id);
    }

    public ConstructionSite save(ConstructionSite constructionSite){
        return constructionSiteRepository.save(constructionSite);
    }

    public ConstructionSite update(Integer id, ConstructionSite constructionSite){
        if(!constructionSiteRepository.existsById(id)){
            throw new RuntimeException("Não Encontrado");
        }
        constructionSite.setId(id);
        return constructionSiteRepository.save(constructionSite);
    }

    public void delete(Integer id) {
        if(!constructionSiteRepository.existsById(id)){
            throw new RuntimeException("não encontrado");
        }
        constructionSiteRepository.deleteById(id);
    }
}
