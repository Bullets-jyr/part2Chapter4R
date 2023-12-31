package kr.co.bullets.part2chapter4r.network

import kr.co.bullets.part2chapter4r.model.Repo
import kr.co.bullets.part2chapter4r.model.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
//    @Headers("Authorization: Bearer <>")
    @GET("/users/{username}/repos")
    fun listRepos(@Path("username") username: String, @Query("page") page: Int): Call<List<Repo>>

//    @Headers("Authorization: Bearer <>")
    @GET("/search/users")
    fun searchUsers(@Query("q") q: String): Call<UserDto>
}