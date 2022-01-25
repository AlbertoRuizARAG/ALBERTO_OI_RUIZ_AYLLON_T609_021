package com.example.fotogallery

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.fotogallery.databinding.ActivityMainBinding
import java.io.File
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import java.io.OutputStream


private const val REQUEST_CODE = 13
private lateinit var filePhoto: File
private const val FILE_NAME = "photo"

class MainActivity : AppCompatActivity() {
    private val toastTag = "TestAppGalleryPhoto"

    private lateinit var binding: ActivityMainBinding

    companion object {
        private val IMAGE_CHOOSE = 1000;
        private val PERMISSION_CODE_CHOOSE_PHOTO = 1001;
        private val PERMISSION_CODE_TAKE_PHOTO = 1002;
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initButtons()

    }



    private fun initButtons() {
        binding.takePhotobutton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    val permissions = arrayOf(Manifest.permission.CAMERA)
                    requestPermissions(permissions, PERMISSION_CODE_TAKE_PHOTO)
                } else{
                    takePhoto()
                }
            } else {
                takePhoto()
            }

        }

        binding.choosePhotoButton.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE_CHOOSE_PHOTO)
                } else{
                    chooseImageGallery();

                }
            }else{
                chooseImageGallery();
            }
        }
    }

    private fun getPhotoFile(fileName: String): File {
        val directoryStorage = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", directoryStorage)
    }

    // Carga de imagen de galeria
    private fun chooseImageGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        observingChooseImagenActivity.launch(intent)
    }

    //Capturar Foto
    private fun takePhoto() {
        val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        filePhoto = getPhotoFile(FILE_NAME)


        val providerFile = FileProvider.getUriForFile(
            this,
            "com.example.fotogallery.fileprovider",
            filePhoto
        )

        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, providerFile)
        if (takePhotoIntent.resolveActivity(this.packageManager) != null) {
            observingTakeImagenActivity.launch(takePhotoIntent)
        } else {
            Toast.makeText(this,"Camera could not open", Toast.LENGTH_SHORT).show()
        }
    }

    //Rescalado de la imagen para ajustar a la UI
    fun resizeImage(imagen: Bitmap): Bitmap {
        val resized = Bitmap.createScaledBitmap(imagen, 250, 250, true)
        return resized
    }

    //Observables para ver si devolvemos
    var observingChooseImagenActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK){
            val data: Intent? = result.data
            binding.mainImageView.setImageURI(data?.data)
        } else {
            Toast.makeText(this,"${toastTag} + ${result.resultCode} + ${result.data}", Toast.LENGTH_SHORT).show()
        }
    }

    //Observables para ver si devolvemos
    var observingTakeImagenActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK){
            val data: Intent? = result.data
            val takenPhoto = BitmapFactory.decodeFile(filePhoto.absolutePath)

            saveImageToStorage(takenPhoto)
            val resizedTakenPhoto = resizeImage(takenPhoto)
            binding.mainImageView.setImageBitmap(resizedTakenPhoto)
        } else {
            Toast.makeText(this,"${toastTag} + ${result.resultCode} + ${result.data}", Toast.LENGTH_SHORT).show()
        }
    }


    // Metodo para el almacenamiento de la imagen
    private fun saveImageToStorage(imagenBitmap: Bitmap) {
        val imageOutStream: OutputStream?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues()
            values.put(MediaStore.Images.Media.DISPLAY_NAME, "image_screenshot.jpg")
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            imageOutStream = contentResolver.openOutputStream(uri!!)

            try {
                imagenBitmap.compress(CompressFormat.JPEG, 100, imageOutStream)
            } finally {
                imageOutStream!!.close()
            }

        } else {
            Toast.makeText(this,"Operacion de almacenamiento no permitida", Toast.LENGTH_SHORT).show()
        }

    }
    //Control de Permisos
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Toast.makeText(this,"${requestCode}", Toast.LENGTH_SHORT).show()
        when(requestCode){

            PERMISSION_CODE_CHOOSE_PHOTO -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    chooseImageGallery()
                }else{
                    Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            PERMISSION_CODE_TAKE_PHOTO -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    takePhoto()

                }else{
                    Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}