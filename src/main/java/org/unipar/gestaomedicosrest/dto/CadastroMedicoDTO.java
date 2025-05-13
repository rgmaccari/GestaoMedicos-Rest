package org.unipar.gestaomedicosrest.dto;

public class CadastroMedicoDTO {
    private Integer id;
    private String nome;
    private String email;
    private Long telefone;
    private String crm;
    private String especialidade;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String complemento;
    private String cidade;

    public CadastroMedicoDTO(){}

    public CadastroMedicoDTO(Integer id,String nome, String email, Long telefone, String crm, String especialidade, String logradouro, Integer numero, String bairro, String complemento, String cidade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.crm = crm;
        this.especialidade = especialidade;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
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

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}

/**
 * private Integer id;
 *     private String nome;
 *     private String email;
 *     private long telefone;
 *     private String crm;
 *     private String especialidade;
 *     private String logradouro;
 *     private Integer numero;
 *     private String bairro;
 *     private String complemento;
 *     private String cidade;
 * */
