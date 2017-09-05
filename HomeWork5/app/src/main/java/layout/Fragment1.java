package layout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nazir.homework5.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fragment1 extends Fragment {

    private static final String ARG_PARAM = "fragment_time";
    private static String mParam = "";

    private static TextView clockText;

    public static Fragment1 newInstance() {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        mParam = getCurrentTimeStr();
        args.putString(ARG_PARAM, mParam);
        fragment.setArguments(args);
        return fragment;
    }

    public static void UpdateTimeText() {
        mParam = getCurrentTimeStr();
        if (clockText != null)
            clockText.setText(mParam);
    }

    private static String getCurrentTimeStr() {

        Calendar calander = Calendar.getInstance();
        SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm:ss");
        return simpleDate.format(calander.getTime());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment1, container, false);

        clockText = (TextView) v.findViewById(R.id.textPage1);
        clockText.setText(mParam);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (clockText != null)
            clockText.setText(clockText.getText() + "1");

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
