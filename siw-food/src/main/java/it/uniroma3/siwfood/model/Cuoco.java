package it.uniroma3.siwfood.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToOne;

@Entity
public class Cuoco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @DateTimeFormat (pattern = "dd-MM-yyyy")
    private LocalDate dataNascita;
    @ElementCollection
    private List<Immagine> immagini;
    
    @OneToMany(mappedBy = "cuoco", cascade = CascadeType.ALL) //se fai un'operazione su cuoco, a cascata le farà anche su ricetta
    private List<Ricetta> ricette;
    
    @OneToOne(mappedBy = "cuoco")
    private User user;
    
    
    public Cuoco(){
        this.ricette = new ArrayList<>();
        this.immagini = new ArrayList<>();
    }
    
    public Cuoco(Long id, String nome, String cognome, LocalDate dataNascita, List<Immagine> immagini, List<Ricetta> ricette) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.immagini = immagini;
        this.ricette = ricette;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
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
    public List<Immagine> getImmagini() {
        return immagini;
    }
    public void setImmagini(List<Immagine> immagini) {
        this.immagini = immagini;
    }
    public List<Ricetta> getRicette() {
        return ricette;
    }
    public void setRicette(List<Ricetta> ricette) {
        this.ricette = ricette;
    }

    
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
        result = prime * result + ((dataNascita == null) ? 0 : dataNascita.hashCode());
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
        Cuoco other = (Cuoco) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (cognome == null) {
            if (other.cognome != null)
                return false;
        } else if (!cognome.equals(other.cognome))
            return false;
        if (dataNascita == null) {
            if (other.dataNascita != null)
                return false;
        } else if (!dataNascita.equals(other.dataNascita))
            return false;
        return true;
    }

    /////////////////////////////////////////////////////////////////////////////////
    public void addRicetta(Ricetta ricetta) {
        this.ricette.add(ricetta);
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
