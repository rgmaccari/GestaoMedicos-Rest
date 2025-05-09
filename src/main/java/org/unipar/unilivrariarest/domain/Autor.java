package org.unipar.unilivrariarest.domain;

import org.unipar.unilivrariarest.dto.CadastroAutorDTO;
import org.unipar.unilivrariarest.exceptions.BusinessException;

public class Autor {

    private Integer id;

    private String nome;

    public Autor() { }

    public Autor(Integer id, CadastroAutorDTO autorInsertRequestDTO) {
        this.id = id;
        this.nome = autorInsertRequestDTO.getNome();
    }

    public Autor(CadastroAutorDTO autorDTO) {
        this.nome = autorDTO.getNome();
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
