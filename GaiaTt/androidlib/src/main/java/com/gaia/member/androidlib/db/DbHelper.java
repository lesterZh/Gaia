package com.gaia.member.androidlib.db;

import android.content.Context;

import com.gaia.member.androidlib.BookLib;
import com.gaia.member.androidlib.BookLibDao;
import com.gaia.member.androidlib.CustomApplication;
import com.gaia.member.androidlib.DaoSession;
import com.gaia.member.androidlib.NoteDao;
import com.gaia.member.androidlib.Steps;
import com.gaia.member.androidlib.StepsDao;

import java.util.List;

import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by baiyuanwei on 16/3/14.
 * 在此java 文件中获取数据库中表的对象,并对其操作
 */
public class DbHelper {

    //note表
    private NoteDao noteDao;

    //BookLib 表
    private BookLibDao bookLibDao;

    //steps表
    private StepsDao stepsDao;

    private static DbHelper dbHelperInstance;
    private static Context mContext;

    /**
     * 单例模式得到DbHelper的对象
     *
     * @param context
     * @return
     */
    public static DbHelper getDbHelperInstance(Context context) {
        if (dbHelperInstance == null) {
            dbHelperInstance = new DbHelper();
            if (mContext == null) {
                mContext = context;
            }

            //获取数据库中表的对象
            DaoSession daoSession = ((CustomApplication) mContext.getApplicationContext()).getDaoSession();
            dbHelperInstance.noteDao = daoSession.getNoteDao();
            dbHelperInstance.bookLibDao = daoSession.getBookLibDao();
            dbHelperInstance.stepsDao = daoSession.getStepsDao();
        }

        return dbHelperInstance;
    }

    /**
     * 增加
     *
     * 如果不存在该书名,就增加数据
     * @param bookLib
     */
    public void insertBookLibData(BookLib bookLib) {
        if (!isSaveByName(bookLib.getName())){
            bookLibDao.insert(bookLib);
        }

    }

    /**
     * 增加
     *
     * 如果不存在,就增加数据
     * @param steps
     */
    public void insertStepsData(Steps steps) {
        if (!isSaveByTime(steps.getTime())){
            stepsDao.insert(steps);
        }

    }

    /**
     * 删除
     *
     * 根据价格price删除数据
     * 也可以根据其它列删除数据
     *
     * @param price
     */
    public void deleteBook(int price) {
        QueryBuilder<BookLib> qb = bookLibDao.queryBuilder();

        //where的括号中就是条件,把其中的"Price"换成其它列名就ok了,"eq"代表相等
        DeleteQuery<BookLib> dq = qb.where(BookLibDao.Properties.Price.eq(price)).buildDelete();
        dq.executeDeleteWithoutDetachingEntities();
    }

    /**
     * 删除
     *
     * 根据多个条件删除数据
     * 这些条件是"与"的关系
     *
     * @param price
     * @param publishDate
     */
    public void deleteBookMoreConditionAnd(int price,String publishDate) {
        QueryBuilder<BookLib> qb = bookLibDao.queryBuilder();

        //where的括号中就是条件,把其中的"Price"换成其它列名就ok了,"eq"代表相等
        DeleteQuery<BookLib> dq = qb.where(qb.and(BookLibDao.Properties.Price.eq(price), BookLibDao.Properties.Publish_date.eq(publishDate))).buildDelete();
        dq.executeDeleteWithoutDetachingEntities();
    }

    /**
     * 删除
     *
     * 根据多个条件来删除数据
     * 这些条件是"或"的关系
     *
     * @param price
     */
    public void deleteBookMoreConditionOr(int price,String publishDate) {
        QueryBuilder<BookLib> qb = bookLibDao.queryBuilder();

        //where的括号中就是条件,把其中的"Price"换成其它列名就ok了,"eq"代表相等
        DeleteQuery<BookLib> dq = qb.where(qb.or(BookLibDao.Properties.Price.eq(price), BookLibDao.Properties.Publish_date.eq(publishDate))).buildDelete();
        dq.executeDeleteWithoutDetachingEntities();
    }

    /**
     * 删除
     *
     * 删除BookLib表中的所有数据
     */
    public void deleteAll() {
        bookLibDao.deleteAll();
    }

