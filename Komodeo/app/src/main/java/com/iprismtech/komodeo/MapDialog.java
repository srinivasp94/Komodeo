package com.iprismtech.komodeo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MapDialog extends DialogFragment {
    String message = "";
    private Context context;
    private FragmentManager fragmentManager;
    private Button btnCancel, btnDone;
    Button btnSchedule;
    TextView tvcancel, tvtest;


    //---empty constructor required


    /* public void setDialogTitle1(String pickup, String drop, UpdateAllDetails updateAllDetails, String sourcelat, String sourcelng, String droplat, String droplng, String vechileTypeID) {
         PICKUP = pickup;
         DROP = drop;
         this.sourcelat = sourcelat;
         this.sourcelng = sourcelng;
         this.droplat = droplat;
         this.updateAllDetails = updateAllDetails;
         this.droplng = droplng;
         this.vechileTypeID = vechileTypeID;
     }
 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.map_dialog, container);
        context = getActivity();


        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().setCancelable(true);
        return view;
    }


}

