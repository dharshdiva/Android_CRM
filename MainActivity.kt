package com.example.crmapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crmapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ContactViewModel by viewModels()
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ContactAdapter(
            onItemClick = { contact ->
                val intent = Intent(this, AddEditContactActivity::class.java)
                intent.putExtra("contact_id", contact.id)
                startActivity(intent)
            },
            onDeleteClick = { contact ->
                viewModel.delete(contact)
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.allContacts.observe(this) { contacts ->
            adapter.submitList(contacts)
            binding.tvEmpty.visibility =
                if (contacts.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddEditContactActivity::class.java))
        }
    }
}
