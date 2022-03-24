package org.sjhstudio.viewmodelexample

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import org.sjhstudio.viewmodelexample.databinding.ActivityMainBinding
import org.sjhstudio.viewmodelexample.viewmodel.MainViewModel

class MainActivity: BaseActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var vm: MyViewModel
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        vm = ViewModelProvider(this)[MyViewModel::class.java]
        binding.viewModel = vm
        binding.lifecycleOwner = this

        /*
        val observer = Observer<Int> {
            binding.dataTv.text = it.toString()
        }
        vm.data.observe(this, observer)
        */

        vm.data.observe(this) {
            binding.dataTv.text = it.toString()
        }

        setListener()
    }

    fun setListener() {
        binding.increaseBtn.setOnClickListener { vm.increase() }
        binding.decreaseBtn.setOnClickListener { vm.decrease() }
        binding.roomExampleBtn.setOnClickListener { startActivity(Intent(this, RoomActivity::class.java)) }
        binding.exampleBtn.setOnClickListener { startActivity(Intent(this, ExampleActivity::class.java)) }
    }
}