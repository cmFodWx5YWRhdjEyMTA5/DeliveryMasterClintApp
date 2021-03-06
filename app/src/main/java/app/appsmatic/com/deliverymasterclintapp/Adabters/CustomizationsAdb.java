package app.appsmatic.com.deliverymasterclintapp.Adabters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.API.Models.CustomizationM;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCustomizations;
import app.appsmatic.com.deliverymasterclintapp.Activites.Home;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.MealAddition;
import app.appsmatic.com.deliverymasterclintapp.CartStructure.MealCustomization;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;

/**
 * Created by Mido PC on 2/8/2017.
 */
public class CustomizationsAdb extends RecyclerView.Adapter<CustomizationsAdb.VH500> {
    private List<CustomizationM>customizationMs;
    private Context context;
    public static List<MealCustomization> mealCustomizations =new ArrayList<>();
    private List<Integer>counts=new ArrayList<>();
    private List<Boolean>selected=new ArrayList<>();
    private RadioButton lastCheckedRB = null;

    public CustomizationsAdb(Context context, List<CustomizationM> customizationMs) {
        this.context = context;
        this.customizationMs = customizationMs;
    }





    @Override
    public VH500 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH500(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_castumizations_rb,parent,false));
    }

    @Override
    public void onBindViewHolder(final VH500 holder, final int position) {


        //select one item only
        View.OnClickListener rbClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checked_rb = (RadioButton) v;
                if(lastCheckedRB != null){
                    lastCheckedRB.setChecked(false);
                }
                lastCheckedRB = checked_rb;
            }
        };
        holder.selectbutton.setOnClickListener(rbClick);

        animate(holder);
        holder.custName.setText(customizationMs.get(position).getCname());
        holder.price.setText(customizationMs.get(position).getCprice() + " " + context.getResources().getString(R.string.rs) + " ");

        //set first item in counts list
        counts.add(position, 0);



        //when button not checked put count 0
        holder.selectbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                counts.set(position, 0);
               // holder.count.setText("" + counts.get(position));
                selected.set(position, true);
                if (counts.get(position) == 0) {
                    mealCustomizations.set(position, new MealCustomization());
                } else {

                    //fill Customizations list with additions and counts
                    MealCustomization mealCustomization = new MealCustomization();
                    mealCustomization.setCustomizationName(customizationMs.get(position).getCname() + "");
                    mealCustomization.setCustomizationPrice(customizationMs.get(position).getCprice());
                    mealCustomization.setCustomizationCount(counts.get(position));
                    mealCustomization.setiD(customizationMs.get(position).getCiD());
                    mealCustomizations.set(position, mealCustomization);

                }
            }else {

                    counts.set(position, 1);
                   // holder.count.setText("" + counts.get(position));
                    selected.set(position, true);
                    if (counts.get(position) == 0) {
                        mealCustomizations.set(position, new MealCustomization());
                    } else {

                        //fill Customizations list with additions and counts
                        MealCustomization mealCustomization = new MealCustomization();
                        mealCustomization.setCustomizationName(customizationMs.get(position).getCname() + "");
                        mealCustomization.setCustomizationPrice(customizationMs.get(position).getCprice());
                        mealCustomization.setCustomizationCount(counts.get(position));
                        mealCustomization.setiD(customizationMs.get(position).getCiD());
                        mealCustomizations.set(position, mealCustomization);


                    }
                }





            }
        });











        //Fill mealCustomizations List with default values
        for (int i=0;i<customizationMs.size();i++){
            mealCustomizations.add(position,new MealCustomization());
            selected.add(position,false);
        }

        /*
        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.selectbutton.isChecked()){

                counts.set(position, counts.get(position) + 1);
                holder.count.setText("" + counts.get(position));
                selected.set(position, true);

                if (counts.get(position) == 0) {

                    mealCustomizations.set(position, new MealCustomization());
                    holder.selectbutton.setChecked(false);

                } else {

                    //fill additions list with additions and counts
                    MealCustomization mealCustomization = new MealCustomization();
                    mealCustomization.setCustomizationName(customizationMs.get(position).getCname() + "");
                    mealCustomization.setCustomizationPrice(customizationMs.get(position).getCprice());
                    mealCustomization.setCustomizationCount(counts.get(position));
                    mealCustomization.setiD(customizationMs.get(position).getCiD());
                    mealCustomizations.set(position, mealCustomization);

                   }
                }
            }
        });

*/

        /*
        //Decrement Additions Count
        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (counts.get(position) == 0)
                    return;
                counts.set(position, counts.get(position) - 1);
                holder.count.setText("" + counts.get(position));

                if (counts.get(position) == 0) {

                    mealCustomizations.set(position, new MealCustomization());
                    holder.selectbutton.setChecked(false);

                }else{
                    MealCustomization mealCustomization = new MealCustomization();
                    mealCustomization.setCustomizationName(customizationMs.get(position).getCname() + "");
                    mealCustomization.setCustomizationPrice(customizationMs.get(position).getCprice());
                    mealCustomization.setCustomizationCount(counts.get(position));
                    mealCustomizations.set(position, mealCustomization);

                }

            }
        });



*/




        ///////////////////////////////////////////////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>






    }

    @Override
    public int getItemCount() {
        return customizationMs.size();
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    public static class VH500 extends RecyclerView.ViewHolder{

        private TextView custName,price,count;
        private ImageView up,down;
        private RadioButton selectbutton;

        public VH500(View itemView) {
            super(itemView);

            //up=(ImageView)itemView.findViewById(R.id.add_up_btn);
            //down=(ImageView)itemView.findViewById(R.id.add_down_btn);
            custName=(TextView)itemView.findViewById(R.id.add_tv_name);
            price=(TextView)itemView.findViewById(R.id.add_tv_prive);
           // count=(TextView)itemView.findViewById(R.id.value_tv);
            selectbutton=(RadioButton)itemView.findViewById(R.id.cust_rdiobutton);



        }
    }




}
