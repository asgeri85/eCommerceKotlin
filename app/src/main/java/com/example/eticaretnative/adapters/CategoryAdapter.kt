package com.example.eticaretnative.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eticaretnative.databinding.CardCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categoryList = arrayListOf<String>()
    var onClick: (String) -> Unit = {}

    class CategoryViewHolder(private val cardCategoryBinding: CardCategoryBinding) :
        RecyclerView.ViewHolder(cardCategoryBinding.root) {
        fun bind(category: String, onClick: (String) -> Unit = {}) {
            cardCategoryBinding.category = category
            cardCategoryBinding.executePendingBindings()

            cardCategoryBinding.cardCategory.setOnClickListener {
                onClick(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val categoryLayout =
            CardCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(categoryLayout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position], onClick)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun updateList(list: List<String>) {
        categoryList.clear()
        categoryList.addAll(list)
        notifyDataSetChanged()
    }
}