package com.ernad.tvmaze.network

import com.ernad.tvmaze.ui.details.movie.MovieObject
import com.ernad.tvmaze.ui.list.movieListObject.MazeObject
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import okhttp3.OkHttpClient




private const val BASE_URL = "https://api.tvmaze.com/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getPhotos] method
 */
interface MazeApiService {
    /**
     * Returns a [List] of [MarsPhoto] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("search/shows")
    suspend fun getItems(@Query ("q") q : String): List<MazeObject>

    @GET("shows/{id}")
    suspend fun getMovieDetails(@Path("id")id : Int) : MovieObject

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MazeApi {
    val retrofitService: MazeApiService by lazy { retrofit.create(MazeApiService::class.java) }
}
