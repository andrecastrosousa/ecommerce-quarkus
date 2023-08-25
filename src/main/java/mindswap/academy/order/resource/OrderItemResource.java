package mindswap.academy.order.resource;


import com.oracle.svm.core.annotate.Delete;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.item.dto.ItemCreateDto;
import mindswap.academy.order.dto.OrderAddItemDto;
import mindswap.academy.order.dto.OrderItemDto;
import mindswap.academy.order.dto.OrderItemUpdateDto;
import mindswap.academy.order.service.OrderItemServiceImp;

import java.util.List;

@Path("/orders")
public class OrderItemResource {

    @Inject
    OrderItemServiceImp orderItemServiceImp;

    @GET
    @Path("/{orderId}/items")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderItemDto> getAll(@PathParam("orderId") Long orderId){
        return orderItemServiceImp.getAll(orderId);
    }

    @GET
    @Path("/{orderId}/items/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderItemDto getById(@PathParam("orderId") Long orderId, @PathParam("itemId") Long itemId) {
        return orderItemServiceImp.getById(orderId, itemId);
    }


    @POST
    @Path("/{orderId}/items")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public OrderItemDto create(@PathParam("orderId") Long orderId, OrderAddItemDto orderAddItemDto){
        return orderItemServiceImp.create(orderId, orderAddItemDto);
    }

    @Delete
    @Path("/{orderId}/items/{itemId}")
    @Transactional
    public void delete(@PathParam("orderId") Long orderId, @PathParam("itemId") Long itemId){
        orderItemServiceImp.delete(orderId, itemId);
    }

    @PUT
    @Path("/{orderId}/items")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public OrderItemDto update(@PathParam("orderId") Long orderId, OrderItemUpdateDto orderItemUpdateDto){
        return orderItemServiceImp.update(orderId,orderItemUpdateDto);
    }

}
