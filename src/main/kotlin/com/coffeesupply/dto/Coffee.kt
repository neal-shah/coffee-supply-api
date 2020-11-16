package com.coffeesupply.dto

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity

@Entity
data class Coffee(
        val sku: Int = 0,
        val productName: String = "",
        val description: String = "",
        val originCountry: String = "",
        val price: Double = 0.00
) : PanacheEntity()