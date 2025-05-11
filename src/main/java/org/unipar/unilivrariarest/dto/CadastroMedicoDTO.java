package org.unipar.unilivrariarest.dto;

public class CadastroMedicoDTO {

    private String nome;

    public CadastroMedicoDTO(){}

    public CadastroMedicoDTO(Integer id, String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
