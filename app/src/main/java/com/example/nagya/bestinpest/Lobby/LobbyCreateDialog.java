package com.example.nagya.bestinpest.Lobby;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nagya.bestinpest.network.RouteNetwork.item.JunctionRestItem;
import com.example.nagya.bestinpest.Lobby.item.Leader;
import com.example.nagya.bestinpest.Lobby.item.LobbyCreatingPOST;
import com.example.nagya.bestinpest.MainMenuActivity;
import com.example.nagya.bestinpest.R;
import com.example.nagya.bestinpest.network.RouteNetwork.item.JunctionsWrapper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by nagya on 27/02/2018.
 */

public class LobbyCreateDialog  extends DialogFragment {

    EditText lobbyName;
    EditText lobbyPassword;
    TextView lobbyPlayerNumber;
    Button plusBtn;
    Button minusBtn;
    ImageButton sendGPSforJunction;
    MainMenuActivity parent;
    Spinner junctionSpiner;
    EditText usernameEditText;

    List<JunctionRestItem> junctionRestItems;

    public LobbyCreateDialog setParent(MainMenuActivity parent){
        this.parent= parent;
        return this;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.MyAlertDialogStyle);
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_lobbycreate, null);
        lobbyName = view.findViewById(R.id.CreateLobbyNameEditText);
        sendGPSforJunction= view.findViewById(R.id.CreateLobby_GPSButton);
        sendGPSforJunction.setImageResource(R.drawable.ic_my_location_black_24dp);
        sendGPSforJunction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent.sendGPSFreeAll();
            }
        });

        lobbyPassword = view.findViewById(R.id.CreateLobbyPasswordEditText);
        lobbyPlayerNumber =view.findViewById(R.id.CreateLobbyPlayerNumber);
        plusBtn = view.findViewById(R.id.CreateLobbyBtnPlus);
        minusBtn = view.findViewById(R.id.CreateLobbyBtnMinus);
        junctionSpiner = view.findViewById(R.id.CreateLobby_StartSpiner);
        usernameEditText = view.findViewById(R.id.CreateLobby_UsernameEditText);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int playerNumber = Integer.parseInt(lobbyPlayerNumber.getText().toString());
                if(playerNumber<6){
                    playerNumber++;
                    lobbyPlayerNumber.setText(playerNumber+"");
                }
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int playerNumber = Integer.parseInt(lobbyPlayerNumber.getText().toString());
                if(playerNumber>3){
                    playerNumber--;
                    lobbyPlayerNumber.setText(playerNumber+"");
                }
            }
        });



        builder.setTitle(R.string.create_lobby_title)
                .setView(view)
                .setPositiveButton(R.string.createBtn_text, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(junctionSpiner.getSelectedItem()!=null && lobbyName.getText()!=null && usernameEditText.getText()!=null) {
                            JunctionRestItem selectedJunction = (JunctionRestItem) junctionSpiner.getSelectedItem();
                            parent.createThisLobby(new LobbyCreatingPOST(
                                    Integer.parseInt(lobbyPlayerNumber.getText().toString()),
                                    lobbyName.getText().toString(),
                                    readPassword(),
                                    new Leader(selectedJunction.getId(), usernameEditText.getText().toString())));
                        }
                        else {
                            Toast.makeText(getContext(),"Set everything first!", Toast.LENGTH_LONG).show();
                        }
                       }
                })
                .setNegativeButton(R.string.cancelBtn_text, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });



        return builder.create();
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {

        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onJunctions(JunctionsWrapper junctionsWrapper) {
        setJunctions(junctionsWrapper.junctions);
    }

    public void setJunctions(List<JunctionRestItem> junctions){
        junctionRestItems= junctions;
        if(junctionSpiner!=null) {
            ArrayAdapter<JunctionRestItem> junctionRestItemArrayAdapter = new ArrayAdapter<JunctionRestItem>(getContext(), R.layout.item_pass_junction, junctionRestItems);
            junctionRestItemArrayAdapter.setDropDownViewResource(R.layout.item_pass_junction);
            junctionSpiner.setAdapter(junctionRestItemArrayAdapter);
        }

    }

    private String readPassword(){
        if (lobbyPassword.getText().length()!=0)
        return lobbyPassword.getText().toString();
        else return null;
    }

    public interface createLobby{
        public void createThisLobby(LobbyCreatingPOST lobbyCreatingPOST);
    }
}

