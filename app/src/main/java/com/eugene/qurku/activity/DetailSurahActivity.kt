package com.eugene.qurku.activity

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eugene.qurku.R
import com.eugene.qurku.adaper.DetailSurahListVerseRecyclerViewAdapter
import com.eugene.qurku.databinding.ActivityDetailSurahBinding
import com.eugene.qurku.fragment.surahdetails.InterpretationBottomSheetFragment
import com.eugene.qurku.repository.SurahDetailRepository
import com.eugene.qurku.response.interpretation.InterpretationDetailResponse
import com.eugene.qurku.response.surahdetails.SurahDetailResponse
import com.eugene.qurku.viewmodel.surahdetails.SurahDetailViewModel
import com.eugene.qurku.viewmodel.surahdetails.SurahDetailViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Response
import kotlin.system.exitProcess

class DetailSurahActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailSurahBinding
    private lateinit var viewModel: SurahDetailViewModel
    private lateinit var repository: SurahDetailRepository
    private var mediaPlayer: MediaPlayer? = null
    private var surahNumber: Int = -1
    private lateinit var dialogNoConnection: MaterialAlertDialogBuilder

    companion object{
        const val NUMBER_SURAH: String = "number"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Sedang memuat..."
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        dialogNoConnection = MaterialAlertDialogBuilder(this@DetailSurahActivity)
            .setTitle(resources.getString(R.string.no_connection))
            .setMessage(resources.getString(R.string.no_connection_message))
            .setPositiveButton("Ok!") { _, _ ->
                exitProcess(-1)
            }

        if(!checkConnection()){
            dialogNoConnection.show()
        }else{
            surahNumber = intent.getIntExtra(NUMBER_SURAH, -1)
            if(surahNumber > -1){
                repository = SurahDetailRepository()
                val factory = SurahDetailViewModelFactory(repository)

                viewModel = ViewModelProvider(this@DetailSurahActivity, factory)[SurahDetailViewModel::class.java]
                viewModel.getData(surahNumber)

                viewModel.isLoadingData.observe(this@DetailSurahActivity){
                    if(it == true){
                        binding.loading.visibility = View.VISIBLE
                    }else{
                        binding.loading.visibility = View.GONE
                    }
                }

                viewModel.dataSurahDetails.observe(this@DetailSurahActivity){ dataSurahDetails ->
                    if(dataSurahDetails.isSuccessful){
                        supportActionBar?.title = "${dataSurahDetails.body()?.surahDetailDataResponse?.namaLatin} (${dataSurahDetails.body()?.surahDetailDataResponse?.nama})"
                        viewModel.dataInterpretationDetails.observe(this@DetailSurahActivity){
                            val adapter = DetailSurahListVerseRecyclerViewAdapter(
                                this@DetailSurahActivity,
                                dataSurahDetails.body()?.surahDetailDataResponse?.surahDetailVerseResponse!!,
                                setOnClickPlayAudio = setOnClickPlayAudio(dataSurahDetails),
                                setOnClickInterpretation = setOnClickInterpretation(it)
                            )
                            binding.recyclerview.adapter = adapter
                            binding.recyclerview.layoutManager = LinearLayoutManager(this@DetailSurahActivity)
                        }
                    }
                }
            }else{
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPostResume() {
        super.onPostResume()
        if(!checkConnection()){
            dialogNoConnection.show()
        }
    }

    private fun setOnClickPlayAudio(it: Response<SurahDetailResponse>) =
        object : DetailSurahListVerseRecyclerViewAdapter.SetOnClickPlayAudio {
            override fun onClickPlayAudio(position: Int) {
                val audioUrl =
                    it.body()?.surahDetailDataResponse?.surahDetailVerseResponse!![position].surahDetailAudioResponse.x01

                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer!!.stop()
                }
                mediaPlayer?.release() // Release resources before reinitializing
                mediaPlayer = MediaPlayer()
                mediaPlayer!!.setAudioAttributes(AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build())

                mediaPlayer!!.setDataSource(audioUrl)
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()
            }
        }

    private fun setOnClickInterpretation(data: Response<InterpretationDetailResponse>): DetailSurahListVerseRecyclerViewAdapter.SetOnClickInterpretation {
        return object: DetailSurahListVerseRecyclerViewAdapter.SetOnClickInterpretation{
            override fun onClickInterpretation(position: Int) {
                val bottomSheet = InterpretationBottomSheetFragment(data.body()?.interpretationDetailDataResponse?.interpretationDetailInterpretationResponse!![position])
                bottomSheet.show(supportFragmentManager, InterpretationBottomSheetFragment.BOTTOM_SHEET_NAME)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer()
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