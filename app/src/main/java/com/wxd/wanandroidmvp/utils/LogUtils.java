package com.wxd.wanandroidmvp.utils;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.wxd.wanandroidmvp.app.MyApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;

public class LogUtils {

    private static final String Tag = MyApplication.application.getPackageName();
    private volatile static LogUtils logUtils;
    private String TOP_LINE = "" +
            "\n^^^^^^^^^^^^^less code,less bug^^^^^^^^^^^^^^\n" +
            "                   _ooOoo_\n" +
            "                  o8888888o\n" +
            "                  88\" . \"88\n" +
            "                  (| -_- |)\n" +
            "                  O\\  =  /O\n" +
            "               ____/`---'\\____\n" +
            "             .'  \\\\|     |//  `.\n" +
            "            /  \\\\|||  :  |||//  \\\n" +
            "           /  _||||| -:- |||||-  \\\n" +
            "           |   | \\\\\\  -  /// |   |\n" +
            "           | \\_|  ''\\---/''  |   |\n" +
            "           \\  .-\\__  `-`  ___/-. /\n" +
            "         ___`. .'  /--.--\\  `. . __\n" +
            "      .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n" +
            "     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
            "     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n" +
            "======`-.____`-.___\\_____/___.-`____.-'======\n" +
            "                   `=---='\n" +
            "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" +
            "            ????????????       ??????BUG\n" +
            "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n";
    private String TOP_BORDER = "?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????";
    private String BOTTOM_BORDER = "?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????";
    private String logDir = "";//????????????????????????
    private boolean debug = true;//????????????log
    private boolean saveSD = false;//?????????log???sd???
    private int CHUNK_SIZE = 120; //???????????????
    private long logSize = 1 * 1024 * 1024L;//??????log???????????? k
    private ExecutorService executor = Executors.newFixedThreadPool(1);

    private LogUtils() {
        Log.e(Tag, TOP_LINE);
        initLogFile();
    }

    public static LogUtils getInstance() {
        if (logUtils == null) {
            synchronized (LogUtils.class) {
                if (logUtils == null) {
                    logUtils = new LogUtils();
                }
            }
        }
        return logUtils;
    }

    private void initLogFile() {
        logDir = getRootDir() + MyApplication.application.getPackageName() + "_log";
        mkDir(logDir);
    }

    public void v(@NonNull Object objMsg) {
        v(Tag, objMsg);
    }

    public void d(@NonNull Object objMsg) {
        d(Tag, objMsg);
    }

    public void i(@NonNull Object objMsg) {
        i(Tag, objMsg);
    }

    public void w(@NonNull Object objMsg) {
        w(Tag, objMsg);
    }

    public void e(@NonNull Object objMsg) {
        e(Tag, objMsg);
    }

    public void v(String tag, @NonNull Object objMsg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.v(tag, msgFormat(stackstr, objMsg));
        }
        if (saveSD) {
            saveToSd(stackstr, objMsg);
        }
    }

    public void d(String tag, @NonNull Object objMsg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.d(tag, msgFormat(stackstr, objMsg));
        }
        if (saveSD) {
            saveToSd(stackstr, objMsg);
        }
    }

    public void i(String tag, @NonNull Object objMsg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.i(tag, msgFormat(stackstr, objMsg));
        }
        if (saveSD) {
            saveToSd(stackstr, objMsg);
        }
    }

    public void w(String tag, @NonNull Object objMsg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.w(tag, msgFormat(stackstr, objMsg));
        }
        if (saveSD) {
            saveToSd(stackstr, objMsg);
        }
    }


    public void e(String tag, @NonNull Object objMsg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.e(tag, msgFormat(stackstr, objMsg));
        }
        if (saveSD) {
            saveToSd(stackstr, objMsg);
        }
    }



    /**
     * ????????????bebug??????
     *
     * @param debug
     * @return
     */
    public LogUtils debug(boolean debug) {
        this.debug = debug;
        return this;
    }


    public boolean isDebug() {
        return debug;
    }

    /**
     * ???????????????sd???
     *
     * @param saveSD
     * @return
     */
    public LogUtils saveSD(boolean saveSD) {
        this.saveSD = saveSD;
        return this;
    }

    /**
     * ??????log????????????
     *
     * @param logSize
     * @return
     */
    public LogUtils setLogSize(int logSize) {
        this.logSize = logSize;
        return this;
    }

    /**
     * ??????log????????????
     *
     * @param logDir
     * @return
     */
    public LogUtils setlogDir(String logDir) {
        if (!logDir.isEmpty()) {
            this.logDir = logDir;
        }
        return this;
    }

    public String getLogFileDir() {
        return logDir;
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
            boolean isLogMethod = stackTraceElement.getClassName().equals(LogUtils.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }

    private String msgFormat(String stackstr, Object objMsg) {
        String msg = "";
        if (objMsg instanceof String) {
            msg = objMsg.toString();
        } else {
            msg = new Gson().toJson(objMsg);
            msg = jsonFormatter(msg);
        }
        byte[] bytes = new byte[0];
        try {
            bytes = msg.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int length = bytes.length;
        String newMsg = TOP_BORDER + "\n" + dateToString(new Date()) + "\n" + stackstr;
        if (length > CHUNK_SIZE) {
            int i = 0;
            while (i < length) {
                int count = Math.min(length - i, CHUNK_SIZE);
                String tempStr = new String(bytes, i, count);
                newMsg += "\n" + tempStr;
                i += CHUNK_SIZE;
            }
        } else {
            newMsg += "\n" + msg;
        }
        newMsg += "\n" + BOTTOM_BORDER;
        return newMsg;

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

    /**
     * ???????????????
     */
    public String getRootDir() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            return "";
        }
    }

    /**
     * ????????????????????????
     * dirPath ????????????
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
     * ????????????
     * filePath ????????????
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
     * ??????????????????
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
     * ?????????????????????
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
     * ????????????
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
     * ????????????
     *
     * @param filePath
     * @param content
     * @param header   ???????????????????????????
     */
    public void appendText(String filePath, String content, boolean header) {
        RandomAccessFile raf = null;
        FileOutputStream tmpOut = null;
        FileInputStream tmpIn = null;
        try {
            File tmp = File.createTempFile("tmp", null);
            tmp.deleteOnExit();//???JVM???????????????

            raf = new RandomAccessFile(filePath, "rw");
            //?????????????????????????????????????????????????????????
            tmpOut = new FileOutputStream(tmp);
            tmpIn = new FileInputStream(tmp);
            long fileLength = 0;
            if (!header) {
                fileLength = raf.length();
            }
            raf.seek(fileLength);
            /**?????????????????????????????????????????????**/

            byte[] buff = new byte[1024];
            //????????????????????????????????????
            int hasRead = 0;
            //?????????????????????????????????
            while ((hasRead = raf.read(buff)) > 0) {
                // ???????????????????????????????????????
                tmpOut.write(buff, 0, hasRead);
            }
            //?????????????????????????????????
            raf.seek(fileLength);//????????????????????????
            //???????????????????????????
            raf.write(content.getBytes());
            //????????????????????????????????????
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
     * ??????????????????
     *
     * @param date ?????????????????? yyyy-MM-dd HH:mm:ss
     * @return
     */
    public String dateToString(Date date) {
        return dateToString(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * ??????????????????
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
     * json?????????
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
