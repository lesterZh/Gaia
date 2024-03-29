package com.gaia.member.gaiatt.utils;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;


/**
 * Fragment的管理类
 */
public class AllFragmentManagement {


    public static List<Fragment> fragmentList = new ArrayList<>();
    public static List<Fragment> brankFragmentList = new ArrayList<>();

    public AllFragmentManagement() {

    }

    /**
     * @param activity    要填充的activity
     * @param containerId 置换fragment的空间ID
     * @param fragment    需要添加的fragment
     */
    public static void ChangeFragment(int type, Activity activity, int containerId, Fragment fragment, boolean isAdd) {

        FragmentManager fg = activity.getFragmentManager();
        FragmentTransaction ft = fg.beginTransaction();

        switch (type) {
            case 1:
                HideAllFragment(ft);
                break;
            case 2:
                HideAllBrankFragment(ft);
                break;
            default:
                break;
        }
        if (isAdd) {
            ft.add(containerId, fragment);
        } else {
            ft.show(fragment);
        }
        ft.commit();
    }



    /**
     * 主Activity中隐藏所有在list中的Fragment
     *
     * @param ft
     */
    public static void HideAllFragment(FragmentTransaction ft) {
        for (Fragment mfragment : fragmentList) {
            ft.hide(mfragment);
        }
    }

    /**
     * 主Activity中隐藏所有在list中的Fragment
     *
     * @param ft
     */
    public static void HideAllBrankFragment(FragmentTransaction ft) {
        for (Fragment mfragment : brankFragmentList) {
            ft.hide(mfragment);
        }
    }



}

