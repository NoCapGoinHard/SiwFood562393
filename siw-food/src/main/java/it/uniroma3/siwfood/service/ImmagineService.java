package it.uniroma3.siwfood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.Immagine;
import it.uniroma3.siwfood.repository.ImmagineRepository;
import jakarta.transaction.Transactional;

@Service
public class ImmagineService {
    
    @Autowired
    private ImmagineRepository immagineRepository;

    public Immagine findById(Long id){
        return this.immagineRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Immagine immagine){
        this.immagineRepository.delete(immagine);
    }

    @Transactional
    public Immagine save(Immagine immagine){
        return this.immagineRepository.save(immagine);
    }
}
