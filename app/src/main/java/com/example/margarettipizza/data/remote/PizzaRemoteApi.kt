package com.example.margarettipizza.data.remote

//import com.example.margarettipizza.domain.entities.Order
import com.example.margarettipizza.data.remote.dto.PizzaDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PizzaRemoteApi {

    @GET("pizza")
    fun getAllPizza(): Observable<List<PizzaDto>>

    @GET("pizza/{id}")
    fun getPizzaById(@Path("id") id: Int): Observable<List<PizzaDto>>

//    //check if return is necessary
//    @POST("pizza/order")
//    fun postOrder(order : Order)

}