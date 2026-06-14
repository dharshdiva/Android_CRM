package com.example.crmapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.crmapp.databinding.ActivityAddEditContactBinding
import kotlinx.coroutines.launch

class AddEditContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditContactBinding
    private val viewModel: ContactViewModel by viewModels()
    private var existingContact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contactId = intent.getLongExtra("contact_id", -1L)

        if (contactId != -1L) {
            title = "Edit Contact"
            lifecycleScope.launch {
                existingContact = viewModel.getContactById(contactId)
                existingContact?.let { contact ->
                    binding.etName.setText(contact.name)
                    binding.etCompany.setText(contact.company)
                    binding.etPhone.setText(contact.phone)
                    binding.etEmail.setText(contact.email)
                    binding.etNotes.setText(contact.notes)
                }
            }
            binding.btnDelete.visibility = android.view.View.VISIBLE
        } else {
            title = "Add Contact"
        }

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val company = binding.etCompany.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val notes = binding.etNotes.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (existingContact != null) {
                viewModel.update(
                    existingContact!!.copy(
                        name = name,
                        company = company,
                        phone = phone,
                        email = email,
                        notes = notes
                    )
                )
            } else {
                viewModel.insert(
                    Contact(
                        name = name,
                        company = company,
                        phone = phone,
                        email = email,
                        notes = notes
                    )
                )
            }
            finish()
        }

        binding.btnDelete.setOnClickListener {
            existingContact?.let {
                viewModel.delete(it)
                finish()
            }
        }
    }
}
