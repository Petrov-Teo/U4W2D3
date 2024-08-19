package io.Petrov_Todor;


import io.Petrov_Todor.exceptions.StringNotValidException;

public class User {
    int eta;
    private String nome;
    private String cognome;
    private String citta;

    public User(String nome, String cognome, int eta, String citta) {
        this.cognome = cognome;
        this.nome = nome;
        this.eta = eta;
        this.citta = citta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws StringNotValidException {
        if (nome.length() < 3) throw new StringNotValidException(nome);
        this.nome = nome;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) throws StringNotValidException {
        if (cognome.length() < 3) throw new StringNotValidException(cognome);
        this.cognome = cognome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    @Override
    public String toString() {
        return "User{" +
                "Età=" + eta +
                ", Nome='" + nome + '\'' +
                ", Cognome='" + cognome + '\'' +
                ", Città='" + citta + '\'' +
                '}';
    }
}



