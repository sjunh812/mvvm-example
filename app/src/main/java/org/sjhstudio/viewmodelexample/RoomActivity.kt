package org.sjhstudio.viewmodelexample

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.sjhstudio.viewmodelexample.adapter.ExampleAdapter
import org.sjhstudio.viewmodelexample.adapter.ExampleCallback
import org.sjhstudio.viewmodelexample.database.Example
import org.sjhstudio.viewmodelexample.databinding.ActivityRoomBinding
import org.sjhstudio.viewmodelexample.viewmodel.ExampleViewModel

class RoomActivity: BaseActivity() {

    private lateinit var binding: ActivityRoomBinding
    private lateinit var exampleVm: ExampleViewModel

    private lateinit var exampleAdapter: ExampleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_room)
        exampleVm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(this.application))[ExampleViewModel::class.java]

        setUI()
        setListener()
        observeExampleList()
    }

    private fun setUI() {
        exampleAdapter = ExampleAdapter().apply {
            setCallback(object: ExampleCallback {
                override fun delete(item: Example) {
                    lifecycleScope.launch(IO) {
                        exampleVm.delete(item)
                    }
                }
            })
        }
        binding.rv.layoutManager = LinearLayoutManager(this)
    }

    private fun setListener() {
        binding.saveBtn.setOnClickListener {
            lifecycleScope.launch(IO) {
                exampleVm.insert(Example(binding.et.text.toString()))
            }
        }
    }

    private fun observeExampleList() {
        exampleVm.getAll().observe(this) {
            exampleAdapter.items = it as ArrayList<Example>
            binding.rv.adapter = exampleAdapter
        }
    }
}