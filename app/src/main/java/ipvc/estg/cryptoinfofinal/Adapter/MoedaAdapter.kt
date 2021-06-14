package ipvc.estg.cryptoinfofinal.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.cryptoinfofinal.APIMarket.Moeda
import ipvc.estg.cryptoinfofinal.R


class MoedaAdapter(val moeda: List<Moeda>, var clickListener: OnMoedaClickListener) : RecyclerView.Adapter<MoedaAdapter.MoedaViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): MoedaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coin_layout, parent, false)
        return MoedaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moeda.size
    }

    override fun onBindViewHolder(holder: MoedaViewHolder, position: Int) {
        return holder.bind(moeda[position],clickListener)
    }

    class MoedaViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.coinName)
        private val symbol : TextView = itemView.findViewById(R.id.coinSymbol)
        private val price: TextView = itemView.findViewById(R.id.priceUsd)
        private val h1: TextView = itemView.findViewById(R.id.oneHour)
        private val h24: TextView = itemView.findViewById(R.id.twentyFourHour)
        private val d7: TextView = itemView.findViewById(R.id.sevenDay)

        fun bind(moeda: Moeda, action: OnMoedaClickListener) {
            name.text = moeda.name
            symbol.text =moeda.symbol

            itemView.setOnClickListener{
                action.onMoedaClick(moeda, adapterPosition)
            }
        }


    }

    interface OnMoedaClickListener{
        fun onMoedaClick(moeda: Moeda, position: Int)
    }

}


