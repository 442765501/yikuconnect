package com.yiku.common.util;

import org.jdiy.util.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Title: 检索基本类
 * </p>
 * <p>
 * Description:常用工具
 * </p>
 * <p>
 * Copyright: Copyright (c) suneasy 2003
 * </p>
 * <p>
 * Company: suneasy
 * </p>
 * 
 * @author wsu
 * @version $Header:
 *          /cvsroot/bcnsearch/src/com/suneasy/search/util/Tools.java,v
 *          1.1.1.1 2003/12/25 17:34:56 yjs Exp $
 */

public class Tools {

	/**
	 * 截NULL
	 * 
	 * @param
	 * @return
	 */

	public static void main(String[] args) {
		//String s = random(20,4);
	}
	
	public static String RmNull(String str) { // remove null string
		if (str == null || str.equals("")) {
			str = "";
		}
		return str;
	}
	
	public static String getIp() throws UnknownHostException { // remove null string
		String str="";
		str = InetAddress.getLocalHost().getHostAddress();
		return str;
	} //
	
	public static String random(int r,int n){		
		String str="";
		 int[] intRet = new int[n]; 
         int intRd = 0; //存放随机数
         int count = 0; //记录生成的随机数个数
         int flag = 0; //是否已经生成过标志
         while(count<n){
              Random rdm = new Random(System.currentTimeMillis());
              intRd = Math.abs(rdm.nextInt())%r+1;
              for(int i=0;i<count;i++){
                  if(intRet[i]==intRd){
                      flag = 1;
                      break;
                  }else{
                      flag = 0;
                  }
              }
              if(flag==0){
                  intRet[count] = intRd;
                  count++;
              }
	     }
	    for(int t=0;t<n;t++){
	        //System.out.println(t+"->"+intRet[t]);
	        str += intRet[t]+",";
	    }
        str = str.substring(0, str.length()-1);
	    //System.out.println(str);
		return str;
	}
	
