package com.gaia;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;


/**
 * 注意:
 * 1、每次更新数据库时,必须更改版本号,并重新运行此工程
 *
 * 此文件的主要作用如下:
 * 1、配置数据库的一些信息,如: Schema、数据库的版本号等 (数据库的名称在Application中设置)
 * 2、建表, 所有的建表工作都是在这里完成的
 *
 * 在生成的文件中主要会用到"表名+Dao" 这个文件,如:NoteDao
 */
public class GreenDaoGenerator {

    //数据库的版本号
    private final static int DB_VERSION = 1;

    public static void main(String[] args) throws Exception {

        //在更新的时候要更改版本号,
        //配置对应的android Module
        Schema schema = new Schema(DB_VERSION, "com.gaia.member.androidlib");

        //加表
        addNote(schema);

        addBook(schema);

        addStudent(schema);

        //在指定的路径里生成必要的文件
        new DaoGenerator().generateAll(schema, "/Users/baiyuanwei/Desktop/盖亚/代码/GaiaTt/androidlib/src/main/java-gen");
    }

    /**
     * 这是一个note表
     *
     * @param schema
     */
    private static void addNote(Schema schema) {

        //Note 是表名
        Entity note = schema.addEntity("Note");

        //设置一个自增长的主键
        note.addIdProperty().primaryKey().autoincrement();

        // 添加想要的列
        note.addIntProperty("note_id");
        note.addStringProperty("text").notNull();
        note.addDateProperty("date");
        note.addStringProperty("comment");
    }

    /**
     * 书表
     *
     * @param schema
     */
    private static void addBook(Schema schema) {
        Entity book = schema.addEntity("BookLib");
        book.addIdProperty().primaryKey().autoincrement();
        book.addStringProperty("name");
        book.addIntProperty("price");
        book.addStringProperty("type");
        book.addStringProperty("publish_date");
    }

    /**
     * 这是一个新建的表
     *
     * @param schema
     */
    private static void addStudent(Schema schema) {
        Entity entity = schema.addEntity("Student");
        entity.addIdProperty().primaryKey().autoincrement();
        entity.addStringProperty("name");
    }
}

