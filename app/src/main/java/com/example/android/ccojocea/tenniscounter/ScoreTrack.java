/*
 * Copyright (c) 2017 Cosmin Cojocea
 *
 * This project was created and submitted by Cosmin Cojocea as part of the Google Challenge Scholarship: Android Basics for Udacity.
 *
 * According to Udacity's Honor code, your submissions must be your own work, hence submitting this project as your own might cause you to earn the suspension of your account.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Except as contained in this notice, the name(s) of the above copyright holders shall not be used in advertising or otherwise to promote the sale, use or other dealings in this Software without prior written authorization.
 *
 *
 *
 *
 */

package com.example.android.ccojocea.tenniscounter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccojo on 11/28/2017.
 */

public class ScoreTrack{
    List<Integer> scoresP1 = new ArrayList<>();
    List<Integer> scoresP2 = new ArrayList<>();
    int currentSet;

    public static String toMyString(List<?> list){
        String result = "";
        for (int i = 0; i < list.size(); i++){
            result = result + "  " + list.get(i);
        }
        return result;
    }

    public void setCurrentSet(int i){
        currentSet = i;
    }



//    public int[] set1 = {0, 0};
//    public int[] set2 = {0, 0};
//    public int[] set3 = {0, 0};
//    public int[] set4 = {0, 0};
//    public int[] set5 = {0, 0};
//    public int[] set6 = {0, 0};
//    public int[] set7 = {0, 0};
//
//    public void setNumberOfSets(int setNumber){
//        this.setsPlayed = setNumber;
//    }
//
//    public void setScoreForASet (int game1, int game2, int setNumber){
//        switch (setNumber){
//            case 1:
//                this.set1[0] = game1;
//                this.set1[1] = game2;
//                this.playerOneSets[0] = game1;
//                this.playerTwoSets[0] = game2;
//                break;
//            case 2:
//                this.set2[0] = game1;
//                this.set2[1] = game2;
//                this.playerOneSets[1] = game1;
//                this.playerTwoSets[1] = game2;
//                break;
//            case 3:
//                this.set3[0] = game1;
//                this.set3[1] = game2;
//                this.playerOneSets[2] = game1;
//                this.playerTwoSets[2] = game2;
//                break;
//            case 4:
//                this.set4[0] = game1;
//                this.set4[1] = game2;
//                this.playerOneSets[3] = game1;
//                this.playerTwoSets[3] = game2;
//                break;
//            case 5:
//                this.set5[0] = game1;
//                this.set5[1] = game2;
//                this.playerOneSets[4] = game1;
//                this.playerTwoSets[4] = game2;
//                break;
//            case 6:
//                this.set6[0] = game1;
//                this.set6[1] = game2;
//                this.playerOneSets[5] = game1;
//                this.playerTwoSets[5] = game2;
//                break;
//            case 7:
//                this.set7[0] = game1;
//                this.set7[1] = game2;
//                this.playerOneSets[6] = game1;
//                this.playerTwoSets[6] = game2;
//                break;
//            default:
//                throw new IndexOutOfBoundsException("Maximum number of sets is 7, please reset scores!");
//        }
//    }
}