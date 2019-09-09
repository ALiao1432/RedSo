package study.ian.redso.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBuilder {
    companion object {
        private var apiBaseUrl = "https://us-central1-redso-challenge.cloudfunctions.net/"

        fun create(): RedSoService {
            val retrofit = Retrofit.Builder()
                .baseUrl(apiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(RedSoService::class.java)
        }
    }
}