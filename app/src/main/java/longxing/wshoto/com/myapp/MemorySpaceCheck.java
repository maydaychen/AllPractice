package longxing.wshoto.com.myapp;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by Weshine on 2017/6/14.
 */

public class MemorySpaceCheck
{

    /**
     * 计算剩余空间
     * @param path
     * @return
     */
    public static long getAvailableSize(String path)
    {
        StatFs fileStats = new StatFs(path);
        fileStats.restat(path);
        return (long) fileStats.getAvailableBlocks() * fileStats.getBlockSize(); // 注意与fileStats.getFreeBlocks()的区别
    }

    /**
     * 计算总空间
     * @param path
     * @return
     */
    private static long getTotalSize(String path)
    {
        StatFs fileStats = new StatFs(path);
        fileStats.restat(path);
        return (long) fileStats.getBlockCount() * fileStats.getBlockSize();
    }

    /**
     * 计算SD卡的剩余空间
     * @return 剩余空间
     */
    public static long getSDAvailableSize()
    {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            return getAvailableSize(Environment.getExternalStorageDirectory().toString());
        }

        return 0;
    }

    /**
     * 计算系统的剩余空间
     * @return 剩余空间
     */
    public static long getSystemAvailableSize()
    {
        // context.getFilesDir().getAbsolutePath();
        return getAvailableSize("/data");
    }


    /**
     * 是否有足够的空间
     * @param filePath 文件路径，不是目录的路径
     * @return
     */
    public static boolean hasEnoughMemory(String filePath)
    {
        File file = new File(filePath);
        long length = file.length();
        if (filePath.startsWith("/sdcard") || filePath.startsWith("/mnt/sdcard"))
        {
            return getSDAvailableSize() > length;
        }
        else
        {
            return getSystemAvailableSize() > length;
        }

    }

    /**
     * 获取SD卡的总空间
     * @return
     */
    public static long getSDTotalSize()
    {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            return getTotalSize(Environment.getExternalStorageDirectory().toString());
        }

        return 0;
    }

    /**
     * 获取系统可读写的总空间
     * @return
     */
    public static long getSysTotalSize()
    {
        return getTotalSize("/data");
    }

    /**
     * SD卡是否存在
     * @return
     */
    public static boolean ExistSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }

    /**
     * 文件是否存在
     * @return
     */
    public static boolean fileIsExists(String path){
        try{
            File f=new File(path);
            if(!f.exists()){
                return false;
            }
        }catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }
}