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

    private ImageView[] imgblock = new ImageView[16];

    private static int BLOCK_SIZE;

    private int[][] board = new int[4][4];  //보드판 배치 배열

    //현재 게임이 실행중인지 판단
    private boolean isPaused = false;

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
                    moveimg(emptyindex+1,1);
                    //moveimg = board[emptyindex/10][emptyindex%10+1];
                    //imgblock[moveimg].scrollBy(BLOCK_SIZE,0);
                    tmp = board[emptyindex/10][emptyindex%10+1];
                    board[emptyindex/10][emptyindex%10+1] = 0;
                    board[emptyindex/10][emptyindex%10] = tmp;
                    if(check_clear()) Toast.makeText(getContext(), "CLEAR", Toast.LENGTH_SHORT).show();
                }
                //left to right
                else if(e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
                    if(!rightable()) return false;
                    emptyindex = emptyindex();
                    moveimg(emptyindex-1,2);
                    //moveimg = board[emptyindex/10][emptyindex%10-1];
                    //imgblock[moveimg].scrollBy(-BLOCK_SIZE,0);
                    tmp = board[emptyindex/10][emptyindex%10-1];
                    board[emptyindex/10][emptyindex%10-1] = 0;
                    board[emptyindex/10][emptyindex%10] = tmp;
                    if(check_clear()) Toast.makeText(getContext(), "CLEAR", Toast.LENGTH_SHORT).show();
                }
                //down to up
                else if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY){
                    if(!upable()) return false;
                    emptyindex = emptyindex();
                    moveimg(emptyindex+10,3);
                    //moveimg = board[emptyindex/10+1][emptyindex%10];
                    //imgblock[moveimg].scrollBy(0,BLOCK_SIZE);
                    tmp = board[emptyindex/10+1][emptyindex%10];
                    board[emptyindex/10+1][emptyindex%10] = 0;
                    board[emptyindex/10][emptyindex%10] = tmp;
                    if(check_clear()) Toast.makeText(getContext(), "CLEAR", Toast.LENGTH_SHORT).show();
                }
                //up to down
                else if(e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY){
                    if(!downable()) return false;
                    emptyindex = emptyindex();
                    moveimg(emptyindex-10,4);
                    //moveimg = board[emptyindex/10-1][emptyindex%10];
                    //imgblock[moveimg].scrollBy(0,-BLOCK_SIZE);
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

        view.findViewById(R.id.menu_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                pauseGame();
            }
        });

        view.findViewById(R.id.resume_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumeGame();
            }
        });

        view.findViewById(R.id.restart_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
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
        View view = getView();
        assert view != null;
        View parent = (View)view.getParent();
        int width = parent.getWidth() - 20;
        int height = parent.getHeight();
        int blockSize = width / 4;
        BLOCK_SIZE = blockSize/2;
        int boardSize = 4 * blockSize;

        //로고 크기 및 위치 초기화
        int logoHeight = (height-width)/2;
        RelativeLayout.LayoutParams logo_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, logoHeight);
        logo_params.setMargins(0,10,0,10);
        View logo = view.findViewById(R.id.game_logo);
        logo.setLayoutParams(logo_params);

        //메뉴 버튼 크기 및 위치 초기화
        RelativeLayout.LayoutParams menu_params = new RelativeLayout.LayoutParams(logoHeight, logoHeight);
        menu_params.setMargins(10,10,0,0);
        View menu = view.findViewById(R.id.menu_button);
        menu.setLayoutParams(menu_params);

        //Restart 버튼 크기 및 위치 초기화
        int buttonSize = (int)(blockSize*1.5);
        RelativeLayout.LayoutParams restart_params = new RelativeLayout.LayoutParams(buttonSize, buttonSize);
        restart_params.setMargins(width/2 - buttonSize/2, (height/2) - (int)(buttonSize*1.2),0,0);
        View restart = view.findViewById(R.id.restart_button);
        restart.setLayoutParams(restart_params);

        //Resume 버튼 크기 및 위치 초기화
        RelativeLayout.LayoutParams resume_params = new RelativeLayout.LayoutParams(buttonSize, buttonSize);
        resume_params.setMargins(width/2 - buttonSize/2, (height/2) + (int)(buttonSize*0.2),0,0);
        View resume = view.findViewById(R.id.resume_button);
        resume.setLayoutParams(resume_params);

        //보드 크기 및 위치 초기화
        RelativeLayout.LayoutParams board_params = new RelativeLayout.LayoutParams(boardSize, boardSize);
        board_params.setMargins(10,logoHeight+20,0,0);
        View board_view = view.findViewById(R.id.game_board);
        board_view.setLayoutParams(board_params);

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

    public void moveimg(int index, int dir){
        RelativeLayout.LayoutParams block_params;
        block_params = new RelativeLayout.LayoutParams(BLOCK_SIZE, BLOCK_SIZE);
        int topMargin;
        int leftMargin;
        switch(dir){
            //left
            case 1 :
                topMargin = (index/10)*BLOCK_SIZE;
                leftMargin = (index%10-1)*BLOCK_SIZE;
                block_params.setMargins(leftMargin, topMargin, 0, 0);
                imgblock[board[index/10][index%10]].setLayoutParams(block_params);
                break;
            //right
            case 2 :
                topMargin = (index/10)*BLOCK_SIZE;
                leftMargin = (index%10+1)*BLOCK_SIZE;
                block_params.setMargins(leftMargin, topMargin, 0, 0);
                imgblock[board[index/10][index%10]].setLayoutParams(block_params);
                break;
            //up
            case 3 :
                topMargin = (index/10-1)*BLOCK_SIZE;
                leftMargin = (index%10)*BLOCK_SIZE;
                block_params.setMargins(leftMargin, topMargin, 0, 0);
                imgblock[board[index/10][index%10]].setLayoutParams(block_params);
                break;
            //down
            case 4 :
                topMargin = (index/10+1)*BLOCK_SIZE;
                leftMargin = (index%10)*BLOCK_SIZE;
                block_params.setMargins(leftMargin, topMargin, 0, 0);
                imgblock[board[index/10][index%10]].setLayoutParams(block_params);
                break;
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
        int ic;
        int i,j;

        ic = 0;
        for(i=0; i<14;i++){
            for(j=i+1; j<15; j++){
                if(board[i] != 0 && board[j] !=0 && board[i] > board[j])
                    ic++;
            }
        }
        if((1 + ic) % 2 == 0) return false;
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
                if(i==3 && j==3) continue;
                if(board[i][j] != k++) return false;
            }
        }
        return true;
    }

    //게임 일시정지 여부 확인
    public boolean getPaused(){
        return isPaused;
    }
    public void setPaused(boolean b){
        isPaused = b;
    }

    //게임 일시정지시키기
    public void pauseGame(){
        View pause_page = getView().findViewById(R.id.pause_screen);
        pause_page.setVisibility(View.VISIBLE);
        pause_page.setAlpha(1);
        //TODO 게임 내 움직임 비활성화시키기
    }

    //게임 resume시키기
    public void resumeGame(){
        View pause_page = getView().findViewById(R.id.pause_screen);
        pause_page.setVisibility(View.INVISIBLE);
        pause_page.setAlpha(0);
        //TODO 게임 내 움직임 다시 활성화시키기
    }

    //게임 restart시키기
    public void restartGame(){
        View pause_page = getView().findViewById(R.id.pause_screen);
        pause_page.setVisibility(View.INVISIBLE);
        pause_page.setAlpha(0);
    }
}