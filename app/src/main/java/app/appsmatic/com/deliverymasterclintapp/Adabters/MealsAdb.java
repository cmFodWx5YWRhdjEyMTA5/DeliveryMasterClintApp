package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResMeals;
import app.appsmatic.com.deliverymasterclintapp.Activites.Customization;
import app.appsmatic.com.deliverymasterclintapp.Activites.PickUpServ;
import app.appsmatic.com.deliverymasterclintapp.URLS.BaseURL;
import app.appsmatic.com.deliverymasterclintapp.R;

/**
 * Created by Mido PC on 12/19/2016.
 */
public class MealsAdb extends RecyclerView.Adapter<MealsAdb.vh2> {

    private ResMeals meals;
    private Context context;
    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";

    public MealsAdb(Context context, ResMeals meals) {
        this.context = context;
        this.meals = meals;
    }


    @Override
    public vh2 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new vh2(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_meal,parent,false));
    }

    @Override
    public void onBindViewHolder(vh2 holder, final int position) {
        holder.title.setText(meals.getMessage().get(position).getName()+"");
        holder.details.setText(meals.getMessage().get(position).getDescription()+"");
        holder.price.setText(meals.getMessage().get(position).getPrice()+" SR");

        //Encoding Img URL
        String url = Uri.encode(BaseURL.IMGS+meals.getMessage().get(position).getImagePreview().toString(), ALLOWED_URI_CHARS);


        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.rotat)
                .fit()
                .into(holder.img);



        //Customize order button
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, PickUpServ.class)
                        .putExtra("price", meals.getMessage().get(position).getPrice() + "")
                        .putExtra("mealId", meals.getMessage().get(position).getID() + ""));

            }
        });

    }

    @Override
    public int getItemCount() {
        return meals.getMessage().size();
    }



    public static class vh2 extends RecyclerView.ViewHolder{
        private TextView title,details,price;
        private ImageView img,btn;
        public vh2(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.tv_meal_home_mealname);
            details=(TextView)itemView.findViewById(R.id.tv_meal_home_maildetails);
            price=(TextView)itemView.findViewById(R.id.tv_meal_home_price);
            img=(ImageView)itemView.findViewById(R.id.meal_home_mealpic);
            btn=(ImageView)itemView.findViewById(R.id.addmeal_home_meals_btn);
        }
    }
}