	public static String getProper(String str) { // remove null string
		String path = "";
		String value = "";
		try {
			path = Fs.getResource("../../").getPath();
			path = path + "config/system.properties";
			//System.out.println(path);
			Properties dbProps = new Properties();
			try {
				dbProps.load(new FileInputStream(path));
				value = RmNull(dbProps.getProperty(str));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;
	}
	
	public static void setProper(String parastr,String str) { // remove null string
		String path = "";
		String value = "";
		try {
			path = Fs.getResource("../../").getPath();
			path = path + "config/system.properties";
			//System.out.println(path);
			Properties dbProps = new Properties();
			try {
				dbProps.load(new FileInputStream(path));
				value = RmNull(dbProps.getProperty(str));
				
				OutputStream fos = new FileOutputStream(path);
	            dbProps.setProperty(parastr, str);
	            //以适合使用 load 方法加载到 Properties 表中的格式，
	            //将此 Properties 表中的属性列表（键和元素对）写入输出流
	            dbProps.store(fos, "Update '"+parastr+"' "+str);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getDate(String fromatstr) { // remove null string "yyyy-MM-dd HH:mm:ss"
		String	today="";
		
		Calendar Datenow=Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(fromatstr);
	    today = formatter.format(Datenow.getTime());
		
	    return today;
	}
	
	public static String getDate() { // remove null string "yyyy-MM-dd HH:mm:ss"
		String	today="";
		
		Calendar Datenow=Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    today = formatter.format(Datenow.getTime());
		
	    return today;
	}
	
	//Y（年） M（月） D（日） h（时） m（分） s（秒） w(周)
	public static String getDate(String date,String interval,int time) throws ParseException { // remove null string "yyyy-MM-dd HH:mm:ss"
		String	today=""; 
		Fn.interval Y = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date datetime= new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse(date);
	    //Y（年） M（月） D（日） h（时） m（分） s（秒） w(周)
	    Y=Fn.interval.valueOf(interval);
	    datetime = Fn.dateadd(Y, time, datetime);	    
	    today = formatter.format(datetime);		
	    return today;
	}
	
	//Y（年） M（月） D（日） h（时） m（分） s（秒） w(周)
	public static int getDatenum(String date1,String date2,String interval) throws ParseException { // remove null string "yyyy-MM-dd HH:mm:ss"
		int	today=0; 
		Fn.interval Y = null;
		Date datetime1= new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse(date1);
		Date datetime2= new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse(date2);
	    //Y（年） M（月） D（日） h（时） m（分） s（秒） w(周)
	    Y=Fn.interval.valueOf(interval);	    
	    today = Fn.datediff(Y, datetime2, datetime1);	    	
	    return today;
	}
	
	//
	public static String cut(String str,int num) throws ParseException { // remove null string "yyyy-MM-dd HH:mm:ss"
		String	value=""; 
		value=Txt.cut(str,num);
		value=value.replace("…","");
	    return value;
	}
	
	//
	public static String cut(String str,int num1,int num2) throws ParseException { // remove null string "yyyy-MM-dd HH:mm:ss"
		String	value="";
		String	value1="";
		String	value2=""; 
		
		value1=Txt.cut(str,num1);
		value1=value1.replace("…","");		
		value2=Txt.cut(str,num2);
		value2=value2.replace("…","");		
		value=value2.replace(value1,"");
		
	    return value;
	}


	public static String bianma(String str) { // remove null string
		//编码
		try {
			str = URLEncoder.encode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}


	public static String bianma(String str,String enc) { // remove null string
		//编码
		try {
			str = URLEncoder.encode(str,enc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	public static String jiema(String str,String enc) { // remove null string
		//解码
		try {
			str = URLDecoder.decode(str,enc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}
	
	
	public static String urlrewrite(String str) { // remove null string
		str = str.replaceAll("showinfo\\.jsp\\?id=([0-9]+)&pn=([0-9]+)", "show_$1_$2.html");   
		str = str.replaceAll("showinfo\\.jsp\\?id=([0-9]+)", "show_$1.html"); 
		str = str.replaceAll("info\\.jsp\\?id=([0-9]+)&pn=([0-9]+)", "info_$1_$2.html");   
		str = str.replaceAll("info\\.jsp\\?id=([0-9]+)", "info_$1.html"); 
		str = str.replaceAll("list\\.jsp\\?c=([0-9]+)&pn='\\+s", "list_$1_'\\+s\\+'.html'");
		str = str.replaceAll("list\\.jsp\\?c=([0-9]+)&pn=([0-9]+)", "list_$1_$2.html"); 
		str = str.replaceAll("list\\.jsp\\?c=([0-9]+)", "list_$1.html");  
		str = str.replaceAll("item\\.jsp\\?id=([0-9]+)", "item_$1.html");
		str = str.replaceAll("list_xintie\\.jsp\\?c=([0-9]+)&pn=([0-9]+)", "xintie_$1_$2.html"); 
		str = str.replaceAll("list_xintie\\.jsp\\?c=([0-9]+)", "xintie_$1.html");
		str = str.replaceAll("list_jinghua\\.jsp\\?c=([0-9]+)&pn=([0-9]+)", "jinghua_$1_$2.html"); 
		str = str.replaceAll("list_jinghua\\.jsp\\?c=([0-9]+)", "jinghua_$1.html");
		str = str.replaceAll("list_xihuan\\.jsp\\?c=([0-9]+)&pn=([0-9]+)", "xihuan_$1_$2.html"); 
		str = str.replaceAll("list_xihuan\\.jsp\\?c=([0-9]+)", "xihuan_$1.html");
		str = str.replaceAll("user_tiezi\\.jsp\\?u=([0-9]+)&c=([0-9]+)&pn=([0-9]+)", "t_$1_$2_$3.html"); 
		str = str.replaceAll("user_tiezi\\.jsp\\?u=([0-9]+)&c=([0-9]+)", "t_$1_$2.html");
		return str;
	}
	

	/**
	 * 过滤 $%^*?+_',.等符号
	 * 
	 * @param str
	 * @return
	 */
	public static String RmFilter(String str) { // remove null string
		if (str == null || str.equals("")) {
			str = "";
		}
		String regEx = "[$%^*?+',.]"; // 表示一个或多个a\p{Punct}

		Pattern p = Pattern.compile(regEx);

		Matcher m = p.matcher(str);

		str = m.replaceAll("");

		return str;
	}

	/**
	 * 读文件到缓冲里面
	 * 
	 * @param filePath
	 * @return
	 */
	public static StringBuffer readFile(String filePath) {
		// StringBuffer bStr = new StringBuffer();
		// String LineStr = "";
		// try {
		// System.setProperty("user.language", "zh");
		// System.setProperty("file.encoding", "GBK");
		// java.io.FileReader fr = new java.io.FileReader(filePath);
		//
		// java.io.BufferedReader br = new java.io.BufferedReader(
		// fr);
		// while ( (LineStr = br.readLine()) != null) {
		// bStr.append(LineStr +
		// System.getProperty("line.separator"));
		// }
		// br.close();
		// fr.close();
		// }
		// catch (Exception e) {
		// System.out.println(e.getMessage());
		// System.out.println("不能读取正文！");
		// }
		// return bStr;
		try {
			return readFile(filePath, "GBK");
		} catch (IOException ex) {
			System.out.println(ex);
			return null;
		}
	}

	public static void writeFile(String filePath, String contStr) {
		writeFile(filePath, contStr, "GBK");
	}

	public static void writeFile(String filePath, String contStr, String encoder) {
		try {
			FileOutputStream afile = new FileOutputStream(
					filePath);
			afile.write(contStr.getBytes(encoder));
			afile.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Can't Written！");
		}
	}

	public static StringBuffer readFile(String filePath, String encoder)
			throws IOException {
		try {

			InputStream fis = new FileInputStream(filePath);
			byte[] b_all = new byte[fis.available()];
			byte[] readBytes = new byte[10240];
			int readInt, start = 0;
			while ((readInt = fis.read(readBytes)) != -1) {
				System.arraycopy(readBytes, 0, b_all, start, readInt);
				start += readInt;
			}
			fis.close();
			String str = new String(b_all, encoder);
			return new StringBuffer(str);
		} catch (IOException e) {
			System.out.println("read  the file " + filePath + " err:"
					+ e.toString());
			throw e;
		}

	}

	

	/**
	 * 日期格式化
	 * 
	 * @param date
	 *            需要格式化日期
	 * @param pattern
	 *            需要格式话的PATTERN
	 * @return 返回格式化的效果
	 */
	public static String dateToStr(Date date, String pattern) {
		SimpleDateFormat formater = new SimpleDateFormat(
				pattern);
		return formater.format(date);
	}

	

	


	/**
	 * 替换字符窜，虽然1。4有功能，但为了能再1。2能用所以用此函数
	 * 
	 * @param source
	 * @param oldString
	 * @param newString
	 * @return
	 */
	public static String Replace(String source, String oldString,
			String newString) {
		StringBuffer output = new StringBuffer(); // define String Buffer
		int lengthOfSource = source.length(); // 源字符串长度
		int lengthOfOld = oldString.length(); // 老字符串长度
		int posStart = 0; // 开始搜索位置
		int pos; // 搜索到老字符串的位置
		while ((pos = source.indexOf(oldString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));

			output.append(newString);
			posStart = pos + lengthOfOld;
		}

		if (posStart < lengthOfSource) {
			output.append(source.substring(posStart));
		}

		return output.toString();
	}

	

	public static void mkDir(String filePath) {
		File file = new File(filePath);
		try {
			file.mkdirs();
			System.out.println("make dir ok!");
		} catch (Exception e) {
			System.out.println("mkdir error:" + e);
		}
	}

	

	/**
	 * 回车换行
	 * 
	 * @param Content
	 * @return
	 */
	public static String addBR(String Content) {
		String makeContent = "";
		StringTokenizer strToken = new StringTokenizer(Content, "\n");
		while (strToken.hasMoreTokens()) {
			makeContent = makeContent + strToken.nextToken() + "<br>";
		}
		makeContent = makeContent.replaceAll("  ", "　");
		return makeContent;
	}

	/**
	 * 字符集转换
	 * 
	 * @param s
	 * @return
	 */
	public static String ISO8859_1ToGBK(String s) {
		try {
			//s = new String(s.getBytes("ISO-8859-1"), "GBK");
		} catch (Exception e) {
			System.out.println("ISO8859_1ToGBK" + e);
		}
		return s;
	}

	/**
	 * 字符集转换
	 * 
	 * @param s
	 * @return
	 */
	public static String GBKToISO8859_1(String s) {
		try {
			//s = new String(s.getBytes("GBK"), "ISO-8859-1");
		} catch (Exception e) {
			System.out.println("GBKToISO8859_1" + e);
		}
		return s;
	}

	

	/**
	 * 替换字符缓冲中所有的标签
	 * 
	 * @param strb
	 *            缓冲
	 * @param sStr
	 *            原字符
	 * @param nString
	 *            新字符
	 * @return
	 */
	public static StringBuffer replaceAll(StringBuffer strb, String sStr,
			String nString) {
		int start = 0;
		while ((start = strb.indexOf(sStr, start)) > -1) {
			strb.replace(start, start + sStr.length(), nString);
			start = start + nString.length();
		}
		return strb;
	}

	/**
	 * 格式话法规，论文，法律之星专用
	 * 
	 * @param strb
	 *            需要格式的字符缓冲
	 * @return
	 */
	public static StringBuffer txtFormat(StringBuffer strb) {
		String LineStr = "";
		int s0 = 0;
		int s1 = 0;
		int s2 = 0;
		int s3 = 0;
		StringBuffer tmpBuffer = new StringBuffer();
		String lineSpr = System.getProperty("line.separator");
		while (strb.indexOf(lineSpr) > -1) {
			LineStr = strb.substring(0, strb.indexOf(lineSpr)); // 取一行
			strb.delete(0, strb.indexOf(lineSpr) + lineSpr.length()); // 删除这一行
			s0 = LineStr.indexOf("    "); // 四个空格
			s1 = LineStr.indexOf("      "); // 六个
			s2 = LineStr.indexOf("                                    "); // 36个
			s3 = LineStr.indexOf("<pre>");

			if (s3 == 0) {
				tmpBuffer.append(LineStr);
				while (strb.indexOf(lineSpr) > -1) {
					LineStr = strb.substring(0, strb.indexOf(lineSpr)); // 取一行
					strb.delete(0, strb.indexOf(lineSpr) + lineSpr.length()); // 删除这一行
					s3 = LineStr.indexOf("</pre>");
					tmpBuffer.append(LineStr + lineSpr);
					if (s3 == 0) {
						break;
					}
				}
			}

			if (s0 == 0) {
				if (s1 == 0) {
					if (s2 == 0) {
						tmpBuffer.append("<div align=right>" + LineStr.trim()
								+ "</div>");
					} else {
						tmpBuffer.append("<div  align=center><strong>"
								+ LineStr.trim() + "</strong></div>");
					}
				} else {
					tmpBuffer.append("　　" + LineStr.trim() + lineSpr + "<br>");
				}
			} else {
				tmpBuffer.append(LineStr + lineSpr + "<br>");
			}

			// tmpBuffer.append(LineStr);
		}
		return tmpBuffer;
	}

	

	/**
	 * 删除网页标签,包括<> 及里面的字符,只留中文
	 * 
	 * @param
	 * @return String
	 */
	public static String rmHtml(String str) {
		if (str == null || str.equals("")) {
			str = "";
		}else{
		
		int start = 0;
		int end = 0;
		StringBuffer strb = new StringBuffer(str);
		while ((start = strb.indexOf("<")) >= 0) {
			// start = orgtxt.indexOf("<");
			end = strb.indexOf(">", start);
			if (start != -1 && end != -1) {
				strb.delete(start, end + 1);
			}
			start++;
			}
			str = strb.toString();
		}
		
		String regEx = "[=\\'^\"./%;&<>(A-Z)(a-z)]"; // 表示一个或多个a\p{Punct}

		Pattern p = Pattern.compile(regEx);

		Matcher m = p.matcher(str);

		str = m.replaceAll("");
		str = str.replaceAll(" ", "");
		return str;
	}

	// 过滤网页代码
	public static String rmHtml2(String page) {
		int start = 0;
		int end = 0;
		StringBuffer strb = new StringBuffer(page);
		while ((start = strb.indexOf("<")) >= 0) {
			// start = orgtxt.indexOf("<");
			end = strb.indexOf(">", start);
			if (start != -1 && end != -1) {
				strb.delete(start, end + 1);
			}
			start++;
		}
		return strb.toString();
	}

	public static void test(int arr[]) {
		int[] tempArr = new int[] { 1, 23, 4, 100, 222, 21 };
		// arr = new int [tempArr.length];
		for (int i = 0; i < tempArr.length; i++) {
			arr[i] = tempArr[i];
		}
	}

	/**
	 * 
	 * @param args
	 */

	/**
	 * 生成图片所略图 oldpath原图地址 savepath缩略图地址 pic_w缩略图宽度
	 * 
	 * @param oldpath
	 * @param savepath
	 * @float pic_w
	 * @return
	 */

	public static void toZipJpg(String oldpath, String savepath, float pic_w)
			throws Exception {

		File objPic = new File(savepath); // 判断有无图片,也是缩略图保存路径
		String picname = "";

		try {

			if (oldpath.indexOf("/") > -1) {
				picname = oldpath.substring(oldpath.lastIndexOf("/") + 1,
						oldpath.length()); // linux路径判断
				savepath = savepath.substring(0, savepath.lastIndexOf("/"));
			} else {
				picname = oldpath.substring(oldpath.lastIndexOf("\\") + 1,
						oldpath.length()); // win路径判断
				savepath = savepath.substring(0, savepath.lastIndexOf("\\"));
			}

			if (!objPic.exists()) { // 如果缩略图找不到

				File file = new File(oldpath); // 读入原图文件

				File saveFile = new File(savepath); // 保存地址判断
				if (!saveFile.exists()) {
					// System.out.println("没有文件夹");
					saveFile.mkdir();
				}
				Image src = null;
				src = javax.imageio.ImageIO.read(file); // 构造Image对象

				float tagsize = pic_w;
				int old_w = src.getWidth(null); // 得到源图宽
				int old_h = src.getHeight(null);
				int new_w = 0;
				int new_h = 0; // 得到源图长
				int tempsize;
				float tempdouble;
				if (old_w > old_h) {
					tempdouble = old_w / tagsize;
				} else {
					tempdouble = old_h / tagsize;
				}
				new_w = Math.round(old_w / tempdouble);
				new_h = Math.round(old_h / tempdouble); // 计算新图长宽
				BufferedImage tag = new BufferedImage(new_w, new_h,
						BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(src, 0, 0, new_w, new_h, null); // 绘制缩小后的图

				javax.imageio.ImageIO.write(tag, "jpg", new File(saveFile,
						picname));
				tag = null;

			}
		} catch (IOException ex) {
			System.out.println("toZipJpg is error");
		}

	}

	public static int isNumber(String s){
		int tempid=0;
		String Para="";
		try{
			if(s.indexOf(".")>-1) {
				int start = 0;
				int end = 0;
				start = s.indexOf(".");
				//System.out.println("------"+s);
				//System.out.println("------"+start);
				end = s.length();
				Para = s.substring(start, end);
				//System.out.println("------"+Para);
				s = s.replaceAll(Para,"");
				//System.out.println("------"+s);
				tempid=Integer.parseInt(s);
			}else tempid=Integer.parseInt(s);
		}catch(NumberFormatException e){
			tempid=0;
		}	return tempid;
	}
	
	public static String getPara(String urlstr, String s){
		String Para="";
		String str2[]={};
		str2 = urlstr.split("&");
		for (int p=0;p<str2.length;p++) {
			if(str2[p].indexOf(s+"=")>-1) {
				int start = 0;
				int end = 0;
				StringBuffer strb = new StringBuffer(str2[p]);
				start = strb.indexOf(s+"=");
				end = str2[p].length();
				Para = str2[p].substring(start, end);
				
				Para = Para.replaceAll(s+"=","");
				
				//System.out.println(urlstr+"------"+Para);
			}
		}
		return Para;
	}
	
	public static String getDomain(String urlstr){
		String Para="";
		String str2[]={};
		urlstr = urlstr.replaceAll("/ROOT/","");
		urlstr = urlstr.replaceAll("/ROOT","");
		str2 = urlstr.split("/");
		Para = str2[str2.length-1];
		return Para;
	}
	
	//只支持元，不支持角、分
	public static String NumberToCN(String m){
	    String num = "零壹贰叁肆伍陆柒捌玖";
	    String dw = "圆拾佰仟万亿";
		String result ="";
	    //String mm[] = null;
	    //mm = m.split("/.");
	    String money = m;

	    //result = num.charAt(Integer.parseInt("" + mm[1].charAt(0))) + "角" +  num.charAt(Integer.parseInt("" + mm[1].charAt(1))) + "分";
	    for (int i = 0; i < money.length(); i++) {
	      String str = "";
	      int n = Integer.parseInt(money.substring(money.length() - i - 1,money.length() - i));
	      str = str + num.charAt(n);
	      if (i == 0) {
	        str = str + dw.charAt(i);
	      }
	      else if ( (i + 4) % 8 == 0) {
	        str = str + dw.charAt(4);
	      }
	      else if (i % 8 == 0) {
	        str = str + dw.charAt(5);
	      }
	      else {
	        str = str + dw.charAt(i % 4);
	      }
	      result = str + result;
	    }
	    result = result.replaceAll("零([^亿万圆角分])", "零");
	    result = result.replaceAll("亿零+万","亿零");
	    result = result.replaceAll("零+", "零");
	    result = result.replaceAll("零([亿万圆])", "$1");
	    result =result.replaceAll("壹拾","拾");
	    
	    return result;
	}
	
	public static String jia(String s1, String s2){
		String s="0.00";
		double heji = 0.00;
		BigDecimal b1 = new BigDecimal(s1); 
   		BigDecimal b2 = new BigDecimal(s2);
		heji = b1.add(b2).doubleValue(); 
		//s =  String.valueOf(heji); 
		s =  Double.toString(heji);
		return s;
	}
	
	public static String jian(String s1, String s2){
		String s="0.00";
		double heji = 0.00;
		BigDecimal b1 = new BigDecimal(s1); 
   		BigDecimal b2 = new BigDecimal(s2);
		heji = b1.subtract(b2).doubleValue(); 
		//s =  String.valueOf(heji); 
		s =  Double.toString(heji); 
		return s;
	}
	
	public static String cheng(String s1, String s2){
		String s="0.00";
		double heji = 0.00;
		BigDecimal b1 = new BigDecimal(s1); 
   		BigDecimal b2 = new BigDecimal(s2);
		heji = b1.multiply(b2).doubleValue(); 
		//s =  String.valueOf(heji); 
		s =  Double.toString(heji); 
		return s;
	}
	
	public static String chu(String s1, String s2){
		String s="0.00";
		double heji = 0.00;
		BigDecimal b1 = new BigDecimal(s1); 
   		BigDecimal b2 = new BigDecimal(s2);
		//heji = b1.add(b2).doubleValue();  如果除不尽，保留两位 4 舍 5 入
		heji = b1.divide(b2,2, BigDecimal.ROUND_HALF_EVEN).doubleValue(); 
		//s =  String.valueOf(heji); 
		s =  Double.toString(heji);
		return s;
	}
	
	public static String chu(String s1, String s2,int len){
		String s="0.00";
		double heji = 0.00;
		BigDecimal b1 = new BigDecimal(s1); 
   		BigDecimal b2 = new BigDecimal(s2);
		//heji = b1.add(b2).doubleValue();  如果除不尽，保留两位 4 舍 5 入
		heji = b1.divide(b2,len, BigDecimal.ROUND_HALF_EVEN).doubleValue(); 
		//s =  String.valueOf(heji); 
		s =  Double.toString(heji);
		return s;
	}

}
