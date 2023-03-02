package com.example.myshoppinglist.ui

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.myshoppinglist.R
import com.example.myshoppinglist.database.entity.Item
import androidx.appcompat.*
import com.example.myshoppinglist.databinding.ActivityMainBinding
import com.example.myshoppinglist.databinding.AddDialogBinding

class addItemDialogue(context: Context, var dialogListener: dialogueListener) : AppCompatDialog(context){

    private lateinit var binding: AddDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.add_dialog)

        binding = AddDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvAdd.setOnClickListener {

            val name = binding.etName.text.toString()

            // Toast to display enter items in edit text
            if (name.isEmpty()) {
                Toast.makeText(context, "Please Enter Item Name", Toast.LENGTH_SHORT).show()
            }

            val item = Item(name)
            dialogListener.onAddBtnClick(item)
            dismiss()
        }

        // On click listener on cancel text to close dialog box
        binding.tvCancel.setOnClickListener {
            cancel()
        }
    }
}