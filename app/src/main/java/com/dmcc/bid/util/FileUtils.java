package com.dmcc.bid.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {

    /**
     * 保存路径的文件夹名称
     */
    public static String DIR_NAME = "qupai_video_test";

    /**
     * 给指定的文件名按照时间命名
     */
    private static SimpleDateFormat OUTGOING_DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");


    /**
     * 得到指定的Video保存路径
     *
     * @return
     */
    public static File getDoneVideoPath(Context context) {
        String str = OUTGOING_DATE_FORMAT.format(new Date());
        File dir = new File(context.getFilesDir()
                + File.separator + DIR_NAME + "/" + str);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }

    private static File getStorageDir(Context context) {
        // Get the directory for the app's private pictures directory.
        File file = new File(context.getExternalFilesDir(
                Environment.MEDIA_MOUNTED), "qupaiVideo");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("TAG", "Directory not created");
            }
        }

        return file;
    }

    /**
     * 检测Sdcard是否存在
     *
     * @return
     */
    public static boolean isExitsSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 得到输出的Video保存路径
     *
     * @return
     */
    public static String newOutgoingFilePath(Context context) {
        String str = OUTGOING_DATE_FORMAT.format(new Date());
        String fileName = getStorageDir(context).getPath() + "/" + File.separator + str + ".mp4";
        return fileName;
    }

    /**
     * 得到输出的Video保存路径
     *
     * @return
     */
    public static String getVoiceFilePath() {
        String str = "_voice" + OUTGOING_DATE_FORMAT.format(new Date());
        String fileName = Constant.DEFAULT_VOICE_PATH;
        createDir(fileName);
        return fileName + File.separator + str + ".wav";
    }


    /**
     * 得到输出的Video保存路径
     *
     * @return
     */
    public static String getVideoFilePath() {
        String str = "_video" + OUTGOING_DATE_FORMAT.format(new Date());
        String fileName = Constant.DEFAULT_VIDEO_PATH + "/" + File.separator + str + ".mp4";
        return fileName;
    }

    /**
     * 得到输出的Video保存路径
     *
     * @return
     */
    public static String getImageFilePath() {
        String str = "_image" + OUTGOING_DATE_FORMAT.format(new Date());
        String fileName = Constant.DEFAULT_IMAGE_PATH + "/" + File.separator + str + ".jpg";
        return fileName;
    }

    /**
     * 5、创建目录
     */
    public static void creatMediaDir() {
        createDir(Constant.DEFAULT_VOICE_PATH);
        createDir(Constant.DEFAULT_IMAGE_PATH);
        createDir(Constant.DEFAULT_VIDEO_PATH);
    }


    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            Log.e("TAG", "创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            Log.e("TAG", "创建目录" + destDirName + "成功！");
            return true;
        } else {
            Log.e("TAG", "创建目录" + destDirName + "失败！");
            return false;
        }
    }
}
