package org.unipar.gestaomedicosrest.dto;

public class UpdateMedicoDto {
    private String nome;
    private Long telefone;
    private String logradouro;

    public UpdateMedicoDto(){}

    public UpdateMedicoDto(String nome, Long telefone, String logradouro) {
        this.nome = nome;
        this.telefone = telefone;
        this.logradouro = logradouro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
}
