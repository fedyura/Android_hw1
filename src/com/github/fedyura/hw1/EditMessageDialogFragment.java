package com.github.fedyura.hw1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class EditMessageDialogFragment extends DialogFragment {
	
	public static String DialogMsg;
	public interface EditMessageDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
	
	EditMessageDialogListener mListener;
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.mp_dialog, null);
        builder.setView(inflater.inflate(R.layout.mp_dialog, null))
        	.setPositiveButton(R.string.DialogOK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // sign in the user ...
            		mListener.onDialogPositiveClick(EditMessageDialogFragment.this);
            		EditText ed = (EditText) dialogView.findViewById(R.id.editTextDialog);
            		DialogMsg = ed.getText().toString();
            	}
        	})
        	.setNegativeButton(R.string.DialogCancel, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                   //LoginDialogFragment.this.getDialog().cancel();
            	   mListener.onDialogNegativeClick(EditMessageDialogFragment.this);
               }
           });
        
        // Create the AlertDialog object and return it
        return builder.create();
    }
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (EditMessageDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
