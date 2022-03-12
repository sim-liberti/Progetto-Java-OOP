package Model;

public class Utente {
    private String nome;
    private String cognome;
    private String login;
    private String password;
    protected String ruolo;

    public Utente(String n, String c, String l, String p){
        this.nome = n;
        this.cognome = c;
        this.login = l;
        this.password = p;
    }

    public void setRuolo(String r){ this.ruolo = r; };

    public String getNome()    { return this.nome;     };
    public String getCognome() { return this.cognome;  };
    public String getLogin()   { return this.login;    };
    public String getPassword(){ return this.password; };
    public String getRuolo()   { return this.ruolo;    };
}
