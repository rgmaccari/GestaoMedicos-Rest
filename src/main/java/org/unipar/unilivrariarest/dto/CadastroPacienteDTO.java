package org.unipar.unilivrariarest.dto;

public class CadastroPacienteDTO {
    private String nome;

    public CadastroPacienteDTO(){
    }

    public CadastroPacienteDTO(Integer id, String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
