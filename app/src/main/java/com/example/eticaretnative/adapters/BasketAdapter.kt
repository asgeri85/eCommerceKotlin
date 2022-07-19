package com.example.eticaretnative.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eticaretnative.databinding.CardBasketBinding
import com.example.eticaretnative.model.ProductResponseModel

class BasketAdapter : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    private val productList = arrayListOf<ProductResponseModel>()

    class BasketViewHolder(private val cardBasketBinding: CardBasketBinding) :
        RecyclerView.ViewHolder(cardBasketBinding.root) {
        fun bind(productResponseModel: ProductResponseModel) {
            cardBasketBinding.product = productResponseModel
            cardBasketBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val layout = CardBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketViewHolder(layout)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateList(list: List<ProductResponseModel>) {
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }
}