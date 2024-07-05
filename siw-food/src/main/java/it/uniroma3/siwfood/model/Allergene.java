package it.uniroma3.siwfood.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Allergene {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @ManyToMany(mappedBy = "allergeni", cascade = CascadeType.ALL)
    private List<Ingrediente> ingredientiCoinvolti = new ArrayList<>();


    public Allergene() {}

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

    public List<Ingrediente> getIngredientiCoinvolti() {
        return ingredientiCoinvolti;
    }

    public void setIngredientiCoinvolti(List<Ingrediente> ingredientiCoinvolti) {
        this.ingredientiCoinvolti = ingredientiCoinvolti;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
        Allergene other = (Allergene) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }


    public void addIngredienteCoinvolto(Ingrediente ingrediente) {
        this.ingredientiCoinvolti.add(ingrediente);
    }
    

}