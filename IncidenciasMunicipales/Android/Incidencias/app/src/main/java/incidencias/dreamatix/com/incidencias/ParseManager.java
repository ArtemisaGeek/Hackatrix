package incidencias.dreamatix.com.incidencias;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

/**
 * Created by Taller on 23/05/2015.
 */
public class ParseManager {
    private static ParseManager mInstance = null;
    private Context mContext;
     private ParseManager(Context mContext){
         Parse.enableLocalDatastore(mContext);
         android.util.Log.w("Starting","ST");
         Parse.initialize(mContext, "8K06at8vbjrsqQUjKekQUm4GKoEMSLJrPnyFlXue", "wyfahmViD1M8VFBbvDxbdV8qAhIwpjxAThh8jcey");

     }
    public static ParseManager getInstance(Context mContext){
        if(mInstance == null){
            mInstance = new ParseManager(mContext);
        }
        return mInstance;
    }

    public void sendToParse(String dni,String description,byte[] image,double latitude,double longitude){
        ParseObject testObject = new ParseObject("Incidencias");
        testObject.put("dni", dni);
        testObject.put("descripcion", description);
        testObject.put("coordenadas", new ParseGeoPoint(latitude, longitude));
        testObject.put("foto",new ParseFile("image.png",image));
        testObject.saveInBackground();
    }
}
