package app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities;
import app.appsmatic.com.deliverymasterclintapp.API.Models.Msg;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResAdditions;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCats;
import app.appsmatic.com.deliverymasterclintapp.API.Models.ResMeals;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Mido PC on 12/4/2016.
 */
public interface ClintAppApi {



    //Sign Up method
    @POST("account/Register")
    Call<Msg> SignUp(@Body Object signup);

    //Login Method
    @POST("account/login")
    Call<Msg>Login(@Body Object loginData);

    //Categories Method
    @POST("menu/categories")
    Call<ResCats>GetCategories(@Body Object resId);

    @POST("menu/meals")
    Call<ResMeals>GetMeals(@Body Object catdata);

    @POST("menu/additions")
    Call<ResAdditions>GetAdditions(@Body Object mealData);



}