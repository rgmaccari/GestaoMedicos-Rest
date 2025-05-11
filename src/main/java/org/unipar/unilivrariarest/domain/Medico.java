package org.unipar.unilivrariarest.domain;

import org.unipar.unilivrariarest.dto.CadastroMedicoDTO;

public class Medico {

    private Integer id;
    private String nome;

    public Medico() { }

    public Medico(Integer id, CadastroMedicoDTO medicoInsertRequestDTO) {
        this.id = id;
        this.nome = medicoInsertRequestDTO.getNome();
    }

    public Medico(CadastroMedicoDTO medicoDTO) {
        this.nome = medicoDTO.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
