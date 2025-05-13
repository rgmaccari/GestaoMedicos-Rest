package org.unipar.gestaomedicosrest.dto;

public class ListagemMedicoDTO {
    //SELECT nome, email, crm, especialidade FROM medico ORDER BY nome asc;
    private Integer id;
    private String nome;
    private String email;
    private String crm;
    private String especialidade;

    public ListagemMedicoDTO(){}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
