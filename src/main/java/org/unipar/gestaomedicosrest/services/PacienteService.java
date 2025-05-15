package org.unipar.gestaomedicosrest.services;

import jakarta.ws.rs.NotFoundException;
import org.unipar.gestaomedicosrest.domain.Paciente;
import org.unipar.gestaomedicosrest.dto.CadastroPacienteDTO;
import org.unipar.gestaomedicosrest.dto.ListagemPacienteDTO;
import org.unipar.gestaomedicosrest.exceptions.BusinessException;
import org.unipar.gestaomedicosrest.repository.PacienteRepository;


import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class PacienteService {

    private PacienteRepository pacienteRepository;

    public PacienteService() {
        pacienteRepository = new PacienteRepository();
    }


    public Paciente insert(CadastroPacienteDTO paciente) throws BusinessException, SQLException, NamingException {
        if (paciente.getNome() == null || paciente.getNome().isEmpty() || paciente.getNome().length() > 100) {
            throw new BusinessException("O nome do paciente é obrigatório e deve ter no máximo 100 caracteres.");
        }

        if (paciente.getEmail() == null || paciente.getEmail().isEmpty() || paciente.getEmail().length() > 100) {
            throw new BusinessException("O email do paciente é obrigatório e deve ter no máximo 100 caracteres.");
        }

        if (paciente.getTelefone() == null || paciente.getTelefone() <= 0) {
            throw new BusinessException("O telefone do paciente é obrigatório.");
        }

        if (paciente.getCpf() == null || paciente.getCpf().isEmpty() || paciente.getCpf().length() > 11) {
            throw new BusinessException("O CPF do paciente é obrigatório e deve ter no máximo 11 caracteres.");
        }

        if (paciente.getUf() == null || paciente.getUf().isEmpty() || paciente.getUf().length() > 2) {
            throw new BusinessException("A UF do paciente é obrigatória e deve conter apenas a sigla do Estado.");
        }

        if (paciente.getLogradouro() == null || paciente.getLogradouro().isEmpty() || paciente.getLogradouro().length() > 50) {
            throw new BusinessException("O logradouro é obrigatório e deve ter no máximo 50 caracteres.");
        }

        if (paciente.getBairro() == null || paciente.getBairro().isEmpty() || paciente.getBairro().length() > 30) {
            throw new BusinessException("O bairro é obrigatório e deve ter no máximo 30 caracteres.");
        }

        if (paciente.getCidade() == null || paciente.getCidade().isEmpty() || paciente.getCidade().length() > 30) {
            throw new BusinessException("A cidade é obrigatório e deve ter no máximo 30 caracteres.");
        }

        if (paciente.getCep() == null) {
            throw new BusinessException("O cep é obrigatório e deve ter no máximo 8 caracteres.");
        }



        return pacienteRepository.insert(paciente);
    }


    public Paciente update(Paciente paciente) throws BusinessException, SQLException, NamingException {
        if (paciente.getId() == null) {
            throw new BusinessException("O id do paciente é obrigatório para edição");
        }

        if (paciente.getNome() == null || paciente.getNome().isEmpty() || paciente.getNome().length() > 100) {
            throw new BusinessException("O nome do paciente é obrigatório e deve possuir até 100 caracteres.");
        }

        if (paciente.getTelefone() == null || paciente.getTelefone() <= 0) {
            throw new BusinessException("O telefone deve ser informado.");
        }

        if (paciente.getLogradouro() == null || paciente.getLogradouro().isEmpty()) {
            throw new BusinessException("O logradouro deve ser informado.");
        }

        pacienteRepository.update(paciente);
        return paciente;
    }


    public Paciente findById(Integer id) throws BusinessException, SQLException, NamingException {

        if (id == null || id == 0) {
            throw new BusinessException("O id do paciente é obrigatório para busca");
        }

        Paciente paciente = pacienteRepository.findById(id);

        if(paciente == null) {
            throw new NotFoundException("Médico não encontrado");
        }
        return paciente;
    }


    public List<ListagemPacienteDTO> findAll() throws NotFoundException, SQLException, NamingException {
        List<ListagemPacienteDTO> listaPacientes = pacienteRepository.findAll();

        if(listaPacientes == null) {
            throw new NotFoundException("Lista vazia. Nenhum paciente encontrado");
        }

        return listaPacientes;
    }


    public void delete(Integer id) throws BusinessException, SQLException, NamingException {

        if (id == null) {
            throw new BusinessException("Id do paciente é obrigatório para exclusão");
        }

        pacienteRepository.delete(id);
    }

}
