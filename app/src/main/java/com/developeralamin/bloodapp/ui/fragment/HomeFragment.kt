package com.developeralamin.bloodapp.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.developeralamin.bloodapp.adapter.Useradapter
import com.developeralamin.bloodapp.databinding.FragmentHomeBinding
import com.developeralamin.bloodapp.model.UserModel
import com.developeralamin.bloodapp.utils.Config
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    lateinit var db: FirebaseFirestore

    private lateinit var list: ArrayList<UserModel>
    private lateinit var adapter: Useradapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        list = ArrayList()
        adapter = Useradapter(this, list)

        db = FirebaseFirestore.getInstance()

        Config.showDialog(requireContext())
        db.collection("users").addSnapshotListener { value, error ->
            val list = arrayListOf<UserModel>()
            val data = value?.toObjects(UserModel::class.java)
            list.addAll(data!!)

            binding.userRecyclerView.adapter = Useradapter(this, list)
            adapter.updateData(list)

           adapter.updateData(list)

            Config.hideDialog()
        }

        searchData()

        return binding.root
    }

    lateinit var searchText: String

    private fun searchData() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                searchText = s.toString().toLowerCase()

                updateRecylerView()
            }
        })
    }


    private fun updateRecylerView() {
        val data = ArrayList<UserModel>()

        for (item in list) {

            var coinName = item.phone!!.lowercase(Locale.getDefault())

            if (coinName.contains(searchText)){
                data.add(item)
            }
            
        }
        adapter.updateData(data)
    }

}