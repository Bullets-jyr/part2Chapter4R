package kr.co.bullets.part2chapter4r

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.bullets.part2chapter4r.adapter.UserAdapter
import kr.co.bullets.part2chapter4r.databinding.ActivityMainBinding
import kr.co.bullets.part2chapter4r.model.Repo
import kr.co.bullets.part2chapter4r.model.UserDto
import kr.co.bullets.part2chapter4r.network.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter

//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://api.github.com/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()

    private var searchFor: String = ""
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val githubService = retrofit.create(GithubService::class.java)
//        githubService.listRepos("Bullets-jyr").enqueue(object : Callback<List<Repo>> {
//            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
//                Log.e("MainActivity", "List Repo : ${response.body().toString()}")
//            }
//
//            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
//
//            }
//        })

        userAdapter = UserAdapter() {
            val intent = Intent(this@MainActivity, RepoActivity::class.java)
            intent.putExtra("username", it.username)
            startActivity(intent)
        }

        binding.userRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }

        val runnable = Runnable {
            searchUser()
        }

        binding.searchEditText.addTextChangedListener {
//            searchUser(it.toString())

            searchFor = it.toString()
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 300)
        }
    }

    private fun searchUser() {
//        Log.e("MainActivity", "q : $q")
        val githubService = APIClient.retrofit.create(GithubService::class.java)

        githubService.searchUsers(searchFor).enqueue(object : Callback<UserDto> {
            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                Log.e("MainActivity", "Search User : ${response.body().toString()}")
                userAdapter.submitList(response.body()?.items)
            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {
                Toast.makeText(this@MainActivity, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }
}