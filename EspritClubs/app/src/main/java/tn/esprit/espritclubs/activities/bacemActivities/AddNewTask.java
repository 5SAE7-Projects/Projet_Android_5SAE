package tn.esprit.espritclubs.activities.bacemActivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import tn.esprit.espritclubs.R;

public class AddNewTask extends BottomSheetDialogFragment {
    private static final String TAG = "AddNewTask";

    private EditText mEditText;
    private Button mSaveButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(tn.esprit.espritclubs.R.layout.add_new_task,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEditText = view.findViewById(R.id.editText);
        mSaveButton = view.findViewById(R.id.addButton);
        boolean isUpdate = false;
        Bundle bundle = getArguments();
        if(bundle != null){
            isUpdate = true;
            String task = bundle.getString("task");
            mEditText.setText(task);
            if(task.length()>0){
                mSaveButton.setEnabled(false);
            }

        }
    }
}
