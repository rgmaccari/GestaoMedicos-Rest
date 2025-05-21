package org.unipar.gestaomedicosrest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.unipar.gestaomedicosrest.domain.Consulta;
import org.unipar.gestaomedicosrest.dto.CadastroConsultaDTO;
import org.unipar.gestaomedicosrest.dto.ExceptionResponseDTO;
import org.unipar.gestaomedicosrest.exceptions.BusinessException;
import org.unipar.gestaomedicosrest.services.ConsultaService;

@Path("/consultas")
public class ConsultaController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(CadastroConsultaDTO dto){
        try{
            ConsultaService service = new ConsultaService();
            Consulta consulta = service.insert(dto);
            return Response.status(Response.Status.CREATED).entity(consulta).build();

        }catch(BusinessException e){
            ExceptionResponseDTO response = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        } catch (Exception e) {
            ExceptionResponseDTO response = new ExceptionResponseDTO(e.getMessage());
            return Response.serverError().entity(response).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id){
        try{
            ConsultaService service = new ConsultaService();
            service.delete(id);
            return Response.status(Response.Status.OK).build();
        }catch (BusinessException e){
            ExceptionResponseDTO response = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }catch (NotFoundException e){
            ExceptionResponseDTO response = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }catch(Exception e){
            ExceptionResponseDTO response = new ExceptionResponseDTO(e.getMessage());
            return Response.serverError().entity(response).build();
        }

    }


}
