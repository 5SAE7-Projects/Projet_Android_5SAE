package tn.esprit.espritclubs.activities.bacemActivities;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import tn.esprit.espritclubs.OnDialogCloseListener;
import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.activities.bacemActivities.Adapter.ToDoAdapter;
import tn.esprit.espritclubs.dao.TaskDao;
import tn.esprit.espritclubs.database.AppDatabase;
import tn.esprit.espritclubs.entities.Task;

public class AddNewTask extends BottomSheetDialogFragment {
    public static final String TAG = "AddNewTask";

    private EditText mEditText;
    private CheckBox checkBox;
    private Button mSaveButton;
    private AppDatabase database;
    private ToDoAdapter adapter;

    public static AddNewTask newInstance() {
        return new AddNewTask();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_add_new_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditText = view.findViewById(R.id.editText);
        mSaveButton = view.findViewById(R.id.addButton1);
        checkBox = view.findViewById(R.id.checkbox);

        Bundle bundle = getArguments();
        boolean isUpdate = bundle != null && bundle.containsKey("task");
        if (isUpdate) {
            String task = bundle.getString("task", "");
            mEditText.setText(task);
            mSaveButton.setEnabled(!task.isEmpty());
            if (task.isEmpty()) {
                mSaveButton.setBackgroundColor(Color.GRAY);
            }
        } else {
            mSaveButton.setEnabled(false);
            mSaveButton.setBackgroundColor(Color.GRAY);
        }

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().isEmpty()) {
                    mSaveButton.setEnabled(false);
                    mSaveButton.setBackgroundColor(Color.GRAY);
                } else {
                    mSaveButton.setEnabled(true);
                    //mSaveButton.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary)); // Set to your desired color
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getArguments();
                boolean isUpdate = bundle != null && bundle.containsKey("task");
                database = AppDatabase.getDatabase(getContext());
                TaskDao taskDao = database.taskDao();
                if(mEditText.getText().toString().isEmpty())
                    Toast.makeText(getContext(), "Please enter a valid Task!", Toast.LENGTH_SHORT).show();
//                if(isUpdate){
//                    update
//                }
//
//                else{
                taskDao.insertTask(new Task(mEditText.getText().toString(),0));
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if (activity instanceof OnDialogCloseListener) {
            ((OnDialogCloseListener) activity).onDialogClose(dialog);
        }
    }
}
