package com.bluewhale.toolslibrary;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by pc1 on 2017/12/8.
 * Company szboanda
 * Introductions
 */

public class ViewTool {

    public static void startActivity(Activity activity,Class className,boolean isColose ){
        activity.startActivity(initIntent(activity,className));
        if(isColose){
            activity.finish();
        }
    }

    public static void startActivityForResult(Activity activity,Class className,boolean isColose,int result ){
        activity.startActivityForResult(initIntent(activity,className),result);
        if(isColose){
            activity.finish();
        }
    }

    private static Intent initIntent(Activity activity,Class className){
        Intent intent = new Intent(activity,className);
        return intent;
    }
}
