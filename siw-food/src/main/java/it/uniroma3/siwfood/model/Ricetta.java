package it.uniroma3.siwfood.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ricetta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descrizione;
    private String ingredienti;
    private String urlImmagine;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public String getIngredienti() {
        return ingredienti;
    }
    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }
    public String getUrlImmagine() {
        return urlImmagine;
    }
    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }

    
}
