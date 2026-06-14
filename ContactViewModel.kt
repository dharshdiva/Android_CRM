package com.example.crmapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).contactDao()

    val allContacts: LiveData<List<Contact>> = dao.getAllContacts()

    fun insert(contact: Contact) = viewModelScope.launch {
        dao.insert(contact)
    }

    fun update(contact: Contact) = viewModelScope.launch {
        dao.update(contact)
    }

    fun delete(contact: Contact) = viewModelScope.launch {
        dao.delete(contact)
    }

    suspend fun getContactById(id: Long): Contact? = dao.getContactById(id)
}
