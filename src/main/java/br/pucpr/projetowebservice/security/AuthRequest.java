package br.pucpr.projetowebservice.security;

public class AuthRequest {

    private String email;
    private String password;
    private String tipologin;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipologin() {
        return tipologin;
    }

    public void setTipologin(String tipologin) {
        this.tipologin = tipologin;
    }
}