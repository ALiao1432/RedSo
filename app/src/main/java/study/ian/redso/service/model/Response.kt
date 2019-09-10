package study.ian.redso.service.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("results") val results: List<Result>
)

sealed class Result(
    @SerializedName("type") val type: String
)

data class Banner(
    @SerializedName("url") var url: String? = null
) : Result(type = "banner")

data class Employee(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("position") var position: String? = null,
    @SerializedName("expertise") var expertise: List<String>? = null,
    @SerializedName("avatar") var avatar: String? = null
) : Result(type = "employee")