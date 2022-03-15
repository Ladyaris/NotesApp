package com.chores.notesapp.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chores.notesapp.R
import com.chores.notesapp.data.model.NoteData
import com.chores.notesapp.data.viewModelsData.NoteViewModel
import com.chores.notesapp.databinding.FragmentAddBinding
import com.chores.notesapp.fragments.SharedViewModels

class AddFragment : Fragment(){

    private var _addBinding : FragmentAddBinding? = null
    private val addBinding get() = _addBinding!!

    private val notesViewModel : NoteViewModel by viewModels()
    private val sharedViewModel : SharedViewModels by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _addBinding = FragmentAddBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        addBinding.spPriorities.onItemSelectedListener = sharedViewModel.listener

        return addBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add){
            insertDataToDatabase()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDatabase() {
        val mTitle = addBinding.etTitle.text.toString()
        val mPriority = addBinding.spPriorities.selectedItem.toString()
        val mDesc = addBinding.etDesc.text.toString()

        val validation = sharedViewModel.verifyDataFromUser(mTitle, mDesc)
        if (validation){
            val newData = NoteData(
                0,
                mTitle,
                sharedViewModel.parsePriority(mPriority),
                mDesc
            )
            notesViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Berhasil ditambahkan", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Tolong isi ya", Toast.LENGTH_SHORT).show()
        }
    }

}

