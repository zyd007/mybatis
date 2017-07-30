package qhd.zy.shop.Util;


import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FilenameUtils;

import qhd.zy.shop.model.ValidateForm;
import qhd.zy.shop.model.ValidateType;
public class RequestUtil {
	public final static String[] allow= {"jpg","bmp","gif","png"};
	public final static String PATH="D:\\JAVA\\01_J2SE视频\\web\\java_web\\mybatiszyweb01\\WebContent\\";
	@SuppressWarnings("unchecked")
	public static void FileUpload(String fname,String fieldname,byte[]bt,HttpServletRequest request) throws IOException{
		FileOutputStream fos=null;
		try {
			//有数据才上传文件，没有就返回null或“”
			if(bt.length>0){
				//对于IE而言，上传的文件获取完整的绝对路径，此时就需要仅仅获取绝对路径中的文件名
			fname=FilenameUtils.getName(fname);
			//得到后缀
			String exs=FilenameUtils.getExtension(fname);
			//检查后缀是否是数组allow中的
			Boolean b=Checktype(exs);
			if(b){
				fos=new FileOutputStream(PATH+"img/"+fname);
				fos.write(bt, 0, bt.length);
			}else{
				//保存错误并返回错误
				Map<String,String> errorMsg = (Map<String,String>)request.getAttribute("errorMsg");
				//这个fieldname为img
				errorMsg.put(fieldname, "图片类型必须是jpg,bmp,png,gif");
			}
}
		} finally{
			if(fos!=null)fos.close();
		}
	}
	private static Boolean Checktype(String exs) {
		for(String s:allow){
			if(s.equals(exs)){
				return true;
			}
		}
		return false;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Boolean Vaildate(Class clz,HttpServletRequest req){
		//得到所有定义的属性
		 Field[]f=clz.getDeclaredFields();
		 //存储错误一般用MAP
		 Map<String, String>errorMsg=(Map<String, String>)req.getAttribute("errorMsg");
		 //默认验证成功
		 Boolean flag=true;
		 for(Field fe:f){
			 //验证那个属性有注释类
			 if(fe.isAnnotationPresent(ValidateForm.class)){
				 //拿出注释类
				 ValidateForm v=fe.getAnnotation(ValidateForm.class);
				 //拿出枚举型
				 ValidateType va=v.type();
				 //判断是否为空-长度-数字(只要一个为错误整体验证错误)
				 if(va==ValidateType.NotNull){
					 Boolean b=NotNull(fe.getName(), req);
					 if(!b){
						 flag=false;
						 errorMsg.put(fe.getName(),v.errorMsg());
					 }
				 }else if(va==ValidateType.Length){
					 Boolean b=length(fe.getName(),v.value(),req);
					 if(!b){
						 flag=false;
						 errorMsg.put(fe.getName(),v.errorMsg());
					 }
				 }else if(va==ValidateType.Number){
					 Boolean b=Number(fe.getName(),req);
					 if(!b){
						 flag=false;
						 errorMsg.put(fe.getName(),v.errorMsg());
					 }
				 }
			 }
		 }
		 return flag;
	}
	//判断参数是否为数字
	private static Boolean Number(String param, HttpServletRequest req) {
		String name=req.getParameter(param);
		try {
			Double.parseDouble(name);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	//判断参数长度是否大于等于6
	private static Boolean length(String param, String value,
			HttpServletRequest req) {
		String name=req.getParameter(param);
		if(name.length()<6){
			return false;
		}
		return true;
	}
	//判断是否为空
	private static Boolean NotNull(String param,HttpServletRequest req){
		//如果没有这个参数就算验证成功
		if(!req.getParameterMap().containsKey(param)){
			return true;
		}
		String name=req.getParameter(param);
		
		if(name==null||"".equals(name.trim())){
			return false;
		}
		return true;
	}
	public static Object set(Class clz, HttpServletRequest req) {
		Map<String, String[]> maps = req.getParameterMap();
		Set<String> keys = maps.keySet();
		Object o = null;
		try {
			o = clz.newInstance();
			for (String key : keys) {
				String[]s=maps.get(key);
				//判断是否是String数组
				if(s.length>1){
					//注入key的set的方法(s为数组里的所有)
					BeanUtils.copyProperty(o, key, s);
				}else{
					//注入key的set的方法(s为数组里的第一个也就是一个)
					BeanUtils.copyProperty(o, key, s[0]);
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return o;
	}

}
