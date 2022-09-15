package com.nisaardiyanti.mydata
//nama packages
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*


class MainActivity : AppCompatActivity() {
//kelas mainactivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    //berfungsi untuk mengakses res layout dengan menggunakan data binding
        setContentView(R.layout.activity_main)
//digunakan untuk membuat variabel agar dapat menampung binding component dengan tipe edittext
        val fileName = findViewById<EditText>(R.id.editFile)
        val fileData = findViewById<EditText>(R.id.editData)
        val btnSave = findViewById<Button>(R.id.btnSave)
    val btnSave2 = findViewById<Button>(R.id.btnSave2)
    //tombol simpan
        val btnView = findViewById<Button>(R.id.btnView)
//tombol lihat
        btnSave.setOnClickListener(View.OnClickListener {
           //untuk mengatur tombol filename agar berfungsi
            val file:String = fileName.text.toString()
            //string untuk nama file
            val data:String = fileData.text.toString()
            //string untuk data yang disimpan
            val fileOutputStream:FileOutputStream
            // val untuk tampilan
            try {
                fileOutputStream = openFileOutput(file, MODE_PRIVATE)

                fileOutputStream.write(data.toByteArray())
            } catch (e: FileNotFoundException){
                e.printStackTrace()
                //menyimpan
            }catch (e: NumberFormatException){
                e.printStackTrace()
            }catch (e: IOException){
                e.printStackTrace()
            }catch (e: Exception){
                e.printStackTrace()
            }
            Toast.makeText(applicationContext,"data save",Toast.LENGTH_LONG).show()
            //akan menampilkan komentar data save setelah menyimpan data
            fileName.text.clear()
            fileData.text.clear()
        })


        btnView.setOnClickListener(View.OnClickListener {
           //mengatur tombol view agar berfungsi saat  diklik
            val filename = fileName.text.toString()
            if(filename.toString()!=null && filename.toString().trim()!=""){
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(filename)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)
                }
                //Displaying data on EditText
                fileData.setText(stringBuilder.toString()).toString()
            }else{
                Toast.makeText(applicationContext,"file name cannot be blank",Toast.LENGTH_LONG).show()
                //akan muncul komentar file tidak bisa kosong
            }
        })

    }
}

