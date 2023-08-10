package com.example.catalogboardgame.DashboardAdmin;



import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class ExcelUtils {


    private static final String TAG="TAG";

    public static void export()
    {
        File sd= Environment.getExternalStorageDirectory();
        String csv="/DataUSsderss.csv";

        File directory = new File(sd.getAbsolutePath());

        //create directory if not exist
        String state = Environment.getExternalStorageState();
        Log.d("TAG", "export: "+state);

        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            Log.d("TAG", "export: "+state);
            return;

        }
        try {

            //file path
            File file = new File(csv);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale(Locale.US.getLanguage(), Locale.US.getCountry()));
//            WritableWorkbook workbook;
//            workbook = Workbook.createWorkbook(file);
//            WritableWorkbook writableWorkbook;
//            writableWorkbook =Workbook.createWorkbook(file);
            file.canWrite();

            Log.d(TAG, "export: "+file);

//            //Excel sheetA first sheetA
//            WritableSheet sheetA = workbook.createSheet("sheet A", 0);
//
//            // column and row titles
//            sheetA.addCell(new Label(0, 0, "sheet A 1"));
//            sheetA.addCell(new Label(1, 0, "sheet A 2"));
//            sheetA.addCell(new Label(0, 1, "sheet A 3"));
//            sheetA.addCell(new Label(1, 1, "sheet A 4"));
//
//            //Excel sheetB represents second sheet
//            WritableSheet sheetB = workbook.createSheet("sheet B", 1);
//
//            // column and row titles
//            sheetB.addCell(new Label(0, 0, "sheet B 1"));
//            sheetB.addCell(new Label(1, 0, "sheet B 2"));
//            sheetB.addCell(new Label(0, 1, "sheet B 3"));
//            sheetB.addCell(new Label(1, 1, "sheet B 4"));
//
//            // close workbook
//            workbook.write();
//            workbook.close();
                FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), csv));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
