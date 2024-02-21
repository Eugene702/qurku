package com.eugene.qurku.admob

import android.app.Activity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class RewardedAd(
    private val activity: Activity,
    private val adId: Int
) {
    private var rewardedAd: RewardedAd? = null

    fun loadAd(){
        val adRequest = AdRequest.Builder().build()

        RewardedAd.load(
            activity,
            activity.getString(adId),
            adRequest,
            object : RewardedAdLoadCallback(){
                override fun onAdLoaded(p0: RewardedAd) {
                    super.onAdLoaded(p0)
                    rewardedAd = p0
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    rewardedAd = null
                }
            }
        )
    }

    fun showAd(onAdDismiss: () -> Unit){
        if(rewardedAd != null){
            rewardedAd!!.fullScreenContentCallback = object : FullScreenContentCallback(){
                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    rewardedAd = null
                    onAdDismiss()
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)
                    rewardedAd = null
                }
            }

            rewardedAd!!.show(activity){}
        }else{
            onAdDismiss()
        }
    }
}