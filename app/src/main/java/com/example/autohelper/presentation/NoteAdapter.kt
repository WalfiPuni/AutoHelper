package com.example.autohelper.presentation

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.autohelper.R
import com.example.autohelper.data.notes.room.entities.NoteEntity
import com.example.autohelper.databinding.NoteListItemBinding

class NoteAdapter(private val listener: Listener): ListAdapter<NoteEntity, NoteAdapter.ItemHolder>(Comparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

    class ItemHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = NoteListItemBinding.bind(view)

        fun setData(note: NoteEntity, listener: Listener) = with(binding){
            tvTitle.text = note.nameDetail
            tvMileage.text = note.mileage.toString()
            itemView.setOnClickListener{
                listener.onClickItem(note)
            }
            btnDelete.setOnClickListener{
                listener.deleteItem(note.id!!)
            }
        }

        companion object{
            fun create(parent: ViewGroup): ItemHolder{
                return ItemHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.note_list_item, parent, false))
            }
        }

    }


    class Comparator: DiffUtil.ItemCallback<NoteEntity>(){
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }

    }


    interface Listener{
        fun deleteItem(id: Int)
        fun onClickItem(note: NoteEntity)
    }
}