package mindswap.academy.item.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.item.dto.ItemCategoryCreateDto;
import mindswap.academy.item.dto.ItemCategoryDto;
import mindswap.academy.item.model.ItemCategory;
import mindswap.academy.item.service.ItemCategoryService;

import java.util.List;

@Path("/itemCategory")

public class ItemCategoryResource {

    @Inject
    ItemCategoryService itemCategoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemCategoryDto> getAll(){
        return itemCategoryService.getAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ItemCategoryDto post(ItemCategoryCreateDto itemCategoryCreateDto){
        return itemCategoryService.create(itemCategoryCreateDto);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/itemCategory/{id}")
    public void delete(@PathParam("id") Long id){
        itemCategoryService.deleteById(id);
    }

}
