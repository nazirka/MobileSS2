package layout;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nazir.homework5.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragment2 extends Fragment {

    private static final String ARG_PARAM = "fragment_date";
    private String mParam = "";

    TextView dateText;

    public static Fragment2 newInstance() {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, getCurrentDateStr());
        fragment.setArguments(args);
        return fragment;
    }

    private static String getCurrentDateStr() {
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd.MM.yyyy");
        return simpleDate.format(new Date());
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

        View v = inflater.inflate(R.layout.fragment_fragment2, container, false);
        dateText = (TextView) v.findViewById(R.id.textPage2);
        dateText.setText(mParam);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
