package com.linc.readdata;

import java.io.FileInputStream;
import java.io.InputStream;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.widget.TextView;

import jxl.*;

public class MainActivity extends Activity {
	TextView txt = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txt = (TextView)findViewById(R.id.txt_show);
		txt.setMovementMethod(ScrollingMovementMethod.getInstance());
		readExcel();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void readExcel() {
		  try {
		   /**
		    * 后续考虑问题,比如Excel里面的图片以及其他数据类型的读取
		    **/
		   InputStream is = new FileInputStream(Environment.getExternalStorageDirectory().getPath() +"/"+"test.txt");
		   //Workbook book = Workbook.getWorkbook(new File("mnt/sdcard/MyExcel.xlsx"));
		   Workbook book = Workbook.getWorkbook(is);
		   
		   int num = book.getNumberOfSheets();
		   txt.setText("the num of sheets is " + num+ "\n");
		   // 获得第一个工作表对象
		   Sheet sheet = book.getSheet(0);
		   int Rows = sheet.getRows();
		   int Cols = sheet.getColumns();
		   txt.append("the name of sheet is " + sheet.getName() + "\n");
		   txt.append("total rows is " + Rows + "\n");
		   txt.append("total cols is " + Cols + "\n");
		   for (int i = 0; i < Cols; ++i) {
		    for (int j = 0; j < Rows; ++j) {
		     // getCell(Col,Row)获得单元格的值
		     	txt.append("contents:" + sheet.getCell(i,j).getContents() + "\n");
		    }
		   }
		   book.close();
		  } catch (Exception e) {
		   System.out.println(e);
		  }
		}

}
