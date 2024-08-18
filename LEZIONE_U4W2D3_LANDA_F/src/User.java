import exceptions.StringNotValidException;

public class User {
    int eta;
    private String nome;
    private String cognome;

    public User(String nome, String cognome, int eta) {
        this.cognome = cognome;
        this.nome = nome;
        this.eta = eta;
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

    @Override
    public String toString() {
        return "User{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", età='" + eta + '\'' +
                '}';
    }
}



