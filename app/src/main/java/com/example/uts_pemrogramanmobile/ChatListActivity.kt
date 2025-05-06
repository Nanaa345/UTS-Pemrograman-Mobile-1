package com.example.uts_pemrogramanmobile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ChatListActivity : AppCompatActivity() {

    private lateinit var etCari: EditText
    private lateinit var listViewChat: ListView
    private lateinit var tvDaftarKosong: TextView
    private lateinit var fabChatBaru: FloatingActionButton
    private lateinit var chatAdapter: ChatAdapter
    private var daftarChat = mutableListOf<ChatItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)

        etCari = findViewById(R.id.etSearch)
        listViewChat = findViewById(R.id.listViewChats)
        tvDaftarKosong = findViewById(R.id.tvEmptyList)
        fabChatBaru = findViewById(R.id.fabNewChat)

        isiDataChatContoh()

        chatAdapter = ChatAdapter(this, daftarChat)
        listViewChat.adapter = chatAdapter

        perbaruiTampilan()

        etCari.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterPercakapan(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        fabChatBaru.setOnClickListener {
            val idChatBaru = daftarChat.size + 1
            val chatBaru = ChatItem(
                id = idChatBaru,
                name = "Kontak Baru $idChatBaru",
                lastMessage = "Mulai mengobrol sekarang!",
                time = "Baru"
            )
            daftarChat.add(0, chatBaru)
            chatAdapter.notifyDataSetChanged()
            perbaruiTampilan()
        }
    }

    private fun isiDataChatContoh() {
        daftarChat.add(
            ChatItem(
                id = 1,
                name = "Clara",
                lastMessage = "Hai, clara aku mau nanya",
                time = "10:30"
            )
        )
        daftarChat.add(
            ChatItem(
                id = 2,
                name = "Siti",
                lastMessage = "Hai siti",
                time = "09:45"
            )
        )
        daftarChat.add(
            ChatItem(
                id = 3,
                name = "Lina",
                lastMessage = "Engga deh",
                time = "Kemarin"
            )
        )
        daftarChat.add(
            ChatItem(
                id = 4,
                name = "Prili",
                lastMessage = "Jangan lupa nanti kopdar",
                time = "Kemarin"
            )
        )
        daftarChat.add(
            ChatItem(
                id = 5,
                name = "Karin",
                lastMessage = "Kamu mau ikut acara besok?",
                time = "01/05"
            )
        )
    }

    private fun filterPercakapan(kataKunci: String) {
        val hasilFilter = mutableListOf<ChatItem>()
        if (kataKunci.isEmpty()) {
            hasilFilter.addAll(daftarChat)
        } else {
            for (chat in daftarChat) {
                if (chat.name.contains(kataKunci, ignoreCase = true) ||
                    chat.lastMessage.contains(kataKunci, ignoreCase = true)
                ) {
                    hasilFilter.add(chat)
                }
            }
        }

        chatAdapter = ChatAdapter(this, hasilFilter)
        listViewChat.adapter = chatAdapter

        if (hasilFilter.isEmpty()) {
            tvDaftarKosong.visibility = View.VISIBLE
            listViewChat.visibility = View.GONE
        } else {
            tvDaftarKosong.visibility = View.GONE
            listViewChat.visibility = View.VISIBLE
        }
    }

    private fun perbaruiTampilan() {
        if (daftarChat.isEmpty()) {
            tvDaftarKosong.visibility = View.VISIBLE
            listViewChat.visibility = View.GONE
        } else {
            tvDaftarKosong.visibility = View.GONE
            listViewChat.visibility = View.VISIBLE
        }
    }
}