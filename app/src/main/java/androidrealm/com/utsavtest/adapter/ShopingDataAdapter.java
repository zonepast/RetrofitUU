package androidrealm.com.utsavtest.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidrealm.com.utsavtest.R;
import androidrealm.com.utsavtest.model.Dataresponse;

/**
 * Created by AFF41 on 7/11/2017.
 */

public class ShopingDataAdapter extends RecyclerView.Adapter<ShopingDataAdapter.ShopingDataHolder> {
    private ArrayList<Dataresponse> dataresponseArrayList = new ArrayList<>();
    private Context context;

    public ShopingDataAdapter(ArrayList<Dataresponse> dataresponseArrayList, Context context) {
        this.dataresponseArrayList = dataresponseArrayList;
        this.context = context;
    }

    @Override
    public ShopingDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_data, parent, false);
        return new ShopingDataHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopingDataHolder holder, int position) {
        Dataresponse dataresponse=dataresponseArrayList.get(position);
        holder.tvNumber.setText(dataresponse.getProdNum());
        holder.tvName.setText(dataresponse.getProdname());
        Picasso.with(context).load("http://www.silvergiftz.com/images/product/"+dataresponse.getProdImage()).into(holder.ivProduct);

    }

    @Override
    public int getItemCount() {
        return dataresponseArrayList.size();
    }

    public class ShopingDataHolder extends RecyclerView.ViewHolder {
        AppCompatImageView ivProduct;
        AppCompatTextView tvName, tvNumber;

        public ShopingDataHolder(View itemView) {
            super(itemView);
            ivProduct = (AppCompatImageView) itemView.findViewById(R.id.iv_product);
            tvName = (AppCompatTextView) itemView.findViewById(R.id.tv_product_name);
            tvNumber = (AppCompatTextView) itemView.findViewById(R.id.tv_product_number);
        }
    }
}
