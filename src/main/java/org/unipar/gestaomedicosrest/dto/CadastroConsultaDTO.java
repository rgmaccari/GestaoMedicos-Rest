package org.unipar.gestaomedicosrest.dto;

import java.time.LocalDateTime;

public class CadastroConsultaDTO {
    private Integer medico_id;
    private Integer paciente_id;
    private LocalDateTime data_hora;

    public CadastroConsultaDTO() {
    }

    public CadastroConsultaDTO(Integer medico_id, Integer paciente_id, LocalDateTime data_hora) {
        this.medico_id = medico_id;
        this.paciente_id = paciente_id;
        this.data_hora = data_hora;
    }

    public Integer getMedico_id() {
        return medico_id;
    }

    public void setMedico_id(Integer medico_id) {
        this.medico_id = medico_id;
    }

    public Integer getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(Integer paciente_id) {
        this.paciente_id = paciente_id;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }
}