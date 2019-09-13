package study.ian.redso.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import study.ian.redso.service.model.Banner
import study.ian.redso.service.model.Employee
import study.ian.redso.service.model.Result

enum class TYPE(val type: String) {
    BANNER("banner"),
    EMPLOYEE("employee")
}

fun getTypeAdapterFactory(): RuntimeTypeAdapterFactory<Result> {
    return RuntimeTypeAdapterFactory
        .of(Result::class.java, "type")
        .registerSubtype(Banner::class.java, TYPE.BANNER.type)
        .registerSubtype(Employee::class.java, TYPE.EMPLOYEE.type)
}

fun getGson(): Gson {
    return GsonBuilder().registerTypeAdapterFactory(getTypeAdapterFactory())
        .create()
}