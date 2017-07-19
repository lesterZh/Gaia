package com.gaia.member.gaiatt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gaia.member.androidlib.BookLib;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.utils.ImageUtils;
import com.gaia.member.androidlib.constant.DbConstant;
import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.db.DbHelper;
import com.gaia.member.androidlib.db.SharedPreferencesHelper;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.TestBean;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.squareup.okhttp.RequestBody;

import org.json.JSONStringer;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppBaseActivity {

    @Bind(R.id.add_button)
    Button addButton;

    @Bind(R.id.delete_button)
    Button deleteButton;

    @Bind(R.id.update_button)
    Button updateButton;

    @Bind(R.id.query_button)
    Button queryButton;

    @Bind(R.id.delete_selection_button)
    Button deleteSelectionButton;

    @Bind(R.id.query_price_button)
    Button queryPriceButton;

    @Bind(R.id.is_save_button)
    Button isSaveButton;

    @Bind(R.id.more_selection_button)
    Button moreSelectionButton;

    @Bind(R.id.book_name)
    EditText nameEdit;

    @Bind(R.id.book_price)
    EditText priceEdit;

    @Bind(R.id.book_type)
    EditText typeEdit;

    @Bind(R.id.book_publish_date)
    EditText publishDateEdit;

    @Bind(R.id.content_text)
    TextView contentText;

    @Bind(R.id.image_view)
    ImageView imageView;


    private DbHelper dbHelper;
    private SharedPreferencesHelper spHelper;
    private NetUtils netUtils;

    @Override
    protected void initVariables() {
        dbHelper = DbHelper.getDbHelperInstance(this);
        spHelper = SharedPreferencesHelper.getSharedPreHelperInstance(this);
        netUtils = NetUtils.getNetUtilsInstance();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookLib bookLib = getBookLib();
                dbHelper.insertBookLibData(bookLib);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteAll();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改数据库中的值时,主键不能改动
                //这要根据情况写逻辑,这里只是举个例子
                BookLib bookLib = new BookLib(12l, "s", 8, "kk", "590");
                dbHelper.updateBook(bookLib);
            }
        });

        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = "";
                List<BookLib> bookList = dbHelper.queryAllBookLib();
                for (int i = 0; i < bookList.size(); i++) {
                    BookLib bookLib = bookList.get(i);
                    content += "\n" + bookLib.getName() + "-" + bookLib.getPrice() + "-" + bookLib.getType() + "-" + bookLib.getPublish_date();
                }
                contentText.setText(content);
            }
        });

        deleteSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteBook(Integer.valueOf(priceEdit.getText().toString()));
            }
        });

        queryPriceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int price = dbHelper.getPriceByName(nameEdit.getText().toString());
                contentText.setText("" + price);
            }
        });

        isSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbHelper.isSaveByName(nameEdit.getText().toString())) {
                    contentText.setText("true");
                } else {
                    contentText.setText("false");
                }
            }
        });

        moreSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<BookLib> bookList = dbHelper.getMoreQueryMoreConditionAnd(typeEdit.getText().toString(), Integer.valueOf(priceEdit.getText().toString()));
                String content = "";
                for (int i = 0; i < bookList.size(); i++) {
                    BookLib bookLib = bookList.get(i);
                    content += "\n" + bookLib.getName() + "-" + bookLib.getPrice() + "-" + bookLib.getType() + "-" + bookLib.getPublish_date();
                }
                contentText.setText(content);
            }
        });
    }

    @Override
    protected void loadData() {
    }

    private BookLib getBookLib() {

        //这边一定要添加判空的代码,第一个参数是主键id,不需要我们给其赋值
        //.....
        return new BookLib(null, nameEdit.getText().toString(), Integer.valueOf(priceEdit.getText().toString()), typeEdit.getText().toString(), publishDateEdit.getText().toString());

    }

    @OnClick(R.id.shared_save_button)
    void sharedSaveListener() {
        spHelper.saveString(DbConstant.TEST_KEY, nameEdit.getText().toString());
    }

    @OnClick(R.id.shared_get_button)
    void sharedGetListener() {
        contentText.setText(spHelper.getString(DbConstant.TEST_KEY));
    }

    @OnClick(R.id.download_image_button)
    void downloadImageListener() {
        String url = "http://img.my.csdn.net/uploads/201407/26/1406382861_7618.jpg";
//        imageLoader.displayImage(url,imageView,ImageUtils.getDisplayImageOptions());

        imageLoader.displayImage(url, imageView, ImageUtils.getDisplayImageOptions(), null, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String s, View view, int i, int i1) {

                //i--已下载的图片的大小; i1 -- 图片的总大小
                Log.e("进度条", "i = " + i + ";   i1 = " + i1);

                //百分比计算方法
                int percent = (int) ((i / (float) i1) * 100);
                Log.e("百分比", "" + percent + "%");
            }
        });
    }

    @OnClick(R.id.login_button)
    void loginListener() {

        /**
         * 这是一个Post 请求的方式
         */
        try {

            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("tel").value("18190712700")
                    .key("psw").value("wujinwo123")
                    .key("deviceTag").value("22")
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);

            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postTest(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<TestBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("error", "" + e.getMessage());
                        }

                        @Override
                        public void onNext(TestBean testBean) {
                            if (testBean.getSuccess()) {
                                Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });


        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 这是一个Get 的请求的方式
         */
        Observable<TestBean> call = NetUtils.getNetUtilsInstance().getNetApiServiceInterface().getTest("dd");
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TestBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TestBean testBean) {
                        //这边就是返回的数据
                    }
                });

    }

    @OnClick(R.id.upload_file_button)
    void uploadFileListener() {

        /**
         * 上传图片
         */

        //图片的路径
        String path = "";
        netUtils.getNetApiServiceInterface().uploadFile(netUtils.getUploadFileProgressBody(path))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TestBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TestBean testBean) {

                    }
                });

    }

    /**
     * 高斯模糊处理的图片
     *
     * 还未实现
     */
    @OnClick(R.id.blur_image_button)
    void blurImageListener(){
        Intent intent = new Intent(MainActivity.this,TestActivity.class);
        startActivity(intent);
    }

    /**
     * 可下拉刷新 加载更多的view
     */
    @OnClick(R.id.test_refresh_button)
    void testListListener(){
        Intent intent= new Intent(MainActivity.this,ListViewActivity.class);
        startActivity(intent);
    }

}
