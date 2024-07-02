package it.uniroma3.siwfood.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Ricetta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private String descrizione;
    @OneToMany(mappedBy = "ricetta", cascade = CascadeType.ALL)
    private List<Ingrediente> ingredienti;
    @ElementCollection
    private List<Immagine> immagini;
    @ManyToOne
    @JoinColumn(name = "cuoco_id")
    private Cuoco cuoco;

    public Ricetta() {
        this.ingredienti = new ArrayList<>();

    }

    public Ricetta(Long id, String nome, String descrizione, List<Ingrediente> ingredienti, List<Immagine> immagini, Cuoco cuoco) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
        this.immagini = immagini;
        this.cuoco = cuoco;
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
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public List<Ingrediente> getIngredienti() {
        return ingredienti;
    }
    public void setIngredienti(List<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public List<Immagine> getImmagini() {
        return immagini;
    }
    public void setPathFoto(List<Immagine> immagini) {
        this.immagini = immagini;
    }
    public Cuoco getCuoco() {
        return cuoco;
    }
    public void setCuoco(Cuoco cuoco) {
        this.cuoco = cuoco;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((ingredienti == null) ? 0 : ingredienti.hashCode());
        result = prime * result + ((cuoco == null) ? 0 : cuoco.hashCode());
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
        Ricetta other = (Ricetta) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (ingredienti == null) {
            if (other.ingredienti != null)
                return false;
        } else if (!ingredienti.equals(other.ingredienti))
            return false;
        if (cuoco == null) {
            if (other.cuoco != null)
                return false;
        } else if (!cuoco.equals(other.cuoco))
            return false;
        return true;
    }
    
/////////////////////////////////////////////////////////////////////////////////


    public void addIngrediente(Ingrediente ingrediente) {
        ingrediente.setRicetta(this);
        this.ingredienti.add(ingrediente);
    }

    public void removeIngrediente(Ingrediente ingrediente) {
        this.ingredienti.remove(ingrediente);
    }

    public Immagine getFirstImmagine(){
        return this.immagini.get(0);
    } 

    public List<Immagine> getImmaginiDopoFirst(){
        try {
            return this.immagini.subList(1, this.immagini.size());
        } catch (Exception e) {
            return null;
        }
    }

    public void addImmagine(Immagine immagine) {
        this.immagini.add(immagine);
    }
    
}
