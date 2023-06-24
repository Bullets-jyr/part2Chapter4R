package kr.co.bullets.part2chapter4r

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.bullets.part2chapter4r.adapter.RepoAdapter
import kr.co.bullets.part2chapter4r.databinding.ActivityRepoBinding
import kr.co.bullets.part2chapter4r.model.Repo
import kr.co.bullets.part2chapter4r.network.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoBinding
    private lateinit var repoAdapter: RepoAdapter

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username") ?: return

        binding.usernameTextView.text = username

        repoAdapter = RepoAdapter()

        binding.repoRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@RepoActivity)
            adapter = repoAdapter
        }

        listRepo(username)
    }

    private fun listRepo(username: String) {
        val githubService = retrofit.create(GithubService::class.java)
        githubService.listRepos(username).enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                Log.e("RepoActivity", "List Repo : ${response.body().toString()}")

                repoAdapter.submitList(response.body())
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

            }
        })
    }
}