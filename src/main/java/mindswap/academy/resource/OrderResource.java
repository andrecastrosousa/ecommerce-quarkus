package mindswap.academy.resource;


import com.oracle.svm.core.annotate.Delete;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.dto.OrderCreateDto;
import mindswap.academy.dto.OrderDto;
import mindswap.academy.service.OrderService;

import java.util.List;

@Path("/orders")
public class OrderResource {

    @Inject
    OrderService orderService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDto> getAll(){
        return orderService.getAll();
    }

    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDto getById(@PathParam("orderId") Long orderId){
        return orderService.getById(orderId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    //Nao posso esquecer de meter  @Valid
    public OrderDto post(OrderCreateDto orderCreateDto){
        return orderService.create(orderCreateDto);
    }

    @Delete
    @Path("{orderId}")
    @Transactional
    public void delete( @PathParam("orderId") Long orderId){
        orderService.delete(orderId);
    }

    @PUT
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public OrderDto put(@PathParam("orderId") Long orderId) {
        return orderService.update(orderId);
    }
}
