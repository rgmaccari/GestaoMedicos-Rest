package org.unipar.gestaomedicosrest.dto;

public class ExceptionResponseDTO {
    private String mensagem;

    public ExceptionResponseDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
