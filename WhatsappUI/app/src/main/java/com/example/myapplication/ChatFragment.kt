package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    lateinit var binding:FragmentChatBinding
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapter: ChatAdapter

    var imageList = ArrayList<ChatItemModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)
        addImages()
        setUpRecyclerView()
        adapter.differ.submitList(imageList)
    }

    private fun addImages() {
        val img1 = ChatItemModel("Arshath","Howr are you?","19:36",R.drawable.aa)
        imageList.add(img1)
        val img2 = ChatItemModel("Rafiq","Whatsup","18:15",R.drawable.bb)
        imageList.add(img2)
        val img3 = ChatItemModel("Farith","Dude","16:16",R.drawable.cc)
        imageList.add(img3)
        val img4 = ChatItemModel("Maheera","Forgot that","11:35",R.drawable.dd)
        imageList.add(img4)
        val img5 = ChatItemModel("Safa","Happy birthday!!","07:17",R.drawable.ee)
        imageList.add(img5)
        val img6 = ChatItemModel("Aadhil","What doing?","16:16",R.drawable.ff)
        imageList.add(img6)
        val img7 = ChatItemModel("Fareena","Still sleeping..","19:36",R.drawable.gg)
        imageList.add(img7)
        val img8 = ChatItemModel("Tara","Triggered rofl","16:16",R.drawable.hh)
        imageList.add(img8)
        val img9 = ChatItemModel("Azlan","had dinner?","19:36",R.drawable.ii)
        imageList.add(img9)
        val img10 = ChatItemModel("Hafsa","I am fine,how are you","16:16",R.drawable.aa)
        imageList.add(img10)

    }

    private fun setUpRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this.requireContext())
        adapter = ChatAdapter(this.requireContext())
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.chatRecyclerView.layoutManager = linearLayoutManager
        binding.chatRecyclerView.adapter = adapter
    }

}