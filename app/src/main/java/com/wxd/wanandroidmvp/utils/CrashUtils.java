package com.wxd.wanandroidmvp.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.wxd.wanandroidmvp.app.MyApplication;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CrashUtils implements Thread.UncaughtExceptionHandler {

    // CrashHandler实例
    private static CrashUtils crashUtils;
    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<>();
    private boolean saveCrash = true;//是否存crash信息到sd卡
    private String logDir = "";//设置文件存储目录
    private long logSize = 1 * 1024 * 1024L;//设置log文件大小 k
    private ExecutorService executor = Executors.newFixedThreadPool(1);

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashUtils() {
        init();
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashUtils getInstance() {
        if (crashUtils == null) {
            synchronized (CrashUtils.class) {
                if (crashUtils == null) {
                    crashUtils = new CrashUtils();
                }
            }
        }
        return crashUtils;
    }

    /**
     * 初始化
     */
    public void init() {
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        logDir = getRootDir() + MyApplication.application.getPackageName() + "_log";
        mkDir(logDir);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            System.gc();

        }
    }


    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                ToastUtils.getInstance().showLong("很抱歉,程序出现异常,即将退出.");
                Looper.loop();
            }
        }.start();
        // 收集设备参数信息
        collectDeviceInfo(MyApplication.application);
        // 保存日志文件
        saveCrashInfo2File(ex);
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private void saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        JSONObject jsonObject = new JSONObject(infos);
        sb.append(jsonFormatter(jsonObject.toString()));
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append("\n" + result);
        setCrash(sb.toString());
    }

    /**
     * 获取根目录
     */
    public String getRootDir() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            return "";
        }
    }

    /**
     * 可创建多个文件夹
     * dirPath 文件路径
     */
    public boolean mkDir(String dirPath) {
        String[] dirArray = dirPath.split("/");
        String pathTemp = "";
        boolean mkdir = false;
        for (int i = 0; i < dirArray.length; i++) {
            pathTemp = pathTemp + "/" + dirArray[i];
            File newF = new File(dirArray[0] + pathTemp);
            if (!newF.exists()) {
                mkdir = newF.mkdir();
            }
        }
        return mkdir;
    }

    /**
     * 是否保存crash信息
     *
     * @param saveCrash
     * @return
     */
    public CrashUtils saveCrash(boolean saveCrash) {
        this.saveCrash = saveCrash;
        return this;
    }

    /**
     * app异常时使用
     *
     * @param msg
     */
    public void setCrash(String msg) {
        String stackstr = targetStackTraceMSg();
        if (saveCrash) {
            saveToSd(stackstr, msg);
        }
    }

    private void saveToSd(final String stackstr, final Object objMsg) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                String data = dateToString(new Date(), "yyyy-MM-dd");
                File[] files = orderByDate(new File(logDir), true);
                List<File> filels = filter(files, data);
                String filepath;
                if (filels.size() > 0) {
                    Long length = getLength(filels.get(0));
                    if (length > logSize) {
                        int index = Integer.parseInt(filels.get(0).getName().replace("log_" + data + "_", "").replace(".html", ""));
                        int id = index + 1;
                        filepath = logDir + "/" + "log_" + data + "_" + id + ".html";
                        creatFile(filepath);
                    } else {
                        filepath = filels.get(0).getAbsolutePath();
                    }

                } else {
                    filepath = logDir + "/" + "log_" + data + "_1.html";
                    creatFile(filepath);
                }
                String msg = "";
                if (objMsg instanceof String) {
                    msg = objMsg.toString();
                } else {
                    msg = jsonFormatter(new Gson().toJson(objMsg));
                }
                appendText(filepath, "<div class=\"dotted\">" + "\n<div class=\"exp\">\n" + dateToString(new Date()) + "\n</div><div>\n" + stackstr + "\n</div><div class=\"redcolor\">\n" + msg.replaceAll("\n", "<br />") + "\n</div></div>", true);
            }
        });

    }

    private String targetStackTraceMSg() {
        StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
        if (targetStackTraceElement != null) {
            return "at " + targetStackTraceElement.getClassName() + "." + targetStackTraceElement.getMethodName() +
                    "(" + targetStackTraceElement.getFileName() + ":" + targetStackTraceElement.getLineNumber() + ")";

        } else {
            return "";
        }
    }

    private StackTraceElement getTargetStackTraceElement() {
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(CrashUtils.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }

    /**
     * 创建文件
     * filePath 文件路径
     */
    public boolean creatFile(String filePath) {
        File file = new File(filePath);
        boolean newFile = false;
        if (!file.exists()) {
            try {
                newFile = file.createNewFile();
            } catch (IOException e) {
                newFile = false;
            }
        }
        return newFile;
    }

    /**
     * 获取文件大小
     *
     * @param filePath
     * @return
     */
    public long getLength(File filePath) {
        if (!filePath.exists()) {
            return -1;
        } else {
            return filePath.length();
        }
    }

    /**
     * 按文件时间排序
     *
     * @param fliePath
     * @param desc
     * @return
     */
    public  File[] orderByDate(File fliePath, boolean desc) {
        File[] fs = fliePath.listFiles();
        Arrays.sort(fs, new Comparator<File>() {
            public int compare(File f1, File f2) {
                long diff = f1.lastModified() - f2.lastModified();
                if (diff > 0)
                    return 1;
                else if (diff == 0)
                    return 0;
                else
                    return -1;
            }

            public boolean equals(Object obj) {
                return true;
            }

        });
        if (desc) {
            File[] nfs = new File[fs.length];
            for (int i = fs.length - 1; i > -1; i--) {
                nfs[fs.length - 1 - i] = fs[i];
            }
            return nfs;
        } else {
            return fs;
        }
    }

    /**
     * 文件筛选
     *
     * @param files
     * @param filter
     * @return
     */
    public List<File> filter(File[] files, String filter) {
        List<File> filels = new ArrayList<>();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().contains(filter)) {
                    filels.add(files[i]);
                }
            }
        }
        return filels;
    }

    /**
     * 追加数据
     *
     * @param filePath
     * @param content
     * @param header   是否在头部追加数据
     */
    public void appendText(String filePath, String content, boolean header) {
        RandomAccessFile raf = null;
        FileOutputStream tmpOut = null;
        FileInputStream tmpIn = null;
        try {
            File tmp = File.createTempFile("tmp", null);
            tmp.deleteOnExit();//在JVM退出时删除

            raf = new RandomAccessFile(filePath, "rw");
            //创建一个临时文件夹来保存插入点后的数据
            tmpOut = new FileOutputStream(tmp);
            tmpIn = new FileInputStream(tmp);
            long fileLength = 0;
            if (!header) {
                fileLength = raf.length();
            }
            raf.seek(fileLength);
            /**将插入点后的内容读入临时文件夹**/

            byte[] buff = new byte[1024];
            //用于保存临时读取的字节数
            int hasRead = 0;
            //循环读取插入点后的内容
            while ((hasRead = raf.read(buff)) > 0) {
                // 将读取的数据写入临时文件中
                tmpOut.write(buff, 0, hasRead);
            }
            //插入需要指定添加的数据
            raf.seek(fileLength);//返回原来的插入处
            //追加需要追加的内容
            raf.write(content.getBytes());
            //最后追加临时文件中的内容
            while ((hasRead = tmpIn.read(buff)) > 0) {
                raf.write(buff, 0, hasRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tmpOut != null) {
                try {
                    tmpOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (tmpIn != null) {
                try {
                    tmpIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 日期转字符串
     *
     * @param date 默认时间类型 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public String dateToString(Date date) {
        return dateToString(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param format
     * @return
     */
    public String dateToString(Date date, String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format, Locale.US);
        try {
            return formater.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * json格式化
     *
     * @param uglyJSONString
     * @return
     */
    public String jsonFormatter(String uglyJSONString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(uglyJSONString);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }

}
