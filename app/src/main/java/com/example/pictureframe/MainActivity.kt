package com.example.pictureframe

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val startPhotoFrameModeButton: Button by lazy {
        findViewById<Button>(R.id.startPhotoFrameModeButton)
    }

    private val addPhotoButton: Button by lazy {
        findViewById<Button>(R.id.addPhotoButton)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //코드 추상화 : 이렇게 메소드로 초기화시키는 이유는 모든 코드가 OnCreat에 만들어지지 않고 나와있으면 기능을 찾기 어렵기 때문이다.
        initAddPhotoButton()
        initStartPhotoFrameModeButton()
    }

    private fun initAddPhotoButton() {
        addPhotoButton.setOnClickListener {
            when {
                //권한의 유무 체크
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    //TODO 권한이 부여되면 갤러리에서 사진을 선택하는 기능 추가
                }

                //권한이 없을 시에 띄우는 교육용팝업. ()안의 권한이 true가 반환되면 팝업을 띄운다.
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    //todo 교육용팝업 확인 후 권한 파업을 띄우는 기능
                    showPermissionContextPopup()
                }


                else -> {
                    //권한을 요청하는 팝업
                    requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
                }
            }
        }
    }

    private fun showPermissionContextPopup(){
        AlertDialog.Builder(this)
            .setTitle("권한이 필요합니다.")
            .setMessage("사진을 불러오기 위해 권한이 필요합니다.")
            .setPositiveButton("동의") { _, _ ->
                {
                    //권한요청
                    requestPermissions(
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        1000
                    )
                }
            }
            .setNegativeButton("거부") { _, _ -> } //그냥 닫으면 되니 필요없음.
            .create()
            .show()
    }

    private fun initStartPhotoFrameModeButton() {


    }


}