package com.example.data.entities.api.details.person

import com.google.gson.annotations.SerializedName


data class Origin (

  @SerializedName("name" ) var name : String? = null,
  @SerializedName("url"  ) var url  : String? = null

)