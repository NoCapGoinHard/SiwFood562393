package it.uniroma3.siwfood.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import it.uniroma3.siwfood.model.auth.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

@Entity
public class Cuoco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;

    @Past
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascita;
    
    @ElementCollection
    @Column(nullable = true)
    private List<Immagine> immagini;
    
    @OneToMany(mappedBy = "cuoco", cascade = CascadeType.ALL)
    private List<Ricetta> ricette = new ArrayList<>();
    


    public Cuoco(){}

    public Cuoco(User user) {
        this.nome = user.getNome();
        this.cognome = user.getCognome();
        this.dataNascita = user.getDataNascita();
    }

    public Cuoco(String nome, String cognome, List<Immagine> immagini, LocalDate dataNascita, List<Ricetta> ricette) {
        this.nome = nome;
        this.cognome = cognome;
        this.immagini = immagini;
        this.dataNascita = dataNascita;
        this.ricette = ricette;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void setRicette(List<Ricetta> ricette){
        this.ricette = ricette;
    }

    public List<Ricetta> getRicette(){
        return this.ricette;
    }

    public List<Immagine> getImmagini() {
        return immagini;
    }

    public void setImmagini(List<Immagine> immagine) {
        this.immagini = immagine;
    }



    public void addRicetta(Ricetta ricetta) {
        this.ricette.add(ricetta);
    }


    

    @Override
    public int hashCode() {
        return Objects.hash(nome,cognome,dataNascita);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cuoco other = (Cuoco) obj;
        return Objects.equals(nome, other.nome) &&
               Objects.equals(cognome, other.cognome) &&
               Objects.equals(dataNascita, other.dataNascita);
    }

    @Override
    public String toString(){
        return this.nome + " " + this.cognome;
    }

    
    
}