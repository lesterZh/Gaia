package com.gaia.member.gaiatt.utils;

import android.app.Dialog;
import android.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangHaohan on 2016/4/18.
 */
public class AllDialogManagement {


    private static List dialogList = new ArrayList<>();


    public static void setDialogManagement(boolean priority,Object o){

        if (priority) {
            setPosition(0,o);
            controlDialog();
        }else{
            dialogList.add(o);
            controlDialog();
        }
    }

    /**
     * 隐藏list非第一位置的其他弹窗
     * */
    private static void controlDialog() {
        hideAllDialog();
        if (dialogList.get(0) instanceof  Dialog) {
            ((Dialog) dialogList.get(0)).show();
        }else{
            ((DialogFragment)dialogList.get(0)).setShowsDialog(true);
        }

    }

    public static  void remove(){
            dialogList.remove(0);
        if (dialogList.size()>0) {
            if (dialogList.get(0) instanceof  Dialog) {
                ((Dialog) dialogList.get(0)).show();
            }else{
                ((DialogFragment)dialogList.get(0)).setShowsDialog(true);
            }
        }
    }
    /**
     * 主Activity中关闭所有在list中的dialog
     *
     *
     */
    private static void hideAllDialog() {
        for (int i = 0; i < dialogList.size(); i++) {
            if (dialogList.get(i) instanceof  Dialog) {
                ((Dialog) dialogList.get(i)).hide();
            }else{
                ((DialogFragment)dialogList.get(i)).setShowsDialog(true);
            }
        }
    }



    /**
     * 设置dialog的优先级
     * */
    private static void setPosition(int position,Object o){
        dialogList.add(position, o);

    }

}
