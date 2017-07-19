package com.gaia.member.androidlib.interfaces;

/**
 * Created by baiyuanwei on 16/3/16.
 */
public interface UploadFileProgressInterface {
    public void onProgress(Long totalBytes, Long remainingBytes, boolean done);
}
