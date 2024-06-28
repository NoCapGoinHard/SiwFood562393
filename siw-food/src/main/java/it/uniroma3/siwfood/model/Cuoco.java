package it.uniroma3.siwfood.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cuoco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @DateTimeFormat (pattern = "dd-MM-yyyy")
    private LocalDate dataNascita;
    private String pathFoto;
    @OneToMany(mappedBy = "cuoco", cascade = CascadeType.ALL) //se fai un'operazione su cuoco, a cascata le farà anche su ricetta
    private List<Ricetta> ricette;


    
    public Cuoco(){
        this.ricette = new ArrayList<>();
    }

    public Cuoco(Long id, String nome, String cognome, LocalDate dataNascita, String pathFoto, List<Ricetta> ricette) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.pathFoto = pathFoto;
        this.ricette = ricette;
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
    public String getPathFoto() {
        return pathFoto;
    }
    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
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
    
}
