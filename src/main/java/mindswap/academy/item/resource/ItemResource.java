package mindswap.academy.item.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.item.dto.ItemCreateDto;
import mindswap.academy.item.dto.ItemDto;

import mindswap.academy.item.dto.ItemUpdateDto;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.service.ItemService;

import java.util.List;

@Path("/item")
public class ItemResource {

    @Inject
    ItemService itemService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemDto> getAll(){
        return itemService.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    public Item getByName(@PathParam("name") String name){
        return itemService.getByName(name);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ItemDto post(ItemCreateDto itemCreateDto){

        return itemService.create(itemCreateDto);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/{id}")
    public ItemDto update(@PathParam("id") Long id, ItemUpdateDto itemUpdateDto){

        return itemService.updateById(id, itemUpdateDto);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/{id}")

    public void delete(@PathParam("id") Long id){
        itemService.deleteById(id);
    }

}
