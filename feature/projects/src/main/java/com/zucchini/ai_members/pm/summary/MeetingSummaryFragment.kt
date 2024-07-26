package com.zucchini.ai_members.pm.summary

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.zucchini.ai_members.pm.AiPmViewModel
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentMeetingSummaryBinding
import com.zucchini.uistate.UiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.File

class MeetingSummaryFragment : Fragment() {
    private var _binding: FragmentMeetingSummaryBinding? = null
    private val binding: FragmentMeetingSummaryBinding get() = _binding!!

    private val viewModel: AiPmViewModel by activityViewModels()

    private val launcherAudio: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleActivityResult(result)
        }

    private val requestPermissionLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                if (!it.value) {
                    Log.e("Permission", "Permission denied: ${it.key}")
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMeetingSummaryBinding.inflate(inflater, container, false)

        clickNavigateToRecord()
        collectMeetingSummaryResultText()

        return binding.root
    }

    private fun clickNavigateToRecord() {
        binding.ivFileUpload.setOnClickListener {
            if (checkAndRequestPermissions()) {
                openFilePicker()
            }
        }
    }

    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "audio/*" // 탐색할 파일 MIME 타입 설정

        launcherAudio.launch(intent)
    }

    private fun checkAndRequestPermissions(): Boolean {
        val readPermission =
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE,
            )
        val writePermission =
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
            )

        val listPermissionsNeeded = mutableListOf<String>()

        if (readPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (writePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        return if (listPermissionsNeeded.isNotEmpty()) {
            requestPermissionLauncher.launch(listPermissionsNeeded.toTypedArray())
            false
        } else {
            true
        }
    }

    private fun sendRecordingFile(recordFilePath: String) {
        viewModel.sendMeetingRecordFile(recordFilePath)
    }

    private fun viewUploadedFileName(fileUri: String) {
        val recordFile = File(fileUri)
        binding.tvFileUpload.text = recordFile.name
    }

    fun getRealPathFromUri(
        contentUri: Uri,
        context: Context,
    ): String? {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Audio.Media.DATA)
            cursor = context.contentResolver.query(contentUri, proj, null, null, null)
            val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
            if (cursor != null && cursor.moveToFirst()) {
                return cursor.getString(columnIndex!!)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    private fun handleActivityResult(result: ActivityResult) {
        // 파일 이름 뷰에 반환 업데이트
        viewUploadedFileName(result.data?.data.toString())

        when (result.resultCode) {
            Activity.RESULT_OK -> {
                val data = result.data // 콜백 메서드를 통해 전달 받은 ActivityResult 객체에서 Intent 객체 추출
                val audioUri: Uri? = data?.data // Intent 객체에서 선택한 오디오 파일의 위치를 가리키는 Uri 추출

                val realPath = getRealPathFromUri(audioUri!!, requireContext())
                sendRecordingFile(realPath ?: "")
                Log.d("MeetingSummaryFragment", "audioUri: $audioUri")
            }

            Activity.RESULT_CANCELED -> { // 사용자가 파일 탐색 중 선택을 하지 않았을 때 내부 코드 실행
                Log.d("launcher_audio Callback", "audio picking is canceled")
            }

            else -> { // 그 외의 경우 예외 처리
                Log.e("launcher_audio Callback", "audio picking has failed")
            }
        }
    }

    private fun collectMeetingSummaryResultText() {
        viewModel.meetingSummaryResultText
            .flowWithLifecycle(lifecycle)
            .onEach { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        // 로딩 중인 경우 로딩 화면 표시
                        // delay(10000)
                    }

                    is UiState.Success -> {
                        binding.aiPmSummaryMeetingResult.text = uiState.data
                    }

                    else -> {
                        binding.aiPmSummaryMeetingResult.text = getString(R.string.fail_to_summary)
                    }
                }
            }.launchIn(lifecycleScope)
    }
}
