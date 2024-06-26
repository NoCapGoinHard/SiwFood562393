package it.uniroma3.siwfood.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private Integer quantita;
    @ManyToOne
    @JoinColumn(name = "ricettaId")
    private Ricetta ricetta;
    
    private List<String> allergeni;
    
    public Ingrediente() {
        this.allergeni = new ArrayList<>();
    }
    
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
    
    public Integer getQuantita() {
        return quantita;
    }
    
    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public Ricetta getRicetta() {
        return ricetta;
    }

    public void setRicetta(Ricetta ricetta) {
        this.ricetta = ricetta;
    }
    
    public List<String> getAllergeni() {
        return allergeni;
    }
    
    public void setAllergeni(List<String> allergeni) {
        this.allergeni = allergeni;
    }
    
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((quantita == null) ? 0 : quantita.hashCode());
        result = prime * result + ((ricetta == null) ? 0 : ricetta.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        return true;
        if (obj == null)
        return false;
        if (getClass() != obj.getClass())
        return false;
        Ingrediente other = (Ingrediente) obj;
        if (nome == null) {
            if (other.nome != null)
            return false;
        } else if (!nome.equals(other.nome))
        return false;
        if (quantita == null) {
            if (other.quantita != null)
            return false;
        } else if (!quantita.equals(other.quantita))
        return false;
        if (ricetta == null) {
            if (other.ricetta != null)
            return false;
        } else if (!ricetta.equals(other.ricetta))
        return false;
        return true;
    }
    
    //////////////////////////////////////////////////////////////////////
    
    public void addAllergene(String allergene) {
        this.allergeni.add(allergene);
    }
    
}
