package com.example.data.mappers

import com.example.data.entities.db.DbPage
import com.example.domain.entities.Page

fun Page.toDbPage(url:String): DbPage = DbPage(
    id = index,
    url = url,
    pages = pages,
    next = next,
    prev = prev,
    count = count
)