package study.ian.redso.service

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import study.ian.redso.service.model.Response

interface RedSoService {

    @GET("/catalog")
    fun getCatalog(
        @Query("team") team: String,
        @Query("page") page: Int
    ): Observable<Response>
}