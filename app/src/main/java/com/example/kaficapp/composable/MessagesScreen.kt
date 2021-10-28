package com.example.kaficapp.composable

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


fun CreateToast(context:Context,message:String,lenght:Int=Toast.LENGTH_SHORT){
    Toast.makeText(context,message,lenght).show()
}