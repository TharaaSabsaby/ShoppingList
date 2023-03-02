package com.example.myshoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myshoppinglist.adapter.shoppingAdapter
import com.example.myshoppinglist.database.entity.Item
import com.example.myshoppinglist.database.myListDB
import com.example.myshoppinglist.database.shoppingItems
import com.example.myshoppinglist.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var liveList:LiveData<List<Item>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listRepository = shoppingItems(myListDB(this))
        val factory = listVMFactory(listRepository)

        val viewModel = ViewModelProvider(this, factory).get(listViewModel::class.java)

        val adapter = shoppingAdapter(listOf(),viewModel)

//        adapter.list = viewModel.allShoppingItems()
        binding.listRecyclerview.layoutManager = LinearLayoutManager(this)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.listRecyclerview.addItemDecoration(
            DividerItemDecoration(
                baseContext,
                layoutManager.orientation
            )
        )

//        var finalList : List<Item>
//            lifecycleScope.launch {

//            adapter.list = getList(viewModel.allShoppingItems())
//
//                adapter.notifyDataSetChanged()
//
//                binding.listRecyclerview.adapter = adapter
//        }


        viewModel.allShoppingItems().observe(this, Observer {
            adapter.list = it
            adapter.notifyDataSetChanged()
        })

        binding.listRecyclerview.adapter = adapter

        // on ClickListener on button to open dialog box
        binding.addItem.setOnClickListener {
            addItemDialogue(this, object : dialogueListener {
                override fun onAddBtnClick(item: Item) {
                    viewModel.insert(item)
                }

            }).show()
        }
    }
    suspend fun getList(flow : Flow<List<Item>>) : List<Item>{
        println("123 123 "+flow.first())
         return flow.first()
    }
}