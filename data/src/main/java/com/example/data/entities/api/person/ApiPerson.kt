package com.example.data.entities.api.details.person

import com.google.gson.annotations.SerializedName


data class ApiPerson (

  @SerializedName("info"    ) var info    : Info?              = Info(),
  @SerializedName("results" ) var results : ArrayList<Results> = arrayListOf()

)