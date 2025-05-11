package org.unipar.unilivrariarest.services;

import jakarta.ws.rs.NotFoundException;
import org.unipar.unilivrariarest.domain.Paciente;
import org.unipar.unilivrariarest.exceptions.BusinessException;
import org.unipar.unilivrariarest.repository.PacienteRepository;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteService {
    PacienteRepository pacienteRepository;

    public PacienteService(){
        pacienteRepository = new PacienteRepository();
    }

    public Paciente insert(Paciente paciente) throws BusinessException, SQLException, NamingException{
        if(paciente.getNome() == null || paciente.getNome().isEmpty()){
            throw new BusinessException("O nome do paciente é obrigatório.");
        }

        if(paciente.getNome().length() > 100){
            throw new BusinessException("O nome do paciente deve ter no máximo 100 caracteres.");
        }

        pacienteRepository.insert(paciente);
        return paciente;
    }


    public Paciente update(Paciente paciente) throws BusinessException, SQLException, NamingException{
        if(paciente.getId() == null){
            throw new BusinessException("O id do paciente é obrigatório para edição.");
        }

        if(paciente.getNome() == null || paciente.getNome().isEmpty()){
            throw new BusinessException("O id do paciente é obrigatório para edição.");
        }

        if(paciente.getNome().length() > 100){
            throw new BusinessException("O nome do paciente deve ter no máximo 100 caracteres");
        }

        pacienteRepository.update(paciente);
        return paciente;
    }


    public Paciente findById(Integer id) throws BusinessException, SQLException, NamingException{
        if(id == null || id == 0){
            throw new BusinessException("O id do paciente é obrigatório para a busca.");
        }

        Paciente paciente = pacienteRepository.findById(id);

        if(paciente == null){
            throw new NotFoundException("Paciente não encontrado");
        }

        return paciente;
    }

    public List<Paciente> findAll() throws BusinessException, SQLException, NamingException{
        List<Paciente> listaPacientes = new ArrayList<>();
        listaPacientes = pacienteRepository.findAll();

        if(listaPacientes == null){
            throw new NotFoundException("Lista vazia. Nenhum paciente encontrado");
        }

        return listaPacientes;
    }

    public void delete(Integer id) throws BusinessException, SQLException, NamingException{
        if(id == null){
            throw new BusinessException("O id do paciente é obrigatório para a exclusão");
        }
    }
}
