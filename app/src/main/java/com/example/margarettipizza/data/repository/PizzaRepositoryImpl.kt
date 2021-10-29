package com.example.margarettipizza.data.repository

//it's better to inject dao here , but for now we can use dao like object
//class PizzaRepositoryImpl : PizzaRepository {
//    private val remoteDataSource = NetworkModule.retrofit
//
//
//    override fun getAll(): Observable<NetworkResource<List<PizzaDto>>> {
//        val list = remoteDataSource.getAllPizza()
//        list.map(Function {
//            return@Function NetworkResource.Success(it.body()!!)
//        }).cast(NetworkResource::class.java).subscribeOn(Schedulers.io())
////        list.map { response ->
////            when{
////                response.body().isNullOrEmpty() -> return@map NetworkResource.Error<List<PizzaDto>>("Body is null")
////                response.isSuccessful -> NetworkResource.Success(response.body()!!)
////                else -> return@map NetworkResource.Error<List<PizzaDto>>("qwe")
////            }
////        }.startWith(
////            ObservableSource<NetworkResource.Loading<List<PizzaDto>>> {
////                it.onNext(NetworkResource.Loading())
////            }
////        ).subscribeOn(Schedulers.io())
//        return list as Observable<NetworkResource<List<PizzaDto>>>
//    }
//
//    override fun getByName(query: String): @NonNull Observable<NetworkResource<List<PizzaDto>>> {
//
//        val list = remoteDataSource.getAllPizza()
//        list.map(Function {
//            return@Function NetworkResource.Success(it.body()!!)
//        }).cast(NetworkResource::class.java).subscribeOn(Schedulers.io())
//        return list as Observable<NetworkResource<List<PizzaDto>>>
//
//
//
//
////        Log.d("qwe", "getByName: on start $query ")
////        val obs = Observable.create<NetworkResource<List<PizzaDto>>> { emmiter ->
////            val response = remoteDataSource.getAllPizza()
////            when {
////                response.body().isNullOrEmpty() -> emmiter.onError(throw Exception("body is null"))
////                response.isSuccessful && !response.body().isNullOrEmpty() -> {
////                    emmiter.onNext(
////                        NetworkResource.Success(
////                            response.body()!!
////                        ))
////                } //already null checked
////            }
////        }.subscribeOn(Schedulers.io())
////        return obs
////        val sub = BehaviorSubject.create<NetworkResource<List<PizzaDto>>> { emmiter ->
////            emmiter.onNext(NetworkResource.Loading())
////            val response = remoteDataSource.getAllPizza()
////            when {
////                response.body().isNullOrEmpty() -> emmiter.onError(throw Exception("body is null"))
////                response.isSuccessful && !response.body().isNullOrEmpty() -> emmiter.onNext(
////                    NetworkResource.Success(response.body()!!)
////                ) //already null checked
////            }
////        }.subscribeOn(Schedulers.io())
////        return sub as BehaviorSubject<NetworkResource<List<PizzaDto>>>
//    }
//
//    override fun getPizzaById(id: Int): PizzaEntity? {
//        return PizzaDatabase.pizzaDao.getById(id)
//    }
//}