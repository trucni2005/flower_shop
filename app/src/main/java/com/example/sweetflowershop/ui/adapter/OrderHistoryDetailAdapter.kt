import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.order.OrderDetailHistory
import com.example.sweetflowershop.network.apiService._Constant
import com.squareup.picasso.Picasso

class OrderHistoryDetailAdapter(
    private var orderDetailHistoryList: List<OrderDetailHistory> = mutableListOf(),
) : RecyclerView.Adapter<OrderHistoryDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderDetailHistoryItem = orderDetailHistoryList[position]
        holder.bind(orderDetailHistoryItem)
    }

    override fun getItemCount(): Int {
        return orderDetailHistoryList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_ohi_name)
        val tvItemPrice: TextView = itemView.findViewById(R.id.tv_ohi_item_price)
        val tvItemQuantity: TextView = itemView.findViewById(R.id.tv_ohi_item_quantity)
        val imageUrl: ImageView = itemView.findViewById(R.id.ohi_image_url)

        fun bind(orderDetailHistoryItem: OrderDetailHistory) {
            tvName.text = orderDetailHistoryItem.nameProduct
            tvItemPrice.text = orderDetailHistoryItem.priceProduct.toString()+"đ"
            tvItemQuantity.text = "Số lượng: "+orderDetailHistoryItem.quantity.toString()
            val url = _Constant.baseUrl_ + "images/product/${orderDetailHistoryItem.image}"
            Picasso.get().load(url).into(imageUrl)
        }
    }

    fun updateOrderDetailHistoryList(newOrderDetailHistoryList: List<OrderDetailHistory>) {
        orderDetailHistoryList = newOrderDetailHistoryList
        notifyDataSetChanged()
    }
}
