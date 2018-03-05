package org.watch.com.util.excel;

import org.apache.poi.hssf.usermodel.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;

public class CreateSimpleExcelToDisk {

    private final static Logger logger = LoggerFactory.getLogger(CreateSimpleExcelToDisk.class);
//    /**
//     * @功能：手工构建一个简单格式的Excel
//     */
//    private static List<OpcDatas2Model> getStudent() throws Exception
//    {
//        List list = new ArrayList();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//
//        Student user1 = new Student(1, "张三", 16, df.parse("1997-03-12"));
//        Student user2 = new Student(2, "李四", 17, df.parse("1996-08-12"));
//        Student user3 = new Student(3, "王五", 26, df.parse("1985-11-12"));
//        list.add(user1);
//        list.add(user2);
//        list.add(user3);
//
//        return list;
//    }

/***********************************************************************************************************************/
/**
 * 以下方法使用范围，标准的table格式，可带平均值
 */
    /**
     * @param name     String 文件名称
     * @param title    String 表格头部信息
     * @param data     String 表格数据
     * @param lastData String 底部平均值，也可以在确保相同格式的情况下传入其它值
     * @param response HttpServletResponse
     * @throws Exception
     */
    public static String strToJs(String name, String title, String data, String lastData, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        JSONArray jsonArray = new JSONArray();

//        标题
        String[] titles = title.split(",");
        int i = 0;
        JSONObject js = new JSONObject();
        do {
            js.put(i + "", titles[i]);
            i++;
        } while (i < titles.length);
        jsonArray.put(0, js);

//        数据
        String[] datas = data.split("]");
        for (int j = 0; j < datas.length; j++) {
            int i2 = 0;
            JSONObject js2 = new JSONObject();
            String[] s = datas[j].split(",");
            do {
                js2.put(i2 + "", s[i2]);
                i2++;
            } while (i2 < s.length);
            jsonArray.put(j + 1, js2);
        }

        //        数据
        JSONObject js3 = new JSONObject();
        if (lastData != null && !lastData.isEmpty()) {
            String[] ldatas = lastData.split(",");
            int i3 = 0;
            do {
                js3.put(i3 + "", ldatas[i3]);
                i3++;
            } while (i3 < ldatas.length);

        }

        jsonObject.put("data", jsonArray);
//生成excel
        return excels_ajax(jsonObject, js3);
    }


    /**
     * @param json JSONObject 除最底部的平均值以外的所有数据
     * @param js3  JSONObject 平均值数据
     * @return String 生成的excel的文件名称
     * @throws Exception
     */
    public static String excels_ajax(JSONObject json, JSONObject js3) throws Exception {
//        obj.has("UserName")
        if (json != null) {
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet("sheet1");
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = sheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();

            HSSFCell cell = row.createCell((short) 0);
            cell.setCellValue("序号");
            cell.setCellStyle(style);

            JSONArray jsonArray = json.getJSONArray("data");
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            int i = 0;
            do {
                String s = jsonObject.getString(i + "");
                i++;
                cell = row.createCell((short) i);
                cell.setCellValue(s);
                cell.setCellStyle(style);
            } while (jsonObject.has(i + ""));

//            不带平均值
            for (int j = 1; j < jsonArray.length(); j++) {
                HSSFRow row2 = sheet.createRow(j);
                int i2 = 0;
                if (jsonArray.length() >= 2) {
                    do {
                        if (i2 == 0) {
                            row2.createCell((short) i2).setCellValue(j);
                        } else {
                            String s = jsonArray.getJSONObject(j).getString(i2 - 1 + "");
                            HSSFCell cell2 = row2.createCell((short) i2);
                            cell2.setCellValue(s.contains("[") ? "" : s);
                            cell2.setCellStyle(style);
                        }
                        i2++;
                    } while (jsonArray.getJSONObject(j).has((i2 - 1) + ""));
                }
            }

//            平均值
            HSSFRow row2 = sheet.createRow(jsonArray.length());
            int i2 = 0;
            do {
                if (i2 == 0) {
                    row2.createCell((short) i2).setCellValue("");
                } else {
                    String s = js3.getString(i2 - 1 + "");
                    HSSFCell cell2 = row2.createCell((short) i2);
                    cell2.setCellValue(s.contains("[") ? "" : s);
                    cell2.setCellStyle(style);
                }
                i2++;
            } while (js3.has((i2 - 1) + ""));

            // 第六步，将文件存到指定位置
            String fileName = json.getString("name") + ".xls";
            try {
                FileOutputStream fout = new FileOutputStream(fileName);
                wb.write(fout);
                fout.close();
                logger.info("[" + fileName + "]---文件导出完成");
                return fileName;
            } catch (Exception e) {
                logger.info("[" + fileName + "]---文件导出失败", e);
                return null;
            }
        }
        return null;
    }
}
