package com.example.eticaretnative.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eticaretnative.databinding.CardProductBinding
import com.example.eticaretnative.model.ProductResponseModel

class ProductAdapters : RecyclerView.Adapter<ProductAdapters.PopularCardViewHolder>() {

    private val productList = arrayListOf<ProductResponseModel>()
    var onClick: (ProductResponseModel) -> Unit = {}

    class PopularCardViewHolder(private val cardProductBinding: CardProductBinding) :
        RecyclerView.ViewHolder(cardProductBinding.root) {
        fun bind(
            productResponseModel: ProductResponseModel,
            onClick: (ProductResponseModel) -> Unit = {},
        ) {
            cardProductBinding.product = productResponseModel
            cardProductBinding.executePendingBindings()

            cardProductBinding.cardProduct.setOnClickListener {
                onClick(productResponseModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularCardViewHolder {
        val cardLayout =
            CardProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularCardViewHolder(cardLayout)
    }

    override fun onBindViewHolder(holder: PopularCardViewHolder, position: Int) {
        holder.bind(productList[position], onClick)
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