package org.unipar.unilivrariarest;

import jakarta.ws.rs.core.Response;
import org.unipar.unilivrariarest.domain.Medico;
import org.unipar.unilivrariarest.dto.CadastroMedicoDTO;
import org.unipar.unilivrariarest.dto.ExceptionResponseDTO;
import org.unipar.unilivrariarest.dto.ListagemMedicoDTO;
import org.unipar.unilivrariarest.exceptions.BusinessException;
import org.unipar.unilivrariarest.services.MedicoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/medicos")
public class MedicoController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(CadastroMedicoDTO medicoInsertRequestDTO) {
        try {
            MedicoService medicoService = new MedicoService();
            Medico medico = medicoService.insert(medicoInsertRequestDTO);

            return Response.status(Response.Status.CREATED).entity(medico).build();

        } catch (BusinessException e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponseDTO).build();

        } catch (Exception e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO("Ocorreu um erro interno.");
            return Response.serverError().entity(exceptionResponseDTO).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id) throws BusinessException {
        try{
            MedicoService medicoService = new MedicoService();
            //return Response.status(Response.Status.FOUND).entity(medicoService.buscarPorId(id)).build();
            return Response.status(Response.Status.OK).entity(medicoService.findById(id)).build();
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
            List<ListagemMedicoDTO> listaMedicos = new ArrayList<ListagemMedicoDTO>();
            MedicoService medicoService = new MedicoService();
            listaMedicos = medicoService.findAll();

            return Response.status(Response.Status.OK).entity(listaMedicos).build();

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
    public Response update(@PathParam("id") Integer id, CadastroMedicoDTO medicoInsertRequestDTO) throws BusinessException {
        try {
            MedicoService medicoService = new MedicoService();
            Medico medico = new Medico(id, medicoInsertRequestDTO);
            return Response.status(Response.Status.OK).entity(medicoService.update(medico)).build();
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
            MedicoService medicoService = new MedicoService();
            medicoService.delete(id);

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
