package tn.esprit.espritclubs.fragments.bacem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.database.AppDatabase;
import tn.esprit.espritclubs.entities.Message;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MessagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessagesFragment newInstance(String param1, String param2) {
        MessagesFragment fragment = new MessagesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private TextView quoteText;
    private Button generateQuoteButton;
    private ArrayList<String> quotes;
    private Random random;
    private Button button;
    private AppDatabase database;
    private EditText editText;

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
        View view = inflater.inflate(tn.esprit.espritclubs.R.layout.fragment_message, container, false);
        // Initialize views
        quoteText = view.findViewById(R.id.quoteText);
        generateQuoteButton = view.findViewById(R.id.generateQuoteButton);
        button = view.findViewById(R.id.sendButton);
        editText = view.findViewById(R.id.messageInput);
        // Initialize the quotes list
        quotes = new ArrayList<>();
        quotes.add("The best way to get started is to quit talking and begin doing.");
        quotes.add("Success is not final; failure is not fatal: It is the courage to continue that counts.");
        quotes.add("Don't watch the clock; do what it does. Keep going.");
        quotes.add("Act as if what you do makes a difference. It does.");

        // Initialize Random object
        random = new Random();

        // Set onClickListener for generateQuoteButton
        generateQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate a random quote and display it
                String randomQuote = getRandomQuote();
                quoteText.setText(randomQuote);
            }
        });

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                database = AppDatabase.getDatabase(getContext());

                database.messageDao().insertTMessage(new Message(editText.getText().toString()));
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    private String getRandomQuote() {
        int index = random.nextInt(quotes.size());
        return quotes.get(index);
    }
}