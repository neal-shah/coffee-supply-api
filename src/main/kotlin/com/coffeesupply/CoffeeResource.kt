package com.coffeesupply

import com.coffeesupply.dto.Coffee
import com.coffeesupply.repository.CoffeeRepository
import org.jboss.resteasy.annotations.jaxrs.QueryParam
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.PathParam

@Path("/coffee")
class CoffeeResource(val repository: CoffeeRepository) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findAll(): Response = Response.ok(repository.listAll()).build()

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    fun findByOriginCountry(
            @QueryParam originCountry: String
    ): Response = Response.ok(repository.findByOriginCountry(originCountry)).build()

    // Create a new Coffee entry
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun create(coffee: Coffee): Response {
        repository.persist(coffee)
        return Response.ok(coffee).status(201).build()
    }

    // Delete a Coffee entry
    @DELETE
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    fun delete(
            @PathParam("id") id: Long
    ): Response {
        repository.deleteById(id)
        return Response.ok("Item id $id deleted.").build()
    }

    // Update a Coffee entry
    @PUT
    @Path("/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun update(coffee: Coffee, @PathParam("id") id: Long): Response {
        repository.update("" +
                "sku = ${coffee.sku}, " +
                "productName = '${coffee.productName}', " +
                "price = ${coffee.price}, " +
                "description = '${coffee.description}', " +
                "originCountry = '${coffee.originCountry}' " +
                "where id = $id")
        return Response.ok(repository.findById(id)).build()
    }
}