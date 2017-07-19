package com.gaia.member.androidlib.net;

import android.util.Log;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.interfaces.NetApiServiceInterface;
import com.gaia.member.androidlib.interfaces.UploadFileProgressInterface;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by baiyuanwei on 16/3/16.
 */
public class NetUtils {

    private static NetUtils netUtilsInstance;

    public static NetUtils getNetUtilsInstance() {
        if (netUtilsInstance == null) {
            netUtilsInstance = new NetUtils();
        }

        return netUtilsInstance;
    }

    /**
     * 获取NetApiServiceInterface的对象
     *
     * @return
     */
    private HashMap<String, RequestBody > filed = new  HashMap<String, RequestBody >();
    public NetApiServiceInterface getNetApiServiceInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(NetApiServiceInterface.class);
    }




    /**
     * 自定义一个带进度条的RequestBody
     *
     * @param mediaType
     * @param file
     * @param uploadFileProgressInterface
     * @return
     */
    private RequestBody createCustomRequestBody(final MediaType mediaType, final File file, final UploadFileProgressInterface uploadFileProgressInterface) {

        //自定义一个RequestBody
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return mediaType;
            }

            @Override
            public long contentLength() throws IOException {
                return file.length();
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                Source source;
                try {
                    source = Okio.source(file);

                    Buffer buffer = new Buffer();
                    Long remaining = contentLength();

                    for (long readCount; (readCount = source.read(buffer, 2048)) != -1; ) {
                        sink.write(buffer, readCount);
                        uploadFileProgressInterface.onProgress(contentLength(), remaining -= readCount, remaining == 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * 得到上传图片的RequestBody
     * 带有上传进度
     *
     * @param path
     * @return
     */
    public RequestBody getUploadFileProgressBody(String path) {

        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        File file = new File(path);

        //此处的"file"是文件对应的key值
        builder.addFormDataPart("file", file.getName(), createCustomRequestBody(MultipartBuilder.FORM, file, new UploadFileProgressInterface() {
            @Override
            public void onProgress(Long totalBytes, Long remainingBytes, boolean done) {

                //百分比
                String percent = ((totalBytes - remainingBytes) * 100) / totalBytes + "%";
                Log.e("百分比:",percent+"");
            }
        }));
        RequestBody body = builder.build();

        return body;
    }

    /**
     * 得到上传图片的RequestBody
     * 没有上传进度
     *
     * @param path
     * @return
     */
    public RequestBody getUploadFileBody(String path){
        File imageFile = new File(path);
        RequestBody fileBody = RequestBody.create(MediaType.parse(NetConstant.IMAGE_TYPE), imageFile);
        MultipartBuilder multipartBuilder = new MultipartBuilder();

        //此处的"file"是文件对应的key值
        multipartBuilder.addFormDataPart("file", imageFile.getName(), fileBody);
        return multipartBuilder.build();
    }


}
