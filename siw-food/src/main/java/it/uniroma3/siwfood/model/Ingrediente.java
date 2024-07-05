package it.uniroma3.siwfood.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Ingrediente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;
    @NotBlank
    private String quantita;

    @ManyToOne
    @JoinColumn(name = "ricettaId")
    private Ricetta ricetta;


    @ManyToMany
    @JoinTable( name = "ingrediente_allergene",
                joinColumns = @JoinColumn(name = "ingredienteId"),
                inverseJoinColumns = @JoinColumn(name = "allergeneId"))
    private List<Allergene> allergeni = new ArrayList<>();

    
    public Ingrediente(){}

    public Ingrediente(String nome, String quantita, Ricetta ricetta){
        this.nome = nome;
        this.quantita = quantita;
        this.ricetta = ricetta;
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
    
    public String getQuantita() {
        return quantita;
    }
    
    public void setQuantita(String quantita) {
        this.quantita = quantita;
    }
    
    public Ricetta getRicetta() {
        return ricetta;
    }
    
    public void setRicetta(Ricetta ricetta) {
        this.ricetta = ricetta;
    }
    
    
    public List<Allergene> getAllergeni() {
            return allergeni;
        }


    public void setAllergeni(List<Allergene> allergeni) {
        this.allergeni = allergeni;
    }
    
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((quantita == null) ? 0 : quantita.hashCode());
        result = prime * result + ((ricetta == null) ? 0 : ricetta.hashCode());
        result = prime * result + ((allergeni == null) ? 0 : allergeni.hashCode());
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
        if (allergeni == null) {
            if (other.allergeni != null)
                return false;
        } else if (!allergeni.equals(other.allergeni))
        return false;
        return true;
    }
    
//////////////////////////////////////////////////////////////////////

    public void addAllergene(Allergene allergene) {
        this.allergeni.add(allergene);
    }

    public void removeAllergene(Allergene allergene) {
        this.allergeni.remove(allergene);
    }

}
