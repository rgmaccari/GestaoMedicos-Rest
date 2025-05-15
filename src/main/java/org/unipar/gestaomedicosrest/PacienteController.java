package org.unipar.gestaomedicosrest;

import jakarta.ws.rs.core.Response;
import org.unipar.gestaomedicosrest.domain.Paciente;
import org.unipar.gestaomedicosrest.dto.CadastroPacienteDTO;
import org.unipar.gestaomedicosrest.dto.ExceptionResponseDTO;
import org.unipar.gestaomedicosrest.dto.ListagemPacienteDTO;
import org.unipar.gestaomedicosrest.dto.UpdatePacienteDTO;
import org.unipar.gestaomedicosrest.exceptions.BusinessException;
import org.unipar.gestaomedicosrest.services.PacienteService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


import java.util.ArrayList;
import java.util.List;

@Path("/pacientes")
public class PacienteController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(CadastroPacienteDTO pacienteInsertRequestDTO) {
        try {
            PacienteService pacienteService = new PacienteService();
            Paciente paciente = pacienteService.insert(pacienteInsertRequestDTO);

            return Response.status(Response.Status.CREATED).entity(paciente).build();

        } catch (BusinessException e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponseDTO).build();

        } catch (Exception e) {
            e.printStackTrace();
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO("Ocorreu um erro interno.");
            return Response.serverError().entity(exceptionResponseDTO).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id) throws BusinessException {
        try{
            PacienteService pacienteService = new PacienteService();
            //return Response.status(Response.Status.FOUND).entity(pacienteService.buscarPorId(id)).build();
            return Response.status(Response.Status.OK).entity(pacienteService.findById(id)).build();
        }catch (BusinessException e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponseDTO).build();

        } catch(NotFoundException e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(exceptionResponseDTO).build();
        } catch(Exception e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO("Ocorreu um erro interno.");
            return Response.serverError().entity(exceptionResponseDTO).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws BusinessException {
        try{
            List<ListagemPacienteDTO> listaPacientes = new ArrayList<ListagemPacienteDTO>();
            PacienteService pacienteService = new PacienteService();
            listaPacientes = pacienteService.findAll();

            return Response.status(Response.Status.OK).entity(listaPacientes).build();

        }catch (NotFoundException e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(exceptionResponseDTO).build();

        } catch (Exception e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.serverError().entity(exceptionResponseDTO).build();
        }

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") Integer id, UpdatePacienteDTO dto) throws BusinessException {
        try {
            PacienteService pacienteService = new PacienteService();
            Paciente paciente = new Paciente(id, dto);
            return Response.status(Response.Status.OK).entity(pacienteService.update(paciente)).build();
        }catch (BusinessException e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponseDTO).build();
        }catch (NotFoundException e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(exceptionResponseDTO).build();
        }catch(Exception e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.serverError().entity(exceptionResponseDTO).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) throws BusinessException {
        try{
            PacienteService pacienteService = new PacienteService();
            pacienteService.delete(id);

            return Response.status(Response.Status.OK).build();

        }catch(BusinessException e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponseDTO).build();
        }catch (NotFoundException e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(exceptionResponseDTO).build();
        }catch(Exception e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.serverError().entity(exceptionResponseDTO).build();
        }
    }


}
