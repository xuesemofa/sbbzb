package org.watch.com.util.files;

import java.io.*;

public class FileTool {
    /**
     * 下载文件
     *
     * @param fileName --文件完整路径
     * @param response
     */
    public static void downloadFile(
            String fileName,
            javax.servlet.http.HttpServletResponse response) {
        try {
//            new String(fileName.getBytes("utf-8"), "iso8859-1")
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition",
                    "attachment;filename="
                            + new String(fileName.getBytes("utf-8"), "iso8859-1"));
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            OutputStream os;
            try {
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
                int i = bis.read(buff);
                while (i != -1) {
                    os.write(buff, 0, buff.length);
                    os.flush();
                    i = bis.read(buff);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 新建目录
     *
     * @param folderPath 目录
     * @return 返回目录创建后的路径
     */
    public static void createFolder(String folderPath) {
        try {
            File myFilePath = new File(new String(folderPath.getBytes("UTF-8"), "UTF-8"), "UTF-8");
            if (!myFilePath.exists()) {
                boolean b = myFilePath.mkdir();
                System.out.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除空目录
     *
     * @param folderPath 目录
     * @return 返回目录创建后的路径
     */
    public static void deleteFolder(String folderPath) {
        try {
            File myFilePath = new File(new String(folderPath.getBytes("UTF-8"), "UTF-8"), "UTF-8");
            if (myFilePath.exists()) {
                boolean b = myFilePath.delete();
                System.out.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
