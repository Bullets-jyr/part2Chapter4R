package kr.co.bullets.part2chapter4r

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.bullets.part2chapter4r.databinding.ActivityRepoBinding

class RepoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}