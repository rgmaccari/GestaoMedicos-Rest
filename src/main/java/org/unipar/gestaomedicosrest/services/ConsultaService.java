package org.unipar.gestaomedicosrest.services;

import org.unipar.gestaomedicosrest.domain.Consulta;
import org.unipar.gestaomedicosrest.domain.Medico;
import org.unipar.gestaomedicosrest.domain.Paciente;
import org.unipar.gestaomedicosrest.dto.CadastroConsultaDTO;
import org.unipar.gestaomedicosrest.exceptions.BusinessException;
import org.unipar.gestaomedicosrest.repository.ConsultaRepository;
import org.unipar.gestaomedicosrest.repository.MedicoRepository;
import org.unipar.gestaomedicosrest.repository.PacienteRepository;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class ConsultaService {
    private ConsultaRepository consultaRepository;
    private PacienteRepository pacienteRepository;
    private MedicoRepository medicoRepository;

    public ConsultaService(){
        this.consultaRepository = new ConsultaRepository();
    }

    public Consulta insert(CadastroConsultaDTO dto) throws SQLException, NamingException, IllegalArgumentException {

        // Horário de funcionamento (segunda a sábado, 07:00 às 19:00)
        LocalDateTime dataHora = dto.getData_hora();
        DayOfWeek diaSemana = dataHora.getDayOfWeek();
        int hora = dataHora.getHour();
        if (diaSemana == DayOfWeek.SUNDAY || hora < 7 || hora >= 19) {
            throw new IllegalArgumentException("Consultas só podem ser agendadas de segunda a sábado, das 07:00 às 19:00.");
        }

        //Antecedência mínima de 30 minutos
        LocalDateTime agora = LocalDateTime.now();
        if (dataHora.isBefore(agora.plusMinutes(30))) {
            throw new IllegalArgumentException("Consultas devem ser agendadas com antecedência mínima de 30 minutos.");
        }

        //Paciente ativo
        Paciente paciente = pacienteRepository.findById(dto.getPaciente_id());
        if (paciente == null || !paciente.isAtivo()) {
            throw new IllegalArgumentException("Paciente inativo ou não encontrado.");
        }

        //Médico ativo (se especificado)
        Integer medicoId = dto.getMedico_id();
        if (medicoId != null) {
            Medico medico = medicoRepository.findById(medicoId);
            if (medico == null || !medico.isAtivo()) {
                throw new IllegalArgumentException("Médico inativo ou não encontrado.");
            }
        }

        if (medicoId == null) {
            List<Medico> medicosDisponiveis = medicoRepository.findDisponibilidadeMedico(dataHora);
            if (medicosDisponiveis.isEmpty()) {
                throw new IllegalArgumentException("Nenhum médico disponível para o horário solicitado.");
            }
            medicoId = medicosDisponiveis.get(new Random().nextInt(medicosDisponiveis.size())).getId();
            dto.setMedico_id(medicoId);
        }



        //Apenas uma consulta por dia para o mesmo paciente
        boolean pacienteTemConsulta = consultaRepository.consultaNoDiaPorPaciente(dto.getPaciente_id(), dataHora.toLocalDate());
        if (pacienteTemConsulta) {
            throw new IllegalArgumentException("Paciente já possui uma consulta agendada neste dia.");
        }

        //Conflito de horário com médico (se especificado)
        if (medicoId != null) {
            boolean medicoOcupado = consultaRepository.medicoDisponivel(medicoId, dataHora);
            if (medicoOcupado) {
                throw new IllegalArgumentException("Médico já possui uma consulta agendada neste horário.");
            }
        }

        return consultaRepository.insert(dto);
    }

    public void delete(Integer id) throws BusinessException, SQLException, NamingException {
        if (id == null) {
            throw new BusinessException("Id da consulta é obrigatória para exclusão");
        }
        consultaRepository.delete(id);
    }
}
