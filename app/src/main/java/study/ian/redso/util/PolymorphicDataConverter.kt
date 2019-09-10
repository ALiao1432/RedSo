package study.ian.redso.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import study.ian.redso.service.model.Banner
import study.ian.redso.service.model.Employee
import study.ian.redso.service.model.Result

const val TYPE = "type"
const val TYPE_BANNER = "banner"
const val TYPE_EMPLOYEE = "employee"

fun getTypeAdapterFactory(): RuntimeTypeAdapterFactory<Result> {
    return RuntimeTypeAdapterFactory
        .of(Result::class.java, TYPE)
        .registerSubtype(Banner::class.java, TYPE_BANNER)
        .registerSubtype(Employee::class.java, TYPE_EMPLOYEE)
}

fun getGson(): Gson {
    return GsonBuilder().registerTypeAdapterFactory(getTypeAdapterFactory())
        .create()
}