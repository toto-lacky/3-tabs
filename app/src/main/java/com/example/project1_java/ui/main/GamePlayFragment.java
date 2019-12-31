package com.example.project1_java.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project1_java.R;

import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class GamePlayFragment extends Fragment{

    //swipe 정책 결정
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 800;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private static int BLOCK_SIZE;

    private int board[][] = new int[4][4];  //보드판 배치 배열

    static GamePlayFragment newInstance() {
        return new GamePlayFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_play, container, false);
        RelativeLayout rl = view.findViewById(R.id.relativeLayout);

        final GestureDetector gd = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
                if(Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;

                //right to left
                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
                    Toast.makeText(getContext(), "Left Swipe", Toast.LENGTH_SHORT).show();
                    Log.d("MyLog","left swipe");
                }
                //left to right
                else if(e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
                    Toast.makeText(getContext(), "right Swipe", Toast.LENGTH_SHORT).show();
                }
                //down to up
                else if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY){
                    Toast.makeText(getContext(), "up Swipe", Toast.LENGTH_SHORT).show();
                }
                //up to down
                else if(e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY){
                    Toast.makeText(getContext(), "down Swipe", Toast.LENGTH_SHORT).show();
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

        rl.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                gd.onTouchEvent(motionEvent);
                return true;
            }
        });

        return view;
    }

    //보드판에 랜덤 배치하기
    public void initboard(){
        int tmp;
        int index_r;
        int board_[] = new int[16];
        int i, j, k;
        Random r = new Random();

        for(i = 0; i <16; i++){
            board_[i] = i;
        }
        for(i = 15 ; i>0; i--){
            index_r = r.nextInt(i-1);
            tmp = board_[i];
            board_[i] = board_[index_r];
            board_[index_r] = tmp;
        }

        while(clearable(board_)){
            for(i = 0; i <16; i++){
                board_[i] = i;
            }
            for(i = 15 ; i>0; i--){
                index_r = r.nextInt(i-1);
                tmp = board_[i];
                board_[i] = board_[index_r];
                board_[index_r] = tmp;
            }
        }

        k=0;
        for(i = 0 ; i<4; i++){
            for(j=0; j<4; j++){
                board[i][j] = board_[k++];
            }
        }
    }
    //섞인 배치를 풀 수 있는지
    public boolean clearable(int[] board){
        int emptyindex= -1;
        int xb, ic;
        int i,j;

        for(i=0; i<15; i++){
            if(board[i] == 15){
                emptyindex = i; break;
            }
        }
        xb = 4 - emptyindex/4;

        ic = 0;
        for(i=0; i<14;i++){
            for(j=i+1; j<15; j++){
                if(board[i] != 0 && board[j] !=0 && board[i] > board[j])
                    ic++;
            }
        }
        if((xb + ic) % 2 == 0) return false;
        else return true;
    }

    //현재 빈 공간(0)이 어디인지 반환
    public int emptyindex(){
        int i,j;
        for(i=0; i<4; i++){
            for(j=0;j<4; j++){
                if(board[i][j] == 0) return i*10+j;
            }
        }
        return -1;
    }

    //상하좌우로 움직이는게 가능한지 반환
    public boolean leftable(){
        int index=emptyindex()%10;
        if(index==3) return false;
        return true;
    }
    public boolean rightable(){
        int index=emptyindex()%10;
        if(index==0) return false;
        return true;
    }
    public boolean upable(){
        int index=emptyindex()/10;
        if(index==3) return false;
        return true;
    }
    public boolean downable() {
        int index = emptyindex() / 10;
        if (index == 0) return false;
        return true;
    }

    //clear 했는지 확인
    public boolean check_clear(){
        int i, j, k;
        k = 1;
        for(i=0 ; i<4; i++){
            for(j=0; j<4; j++){
                if(board[i][j] != k++) return false;
            }
        }
        return true;
    }
}