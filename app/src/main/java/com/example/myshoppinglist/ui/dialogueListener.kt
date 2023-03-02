package com.example.myshoppinglist.ui

import com.example.myshoppinglist.database.entity.Item

interface dialogueListener {
    fun onAddBtnClick(item: Item)
}