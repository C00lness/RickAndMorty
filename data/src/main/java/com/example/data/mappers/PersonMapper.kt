package com.example.data.mappers

import com.example.data.entities.api.details.person.Results
import com.example.data.entities.db.DbPerson
import com.example.domain.entities.Person

fun Results.toPerson(): Person = Person(
    name = name,
    species = species,
    status = status,
    gender = gender,
    image = image,
    index = id!!
)

fun DbPerson.toPerson(): Person = Person(
    name = name,
    species = species,
    status = status,
    gender = gender,
    image = image,
    index = id!!
)

fun Person.toDbPerson(url:String): DbPerson = DbPerson(
    name = name,
    species = species,
    status = status,
    gender = gender,
    image = image,
    id = null,
    url = url
)