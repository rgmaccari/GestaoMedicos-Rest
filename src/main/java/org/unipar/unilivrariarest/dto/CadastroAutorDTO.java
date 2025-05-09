package org.unipar.unilivrariarest.dto;

public class CadastroAutorDTO {

    private String nome;

    public CadastroAutorDTO(){}

    public CadastroAutorDTO(Integer id, String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
