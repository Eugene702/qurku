package com.eugene.qurku.fragment.surahdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eugene.qurku.databinding.ComponentSurahDetailInterpretationBottomSheetBinding
import com.eugene.qurku.response.interpretation.InterpretationDetailInterpretationResponse
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InterpretationBottomSheetFragment(
    private val data: InterpretationDetailInterpretationResponse
): BottomSheetDialogFragment() {
    private lateinit var binding: ComponentSurahDetailInterpretationBottomSheetBinding

    companion object{
        const val BOTTOM_SHEET_NAME = "InterpretationBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ComponentSurahDetailInterpretationBottomSheetBinding.inflate(inflater, container, false)
        "Ayat ${data.ayat}".also { binding.verse.text = it }
        binding.interpretation.text = data.teks
        return binding.root
    }
}