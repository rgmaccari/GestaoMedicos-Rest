package org.unipar.unilivrariarest.services;

import jakarta.ws.rs.NotFoundException;
import org.unipar.unilivrariarest.domain.Medico;
import org.unipar.unilivrariarest.dto.CadastroMedicoDTO;
import org.unipar.unilivrariarest.dto.ListagemMedicoDTO;
import org.unipar.unilivrariarest.exceptions.BusinessException;
import org.unipar.unilivrariarest.repository.MedicoRepository;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoService {

    private MedicoRepository medicoRepository;

    public MedicoService() {
        medicoRepository = new MedicoRepository();
    }


    public Medico insert(CadastroMedicoDTO medico) throws BusinessException, SQLException, NamingException {
        if (medico.getNome() == null || medico.getNome().isEmpty() || medico.getNome().length() > 100) {
            throw new BusinessException("O nome do medico é obrigatório e deve ter no máximo 100 caracteres.");
        }

        if (medico.getEmail() == null || medico.getEmail().isEmpty() || medico.getEmail().length() > 100) {
            throw new BusinessException("O email do medico é obrigatório e deve ter no máximo 100 caracteres.");
        }

        if (medico.getTelefone() == null || medico.getTelefone() <= 0) {
            throw new BusinessException("O telefone do medico é obrigatório.");
        }

        if (medico.getCrm() == null || medico.getCrm().isEmpty() || medico.getCrm().length() > 20) {
            throw new BusinessException("O CRM do medico é obrigatório e deve ter no máximo 20 caracteres.");
        }

        if (medico.getEspecialidade() == null || medico.getEspecialidade().isEmpty() || medico.getEspecialidade().length() > 20) {
            throw new BusinessException("A especialidade do medico é obrigatório e deve uma entre as opções: Ortopedia, Cardiologia, Ginecologia ou Dermatologia.");
        }

        if (medico.getLogradouro() == null || medico.getLogradouro().isEmpty() || medico.getLogradouro().length() > 50) {
            throw new BusinessException("O logradouro é obrigatório e deve ter no máximo 50 caracteres.");
        }

        if (medico.getBairro() == null || medico.getBairro().isEmpty() || medico.getBairro().length() > 30) {
            throw new BusinessException("O bairro é obrigatório e deve ter no máximo 30 caracteres.");
        }

        if (medico.getCidade() == null || medico.getCidade().isEmpty() || medico.getCidade().length() > 30) {
            throw new BusinessException("A cidade é obrigatório e deve ter no máximo 30 caracteres.");
        }

        return medicoRepository.insert(medico);
        //return medico;
    }


    public Medico update(Medico medico) throws BusinessException, SQLException, NamingException {
        if (medico.getId() == null) {
            throw new BusinessException("O id do medico é obrigatório para edição");
        }

        if (medico.getNome() == null || medico.getNome().isEmpty() || medico.getNome().length() > 100) {
            throw new BusinessException("O nome do medico é obrigatório e deve possuir até 100 caracteres.");
        }

        if (medico.getTelefone() == null || medico.getTelefone() <= 0) {
            throw new BusinessException("O telefone deve ser informado.");
        }

        medicoRepository.update(medico);
        return medico;
    }


    public Medico findById(Integer id) throws BusinessException, SQLException, NamingException {

        if (id == null || id == 0) {
            throw new BusinessException("O id do medico é obrigatório para busca");
        }

        Medico medico = medicoRepository.findById(id);

        if(medico == null) {
            throw new NotFoundException("Médico não encontrado");
        }
        return medico;
    }


    public List<ListagemMedicoDTO> findAll() throws NotFoundException, SQLException, NamingException {
        List<ListagemMedicoDTO> listaMedicos = medicoRepository.findAll();

        if(listaMedicos == null) {
            throw new NotFoundException("Lista vazia. Nenhum medico encontrado");
        }

        return listaMedicos;
    }


    public void delete(Integer id) throws BusinessException, SQLException, NamingException {

        if (id == null) {
            throw new BusinessException("Id do medico é obrigatório para exclusão");
        }

        medicoRepository.delete(id);
    }

}
