package app.appsmatic.com.deliverymasterclintapp.Activites;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;

public class ShoppingCart extends AppCompatActivity {

    private ImageView pickuBtn,deleviryBtn;
    private RecyclerView mealslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_shopping_cart);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        pickuBtn=(ImageView)findViewById(R.id.cart_pickup_btn);
        deleviryBtn=(ImageView)findViewById(R.id.cart_delvery_btn);
        mealslist=(RecyclerView)findViewById(R.id.cart_meals_list);


        //Set image language for pickup and delivery button
        if(SaveSharedPreference.getLangId(this).equals("ar")){
           pickuBtn.setImageResource(R.drawable.cart_pickup_btn_arabic);
           deleviryBtn.setImageResource(R.drawable.cart_delivery_btn_arabic);
        }else{
            pickuBtn.setImageResource(R.drawable.cart_picup_btn_english);
            deleviryBtn.setImageResource(R.drawable.cart_delivery_btn_english);
        }






    }
}