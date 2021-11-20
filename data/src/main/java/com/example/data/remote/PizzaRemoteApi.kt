package com.example.data.remote

//import com.example.margarettipizza.domain.entities.Order
import com.example.data.remote.dto.OrderRemoteDto
import com.example.data.remote.dto.PizzaDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PizzaRemoteApi {

    @GET("pizza")
    fun getAllPizza(): Single<List<PizzaDto>>

    @GET("pizza/{id}")
    fun getPizzaById(@Path("id") id: Int): Single<PizzaDto>

    //check if return is necessary
    @POST("pizza/order")
    fun postOrder(@Body orderList: List<OrderRemoteDto>): Completable

}