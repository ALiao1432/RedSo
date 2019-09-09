package study.ian.redso.service.model

import com.google.gson.annotations.SerializedName

//data class Response(
//    @SerializedName("result") val results: List<Result>
//)
//
//data class Result (
//    @SerializedName("type") val type: String,
//    @SerializedName("url") var url: String? = null,
//    @SerializedName("id") var id: String? = null,
//    @SerializedName("name") var name: String? = null,
//    @SerializedName("position") var position: String? = null,
//    @SerializedName("expertise") var expertise: List<String>? = null,
//    @SerializedName("avatar") var avatar: String? = null
//)

data class Response(
    @SerializedName("result") val results: List<Result>
)

abstract class Result {
    abstract val type: String
}

data class Banner (
    @SerializedName("type") override val type: String,
    @SerializedName("url") var url: String? = null
) : Result()

data class Employee (
    @SerializedName("type") override val type: String,
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("position") var position: String? = null,
    @SerializedName("expertise") var expertise: List<String>? = null,
    @SerializedName("avatar") var avatar: String? = null
) : Result()