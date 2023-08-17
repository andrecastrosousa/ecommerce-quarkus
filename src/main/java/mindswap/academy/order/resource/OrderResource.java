package mindswap.academy.order.resource;


import com.oracle.svm.core.annotate.Delete;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.order.dto.OrderCreateDto;
import mindswap.academy.order.dto.OrderDto;
import mindswap.academy.order.dto.OrderUpdatedDto;
import mindswap.academy.order.service.OrderServiceImp;

import java.util.List;

@Path("/orders")
public class OrderResource {

    @Inject
    OrderServiceImp orderServiceImp;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDto> getAll(){
        return orderServiceImp.getAll();
    }

    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDto getById(@PathParam("orderId") Long orderId){
        return orderServiceImp.getById(orderId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    //Nao posso esquecer de meter  @Valid
    public OrderDto post(@Valid OrderCreateDto orderCreateDto){
        return orderServiceImp.create(orderCreateDto);
    }

    @Delete
    @Path("{orderId}")
    @Transactional
    public void delete( @PathParam("orderId") Long orderId){
        orderServiceImp.delete(orderId);
    }

    @PUT
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public OrderDto put(@PathParam("orderId") Long orderId, OrderUpdatedDto orderUpdatedDto) {
        return orderServiceImp.update(orderId, orderUpdatedDto);
    }
}
