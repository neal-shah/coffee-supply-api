package com.coffeesupply.repository

import com.coffeesupply.dto.Coffee
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CoffeeRepository : PanacheRepository<Coffee> {

    fun findByOriginCountry(originCountry: String) = list("originCountry = '$originCountry'")
}