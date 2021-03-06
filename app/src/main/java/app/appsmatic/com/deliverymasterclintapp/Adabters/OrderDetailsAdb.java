package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.OrderDetailsItem;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import app.appsmatic.com.deliverymasterclintapp.URLS.BaseURL;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Mido PC on 3/20/2017.
 */
public class OrderDetailsAdb extends RecyclerView.Adapter<OrderDetailsAdb.Vh309> {

    private List<OrderDetailsItem> orderDetailsItems;
    private Context context;
    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";

    public OrderDetailsAdb(List<OrderDetailsItem> orderDetailsItems, Context context) {
        this.orderDetailsItems = orderDetailsItems;
        this.context = context;
    }

    @Override
    public Vh309 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Vh309(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_orders_details,parent,false));
    }

    @Override
    public void onBindViewHolder(Vh309 holder, int position) {

        animate(holder);
        holder.mealName.setText(orderDetailsItems.get(position).getName() + "");
        holder.mealCount.setText(orderDetailsItems.get(position).getQuantity() + "");
        holder.mealTotalPrice.setText(orderDetailsItems.get(position).getTotalPrice()+" "+context.getResources().getString(R.string.rs));
        holder.addtv.setVisibility(View.INVISIBLE);
        holder.custtv.setVisibility(View.INVISIBLE);

        //Encoding Img URL
        String url = Uri.encode(orderDetailsItems.get(position).getImage().toString(), ALLOWED_URI_CHARS);

        //Check Settings For Load images
        if(SaveSharedPreference.getImgLoadingSatatus(context)){
            Picasso.with(context)
                    .load(url)
                    .placeholder(R.drawable.rotat)
                    .fit()
                    .into(holder.mealpic);
        }else {

            Picasso.with(context)
                    .load(R.drawable.mealsplaceholder)
                    .fit()
                    .into(holder.mealpic);
        }

        if(getAllAdditions(orderDetailsItems.get(position)).isEmpty()){
            holder.addtv.setVisibility(View.INVISIBLE);
        }else {
            holder.addtv.setVisibility(View.VISIBLE);
        }
        if(getAllCustomizations(orderDetailsItems.get(position)).isEmpty()){
            holder.custtv.setVisibility(View.INVISIBLE);
        }else {
            holder.custtv.setVisibility(View.VISIBLE);
        }


        holder.mealAdd.setText(getAllAdditions(orderDetailsItems.get(position))+"");
        holder.mealCust.setText(getAllCustomizations(orderDetailsItems.get(position))+"");


    }

    @Override
    public int getItemCount() {
        return orderDetailsItems.size();
    }






// Calc total price
    public double sumTotall(){
        double x=0.0;
        for(int i=0;i<orderDetailsItems.size();i++){
            x=x+orderDetailsItems.get(i).getTotalPrice();
        }
        return x;
    }



    //collect all additions

    public String getAllAdditions (OrderDetailsItem orderDetails){
        String result = "";
        StringBuilder st = new StringBuilder();

        //collect additions
        if (orderDetails.getAdditions() != null) {
            for (int x = 0; x < orderDetails.getAdditions().size(); x++) {
                st.append(" # " + orderDetails.getAdditions().get(x).getAdditionName() + "  "
                        + orderDetails.getAdditions().get(x).getAdditionQuantity() + "  "
                        + orderDetails.getAdditions().get(x).getAdditionPrice() +" "+context.getResources().getString(R.string.rs)+ "\n");
            }
        }

        result = st.toString();
        return result;

    }

    public String getAllCustomizations (OrderDetailsItem orderDetails){
        String result = "";
        StringBuilder st = new StringBuilder();

        //Collect customizations
        if (orderDetails.getCustomization() != null) {
            for (int i = 0; i < orderDetails.getCustomization().size(); i++) {
                st.append(" # " + orderDetails.getCustomization().get(i).getCustomizationName() + "   "
                        + orderDetails.getCustomization().get(i).getCustomizationQuantity() + "   "
                        + orderDetails.getCustomization().get(i).getCustomizationPrice() + " " +context.getResources().getString(R.string.rs)+ "\n");
            }

        }

        result = st.toString();
        return result;

    }


















    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }


    public static class Vh309 extends RecyclerView.ViewHolder{
        private TextView mealName,mealCount,mealTotalPrice,mealAdd,mealCust,addtv,custtv;
        private CircleImageView mealpic;

        public Vh309(View itemView) {
            super(itemView);
            mealName=(TextView)itemView.findViewById(R.id.order_details_meal_name);
            mealCount=(TextView)itemView.findViewById(R.id.order_details_meal_count);
            mealTotalPrice=(TextView)itemView.findViewById(R.id.order_details_meal_price);
            mealAdd=(TextView)itemView.findViewById(R.id.order_details_meal_add_list);
            mealCust=(TextView)itemView.findViewById(R.id.order_details_meal_cust_list);
            addtv=(TextView)itemView.findViewById(R.id.order_details_add_tv);
            custtv=(TextView)itemView.findViewById(R.id.order_details_meal_cust_tv);

            mealpic=(CircleImageView)itemView.findViewById(R.id.order_details_meal_pic);
        }
    }


}
