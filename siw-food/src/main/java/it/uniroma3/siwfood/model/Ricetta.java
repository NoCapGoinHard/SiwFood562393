package it.uniroma3.siwfood.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import jakarta.validation.constraints.NotBlank;


@Entity
public class Ricetta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;


    private String descrizione;

    @ElementCollection
    @Column(nullable = true)
    private List<Immagine> immagini = new ArrayList<>();

    @OneToMany(mappedBy = "ricetta", cascade = CascadeType.ALL)
    private List<Ingrediente> ingredienti = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cuoco_id")
    private Cuoco cuoco;


    public Ricetta(){}

    public Ricetta(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public Ricetta(String nome, String descr, List<Immagine> immagini, List<Ingrediente> ingr, Cuoco cuoco) {
        this.nome = nome;
        this.descrizione = descr;
        this.immagini = immagini;
        this.ingredienti = ingr;
        this.cuoco = cuoco;
    }

    public boolean hasImages() {
        return !this.immagini.isEmpty();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String titolo) {
        this.nome = titolo;
    }

    public List<Immagine> getImmagini() {
        return immagini;
    }

    public void setImmagini(List<Immagine> immagini) {
        this.immagini = immagini;
    }

    public List<Ingrediente> getIngredienti() {
        return this.ingredienti;
    }

    public void setIngredienti(List<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public Cuoco getCuoco() {
        return cuoco;
    }

    public void setCuoco(Cuoco autore) {
        this.cuoco = autore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }


    
    @Override
    public int hashCode() {
        return Objects.hash(descrizione,ingredienti,cuoco);
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
        if (descrizione == null) {
            if (other.descrizione != null)
                return false;
        } else if (!descrizione.equals(other.descrizione))
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
 


    public void addIngrediente(Ingrediente ingrediente) {
        this.ingredienti.add(ingrediente);
    }
    public void removeIngrediente(Ingrediente ingrediente) {
        this.ingredienti.remove(ingrediente);
    }
}

