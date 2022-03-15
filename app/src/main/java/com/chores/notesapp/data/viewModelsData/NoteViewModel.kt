package com.chores.notesapp.data.viewModelsData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.chores.notesapp.data.model.NoteData
import com.chores.notesapp.data.model.NoteDatabase
import com.chores.notesapp.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application){
    private val noteDao = NoteDatabase.getDatabase(application).noteDao()
    private val repository : NoteRepository
    val getAllData : LiveData<List<NoteData>>
    val sortedByHighPriority : LiveData<List<NoteData>>
    val sortedByLowPriority : LiveData<List<NoteData>>

    init {
        repository = NoteRepository(noteDao)
        getAllData = repository.getAllData
        sortedByHighPriority = repository.sortByHighPriority
        sortedByLowPriority = repository.sortByLowPriority
    }

    fun  insertData(noteData: NoteData){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertData(noteData)
        }
    }

    fun updateData(noteData: NoteData){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateData(noteData)
        }
    }

    fun deleteData(noteData: NoteData){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteData(noteData)
        }
    }

    fun deleteAllData(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllData()
        }
    }

    fun searchDatabase(searchQuery: String) : LiveData<List<NoteData>>{
        return  repository.searchData(searchQuery)
    }
}