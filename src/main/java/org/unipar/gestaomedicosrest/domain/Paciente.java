package org.unipar.gestaomedicosrest.domain;

import org.unipar.gestaomedicosrest.dto.CadastroPacienteDTO;

public class Paciente {
    private Integer id;
    private String nome;

    public Paciente(){}

    public Paciente(Integer id, CadastroPacienteDTO pacienteInsertRequestDTO) {
        this.id = id;
        this.nome = pacienteInsertRequestDTO.getNome();
    }

    public Paciente(String nome){
        this.nome = nome;
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
