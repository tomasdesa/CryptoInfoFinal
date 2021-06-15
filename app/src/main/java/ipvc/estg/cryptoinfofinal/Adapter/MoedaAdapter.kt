package ipvc.estg.cryptoinfofinal.Adapter

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader
import com.squareup.picasso.Picasso
import ipvc.estg.cryptoinfofinal.APIMarket.Moeda
import ipvc.estg.cryptoinfofinal.R


class MoedaAdapter(val moeda: List<Moeda>,var activity: Activity, var clickListener: OnMoedaClickListener) : RecyclerView.Adapter<MoedaAdapter.MoedaViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): MoedaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coin_layout, parent, false)
        return MoedaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moeda.size
    }

    override fun onBindViewHolder(holder: MoedaViewHolder, position: Int) {
        return holder.bind(moeda[position],clickListener,activity)
    }

    class MoedaViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.coinName)
        private val symbol : TextView = itemView.findViewById(R.id.coinSymbol)
        private val price: TextView = itemView.findViewById(R.id.priceUsd)
        private val h1: TextView = itemView.findViewById(R.id.oneDay)
        private val h24: TextView = itemView.findViewById(R.id.oneday)
        private val d7: TextView = itemView.findViewById(R.id.sevenDay)
        private val coinIcon: ImageView = itemView.findViewById(R.id.coinIcon)

        fun bind(moeda: Moeda, action: OnMoedaClickListener, Activity:Activity) {
            name.text = moeda.name
            symbol.text =moeda.symbol
            price.text=moeda.price.toString()
            h1.text=Math.round((moeda.h1.price_change_pct)*100 ).toString()
            h24.text=Math.round((moeda.d1.price_change_pct)*100).toString()
            d7.text=Math.round((moeda.d7.price_change_pct)*100).toString()

            if(moeda.logo_url.contains(".svg")){
            SvgLoader.pluck()
                .with(Activity)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(moeda.logo_url, coinIcon);
                }
            else if (moeda.logo_url.contains(".png")){
                Picasso.get().load(moeda.logo_url).into(coinIcon);
            }


            h1.setTextColor(if(moeda.h1.price_change_pct.toString()!!.contains("-"))
                Color.parseColor("#FF0000")
            else
                Color.parseColor("#32CD32")
            )

            h24.setTextColor(if(moeda.d1.price_change_pct.toString()!!.contains("-"))
                Color.parseColor("#FF0000")
            else
                Color.parseColor("#32CD32")
            )

            d7.setTextColor(if(moeda.d7.price_change_pct.toString()!!.contains("-"))
                Color.parseColor("#FF0000")
            else
                Color.parseColor("#32CD32")
            )

            itemView.setOnClickListener{
                action.onMoedaClick(moeda, adapterPosition)
            }
        }


    }

    interface OnMoedaClickListener{
        fun onMoedaClick(moeda: Moeda, position: Int)
    }

}


