package com.example.afinal;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link learnpdfFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class learnpdfFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    PDFView pdfView;
    int pos;
    String pdfurl;
    ImageButton button_back;
    learnPdf learnpdf;
    public static String[] lesson_slides = {
            //lesson 16
            "https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-046j-design-and-analysis-of-algorithms-spring-2015/lecture-notes/MIT6_046JS15_lec16.pdf",
            //lesson 17
            "https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-046j-design-and-analysis-of-algorithms-spring-2015/lecture-notes/MIT6_046JS15_lec17.pdf",
            //lesson 18
            "https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-046j-design-and-analysis-of-algorithms-spring-2015/lecture-notes/MIT6_046JS15_lec18.pdf",
            //problem pdf
            "https://people.eecs.berkeley.edu/~vazirani/algorithms/chap8.pdf"
    };

    public learnpdfFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment learnpdfFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static learnpdfFragment newInstance(String param1, String param2) {
        learnpdfFragment fragment = new learnpdfFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView =
                inflater.inflate(R.layout.fragment_learnpdf,
                        container,
                        false);
        Bundle bundle = getArguments();
        if (bundle!=null){
            pos = bundle.getInt("pos");
        }

        pdfView = (PDFView) rootView.findViewById(R.id.idPDFView);
        button_back = (ImageButton) rootView.findViewById(R.id.goBackVideoFragment);
        if (pos == 0)
            pdfurl = lesson_slides[0];
        else if (pos == 1)
            pdfurl = lesson_slides[1];
        else if (pos == 2)
            pdfurl = lesson_slides[2];
        else
            pdfurl = lesson_slides[3];

        new RetrivePDFfromUrl().execute(pdfurl);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learnpdf.onBackPdfItemSelected();
            }
        });

        return rootView;
    }

    public interface learnPdf{
        public void onBackPdfItemSelected();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof learnpdfFragment.learnPdf) {
            learnpdf = (learnpdfFragment.learnPdf) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }
    class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            pdfView.fromStream(inputStream).load();
        }
    }
}