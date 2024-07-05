package it.uniroma3.siwfood.model.auth;


import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Credentials {
    
    public static final String UTENTE_CUOCO = "CUOCO";
    public static final String UTENTE_AMMINISTRATORE = "ADMIN";
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String username;
    
    @NotBlank
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String ruolo;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;
    
    
    public Credentials(){}

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRuolo() {
        return ruolo;
    }
    
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    

    
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        return true;
        if (obj == null)
        return false;
        if (getClass() != obj.getClass())
        return false;
        Credentials other = (Credentials) obj;
        if (username == null) {
            if (other.username != null)
            return false;
        } else if (!username.equals(other.username))
        return false;
        if (password == null) {
            if (other.password != null)
            return false;
        } else if (!password.equals(other.password))
            return false;
            return true;
    }
    
    
    
    public boolean isAdmin(){
        return this.ruolo.equals(UTENTE_AMMINISTRATORE);
    }

    
}