    /**
     * 修改
     *
     * 1. 修改之前必须要知道该条目的主键的值
     * 如: booklib 旧数据: 12l a 8 b 89
     *             新数据: 12l a 10 g 89
     *             12l就是主键,类型是长整型long
     * @param bookLib
     */
    public void updateBook(BookLib bookLib) {
        //当修改数据时,bookLib中每个属性的值必须要有,且主键不能改动
        bookLibDao.update(bookLib);
    }
    /**
     * 修改
     *
     * 1. 修改之前必须要知道该条目的主键的值
     * 如: booklib 旧数据: 12l a 8 b 89
     *             新数据: 12l a 10 g 89
     *             12l就是主键,类型是长整型long
     * @param steps
     */
    public void updateSteps(Steps steps) {
        //当修改数据时,steps中每个属性的值必须要有,且主键不能改动
        stepsDao.update(steps);
    }

    /**
     * 查询
     *
     * 查询booklib表中的所有数据
     * @return
     */
    public List<BookLib> queryAllBookLib() {
        return bookLibDao.loadAll();
    }


    /**
     * 查询
     *
     * 根据name这一列,查询是否已经存在此name
     * 也可以根据其它列来查询,只要修改where中的代码就ok了
     *
     * @param name
     * @return
     */
    public boolean isSaveByName(String name) {
        QueryBuilder<BookLib> qb = bookLibDao.queryBuilder();
        qb.where(BookLibDao.Properties.Name.eq(name));
        int count = (int) qb.buildCount().count();
        return count > 0 ? true : false;
    }

    /**
     * 查询
     *
     * 根据time这一列,查询是否已经存在此time
     * 也可以根据其它列来查询,只要修改where中的代码就ok了
     *
     * @param time
     * @return
     */
    public boolean isSaveByTime(String time) {
        QueryBuilder<Steps> qb = stepsDao.queryBuilder();
        qb.where(StepsDao.Properties.Time.eq(time));
        int count = (int) qb.buildCount().count();
        return count > 0 ? true : false;
    }

    /**
     * 查询
     *
     * 根据当前时间返回id
     *
     * @param time
     * @return -1代表没有
     */
    public long getIdByTime(String time){
        QueryBuilder<Steps> qb = stepsDao.queryBuilder();
        qb.where(StepsDao.Properties.Time.eq(time));
        if (qb.list().size()>0){
            //返回书的价格
            return qb.list().get(0).getId();
        }else {
            //没有此书,返回一个错误码
            return -1;
        }
    }

    /**
     * 查询
     *
     * 根据当前时间返回id
     *
     * @param time
     * @return -1代表没有
     */
    public int getStepsByTime(String time){
        QueryBuilder<Steps> qb = stepsDao.queryBuilder();
        qb.where(StepsDao.Properties.Time.eq(time));
        if (qb.list().size()>0){
            //返回书的价格
            return qb.list().get(0).getToday_step();
        }else {
            //没有,返回一个错误码
            return -1;
        }
    }
    /**
     * 查询
     *
     * 根据书的名字返回书的价格
     *
     * @param name
     * @return -1代表没有此书
     */
    public int getPriceByName(String name){
        QueryBuilder<BookLib> qb = bookLibDao.queryBuilder();
        qb.where(BookLibDao.Properties.Name.eq(name));
        if (qb.list().size()>0){
            //返回书的价格
            return qb.list().get(0).getPrice();
        }else {
            //没有此书,返回一个错误码
            return -1;
        }
    }

    /**
     * 查询
     *
     * 根据多个条件来查询
     * 这些条件是"或"的关系
     *
     * @param type
     * @param price
     * @return 返回符合条件的所有booklib
     */
    public List<BookLib> getMoreQueryMoreConditionOr(String type,int price){
        QueryBuilder<BookLib> qb = bookLibDao.queryBuilder();
        qb.where(qb.or(BookLibDao.Properties.Type.eq(type), BookLibDao.Properties.Price.eq(price)));
        return qb.list();
    }

    /**
     * 查询
     *
     * 根据多个条件来查询
     * 这些条件是"与"的关系
     *
     * @param type
     * @param price
     * @return 返回符合条件的所有booklib
     */
    public List<BookLib> getMoreQueryMoreConditionAnd(String type,int price){
        QueryBuilder<BookLib> qb = bookLibDao.queryBuilder();
        qb.where(qb.and(BookLibDao.Properties.Type.eq(type), BookLibDao.Properties.Price.eq(price)));

        //这个默认是"与"的关系
//        qb.where(BookLibDao.Properties.Type.eq(type), BookLibDao.Properties.Price.eq(price));

        return qb.list();
    }
}
