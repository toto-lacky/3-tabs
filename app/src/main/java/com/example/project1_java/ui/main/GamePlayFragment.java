package com.example.project1_java.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    private ImageView imgblock[] = new ImageView[16];

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

        for(int i = 1; i<16; i++){
            String blockID = "block" + i;
            int resID = getResources().getIdentifier(blockID, "id", getContext().getPackageName());
            imgblock[i] = view.findViewById(resID);
        }

        final GestureDetector gd = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
                int moveimg;
                int tmp, emptyindex;

                if(Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;

                //right to left
                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
                    if(!leftable()) return false;
                    emptyindex = emptyindex();
                    moveimg = board[emptyindex/10][emptyindex%10+1];
                    imgblock[moveimg].scrollBy(BLOCK_SIZE,0);
                    tmp = board[emptyindex/10][emptyindex%10+1];
                    board[emptyindex/10][emptyindex%10+1] = 0;
                    board[emptyindex/10][emptyindex%10] = tmp;
                    if(check_clear()) Toast.makeText(getContext(), "CLEAR", Toast.LENGTH_SHORT).show();
                }
                //left to right
                else if(e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
                    if(!rightable()) return false;
                    emptyindex = emptyindex();
                    moveimg = board[emptyindex/10][emptyindex%10-1];
                    imgblock[moveimg].scrollBy(-BLOCK_SIZE,0);
                    tmp = board[emptyindex/10][emptyindex%10-1];
                    board[emptyindex/10][emptyindex%10-1] = 0;
                    board[emptyindex/10][emptyindex%10] = tmp;
                    if(check_clear()) Toast.makeText(getContext(), "CLEAR", Toast.LENGTH_SHORT).show();
                }
                //down to up
                else if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY){
                    if(!upable()) return false;
                    emptyindex = emptyindex();
                    moveimg = board[emptyindex/10+1][emptyindex%10];
                    imgblock[moveimg].scrollBy(0,BLOCK_SIZE);
                    tmp = board[emptyindex/10+1][emptyindex%10];
                    board[emptyindex/10+1][emptyindex%10] = 0;
                    board[emptyindex/10][emptyindex%10] = tmp;
                    if(check_clear()) Toast.makeText(getContext(), "CLEAR", Toast.LENGTH_SHORT).show();
                }
                //up to down
                else if(e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY){
                    if(!downable()) return false;
                    emptyindex = emptyindex();
                    moveimg = board[emptyindex/10-1][emptyindex%10];
                    imgblock[moveimg].scrollBy(0,-BLOCK_SIZE);
                    tmp = board[emptyindex/10-1][emptyindex%10];
                    board[emptyindex/10-1][emptyindex%10] = 0;
                    board[emptyindex/10][emptyindex%10] = tmp;
                    if(check_clear()) Toast.makeText(getContext(), "CLEAR", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onStart() {
        super.onStart();
        initGraphic();
    }

    //보드판 그래픽 초기화
    private void initGraphic(){
        //Log.d("\n\nInitGraphic","entered");
        View view = getView();
        assert view != null;
        View parent = (View)view.getParent();
        int width = parent.getWidth();
        int height = parent.getHeight();
        int blockSize = width / 4;
        BLOCK_SIZE = blockSize;
        int boardSize = 4 * blockSize;
        //Log.d("InitGraphic","blockSize: "+blockSize+" boardSize: "+boardSize);

        //로고 크기 및 위치 초기화
        int logoHeight = (height-width)/2;
        RelativeLayout.LayoutParams logo_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, logoHeight);
        logo_params.setMargins(0,10,0,10);
        View logo = view.findViewById(R.id.game_logo);
        logo.setLayoutParams(logo_params);

        //보드 크기 및 위치 초기화
        RelativeLayout.LayoutParams board_params = new RelativeLayout.LayoutParams(boardSize, boardSize);
        board_params.setMargins(0,logoHeight+20,0,0);
        View boardv = view.findViewById(R.id.game_board);
        boardv.setLayoutParams(board_params);

        //블럭 크기 및 위치 초기화
        RelativeLayout.LayoutParams block_params;
        initboard();
        for(int i = 1; i < 16; i++){
            block_params = new RelativeLayout.LayoutParams(blockSize, blockSize);
            int topMargin = ((i-1)/4)*blockSize;
            int leftMargin = ((i-1)%4)*blockSize;
            block_params.setMargins(leftMargin,topMargin,0,0);

            int index = board[(i-1)/4][(i-1)%4];
            String blockID = "block" + index;

            //String blockID = "block" + i;
            int resID = getResources().getIdentifier(blockID, "id", getContext().getPackageName());
            ImageView block = view.findViewById(resID);
            block.setLayoutParams(block_params);
        }
    }

    //보드판에 랜덤 배치하기
    public void initboard(){

        int tmp;
        int index_r;
        int board_[] = new int[16];
        int i, j, k;
        Random r = new Random();
        //배치
        for(i = 0; i <15; i++){
            board_[i] = i+1;
        }
        board_[15] = 0;

        for(i = 14 ; i>1; i--){
            index_r = r.nextInt(i);
            tmp = board_[i];
            board_[i] = board_[index_r];
            board_[index_r] = tmp;
        }
        //재배치
        while(!clearable(board_)){
            for(i = 0; i <16; i++){
                board_[i] = i;
            }
            for(i = 15 ; i>0; i--){
                index_r = r.nextInt(i);
                tmp = board_[i];
                board_[i] = board_[index_r];
                board_[index_r] = tmp;
            }
        }
        //board 판에 옮기기
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
        int index = emptyindex()/10;
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