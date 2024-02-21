package com.eugene.qurku.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eugene.qurku.R
import com.eugene.qurku.adaper.MainListSurahRecyclerViewAdapter
import com.eugene.qurku.admob.RewardedAd
import com.eugene.qurku.databinding.ActivityMainBinding
import com.eugene.qurku.repository.MainRepository
import com.eugene.qurku.response.listsurah.ListSurahDataResponse
import com.eugene.qurku.viewmodel.main.MainViewModel
import com.eugene.qurku.viewmodel.main.MainViewModelFactory
import com.google.android.gms.ads.MobileAds
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var dialogNoConnection: MaterialAlertDialogBuilder
    private lateinit var rewardedAd: RewardedAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this@MainActivity)
        rewardedAd = RewardedAd(this@MainActivity, R.string.reward_ad_id)

        dialogNoConnection = MaterialAlertDialogBuilder(this@MainActivity)
            .setTitle(resources.getString(R.string.no_connection))
            .setMessage(resources.getString(R.string.no_connection_message))
            .setPositiveButton("Ok!") { _, _ ->
                exitProcess(-1)
            }

        if(!checkConnection()){
            dialogNoConnection.show()
        }else{
            val mainRepository = MainRepository()
            val viewModelFactory = MainViewModelFactory(mainRepository)
            viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
            viewModel.isLoadingGetData.observe(this@MainActivity) {
                if (it == true) {
                    binding.loading.visibility = View.VISIBLE
                } else {
                    binding.loading.visibility = View.GONE
                }
            }

            viewModel.getAllSurah()
            viewModel.data.observe(this) { it ->
                if (it.isSuccessful) {

                    val adapter = MainListSurahRecyclerViewAdapter(this, it.body()?.data!!, object: MainListSurahRecyclerViewAdapter.ItemClickListener{
                        override fun onItemClickListener(position: Int) {
                            rewardedAd.loadAd()
                            rewardedAd.showAd(){
                                val intent = Intent(this@MainActivity, DetailSurahActivity::class.java)
                                intent.putExtra(DetailSurahActivity.NUMBER_SURAH, it.body()?.data!![position].nomor)
                                startActivity(intent)
                            }
                        }
                    })

                    binding.recyclerView.adapter = adapter
                    binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    initializeSearchView(it.body()?.data!!)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(!checkConnection()){
            dialogNoConnection.show()
        }
    }

    private fun initializeSearchView(data: List<ListSurahDataResponse>){
        binding.searchView.editText.setOnEditorActionListener { v, _, _ ->
            val query = v.text.toString()
            val filterData = data.filter{
                it.namaLatin.contains(query, true) || it.nomor.toString() == query ||
                        it.nama.contains(query, true) || it.tempatTurun.contains(query, true) ||
                        it.jumlahAyat.toString() == query || it.deskripsi.contains(query, true) ||
                        it.arti.contains(query, true)
            }

            val adapter = MainListSurahRecyclerViewAdapter(this, filterData, object: MainListSurahRecyclerViewAdapter.ItemClickListener{
                override fun onItemClickListener(position: Int) {
                    rewardedAd.loadAd()
                    rewardedAd.showAd {
                        val intent = Intent(this@MainActivity, DetailSurahActivity::class.java)
                        intent.putExtra(DetailSurahActivity.NUMBER_SURAH, filterData[position].nomor)
                        startActivity(intent)
                    }
                }
            })

            binding.searchRecyclerview.adapter = adapter
            binding.searchRecyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            return@setOnEditorActionListener false
        }
    }

    private fun checkConnection(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw      = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }
}