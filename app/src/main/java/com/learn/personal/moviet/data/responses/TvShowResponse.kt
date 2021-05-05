package com.learn.personal.moviet.data.responses

import com.google.gson.annotations.SerializedName
import com.learn.personal.moviet.data.models.TvShow

class TvShowResponse {
    @SerializedName("results")
    val results: List<TvShow>? = null
}