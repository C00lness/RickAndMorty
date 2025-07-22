package com.example.data.mappers

import com.example.data.entities.ApiDetails
import com.example.data.entities.db.DbDetails
import com.example.domain.entities.Details


fun ApiDetails.toDetails(): Details = Details(
    name = name,
    species = species,
    status = status,
    gender = gender,
    image = image,
    type = type,
    location = location?.name.toString()
)

fun DbDetails.toDetails(): Details = Details(
    name = name,
    species = species,
    status = status,
    gender = gender,
    image = image,
    type = type,
    location = location
)
fun Details.toDbDetails(url:String): DbDetails = DbDetails(
    name = name,
    species = species,
    status = status,
    gender = gender,
    image = image,
    type = type,
    location = location,
    id = null,
    url = url
)