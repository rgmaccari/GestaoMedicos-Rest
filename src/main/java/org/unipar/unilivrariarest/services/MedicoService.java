package org.unipar.unilivrariarest.services;

import jakarta.ws.rs.NotFoundException;
import org.unipar.unilivrariarest.domain.Medico;
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


    public Medico insert(Medico medico) throws BusinessException, SQLException, NamingException {
        if (medico.getNome() == null || medico.getNome().isEmpty()) {
            throw new BusinessException("O nome do medico é obrigatório");
        }

        if (medico.getNome().length() > 100) {
            throw new BusinessException("O nome do medico deve ter no máximo 100 caracteres");
        }

        medicoRepository.insert(medico);
        return medico;
    }


    public Medico update(Medico medico) throws BusinessException, SQLException, NamingException {
        if (medico.getId() == null) {
            throw new BusinessException("O id do medico é obrigatório para edição");
        }

        if (medico.getNome() == null || medico.getNome().isEmpty()) {
            throw new BusinessException("O nome do medico é obrigatório");
        }

        if (medico.getNome().length() > 100) {
            throw new BusinessException("O nome do medico deve ter no máximo 100 caracteres");
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


    public List<Medico> findAll() throws NotFoundException, SQLException, NamingException {
        List<Medico> listaMedicos = new ArrayList<>();
        listaMedicos = medicoRepository.findAll();

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
