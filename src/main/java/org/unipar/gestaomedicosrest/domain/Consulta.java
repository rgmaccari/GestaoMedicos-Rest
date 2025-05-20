package org.unipar.gestaomedicosrest.domain;

import org.unipar.gestaomedicosrest.dto.CadastroConsultaDTO;

import java.time.LocalDateTime;

public class Consulta {
    private Integer id;
    private Integer medico_id;
    private Integer paciente_id;
    private LocalDateTime data_hora;

    public Consulta(){}

    public Consulta(CadastroConsultaDTO dto){
        this.medico_id = dto.getMedico_id();
        this.paciente_id = dto.getPaciente_id();
        this.data_hora = dto.getData_hora();
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public Integer getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(Integer paciente_id) {
        this.paciente_id = paciente_id;
    }

    public Integer getMedico_id() {
        return medico_id;
    }

    public void setMedico_id(Integer medico_id) {
        this.medico_id = medico_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
