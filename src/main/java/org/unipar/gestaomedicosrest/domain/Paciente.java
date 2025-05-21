package org.unipar.gestaomedicosrest.domain;

import org.unipar.gestaomedicosrest.dto.CadastroPacienteDTO;
import org.unipar.gestaomedicosrest.dto.UpdatePacienteDTO;

public class Paciente {
    private Integer id;
    private String nome;
    private String email;
    private Long telefone;
    private String cpf;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private Long cep;
    private boolean ativo;

    public Paciente(){}

    public Paciente(CadastroPacienteDTO dto) {
        this.id = id;
        this.nome = dto.getNome();
        this.email = dto.getEmail();
        this.telefone = dto.getTelefone();
        this.cpf = dto.getCpf();
        this.logradouro = dto.getLogradouro();
        this.numero = dto.getNumero();
        this.complemento = dto.getComplemento();
        this.bairro = dto.getBairro();
        this.cidade = dto.getCidade();
        this.uf = dto.getUf();
        this.cep = dto.getCep();
        this.ativo = true;
    }

    public Paciente(Integer id, UpdatePacienteDTO dto){
        this.id = id;
        this.nome = dto.getNome();
        this.telefone = dto.getTelefone();
        this.logradouro = dto.getLogradouro();
    }

    public Paciente(Integer id, String nome, String email, Long telefone, String cpf, String logradouro, Integer numero, String complemento, String bairro, String cidade, String uf, Long cep, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.ativo = true;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Long getCep() {
        return cep;
    }

    public void setCep(Long cep) {
        this.cep = cep;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
