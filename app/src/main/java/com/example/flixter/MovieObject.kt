package com.example.flixter


import com.google.gson.annotations.SerializedName
class MovieObject {
    @JvmField
    @SerializedName("title")
    var title: String? = null

    @SerializedName("overview")
    var description: String? = null

    @SerializedName("poster_path")
    var imageURL: String? = null
}