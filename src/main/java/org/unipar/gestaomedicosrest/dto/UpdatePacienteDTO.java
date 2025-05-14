package org.unipar.gestaomedicosrest.dto;

public class UpdatePacienteDTO {
    private String nome;
    private String telefone;
    private String email;
    private String logradouro;

    public UpdatePacienteDTO(){
    }

    public UpdatePacienteDTO(String nome, String telefone, String email, String logradouro) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.logradouro = logradouro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
}
