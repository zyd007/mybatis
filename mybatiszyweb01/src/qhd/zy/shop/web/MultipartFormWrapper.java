package qhd.zy.shop.web;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;


public class MultipartFormWrapper extends HttpServletRequestWrapper {
	private static Map<String,String[]>params=null;
	public MultipartFormWrapper(HttpServletRequest request){
		super(request);
		params=new HashMap<String, String[]>();
		setParams(request);
	}
	private void setParams(HttpServletRequest request) {
		InputStream is=null;
		ByteArrayOutputStream baos=null;
		try {
			if(ServletFileUpload.isMultipartContent(request)){
				ServletFileUpload upload=new ServletFileUpload();
				FileItemIterator fi=upload.getItemIterator(request);
				while(fi.hasNext()){
					FileItemStream fs=fi.next();
					is=fs.openStream();
					if(fs.isFormField()){
						setForm(fs.getFieldName(),is);
					}else{
						/**
						 * 将一个文件输入流转换为字节数组需要通过ByteArrayoutputStream
						 */
						byte[]buf=new byte[1024];
						baos=new ByteArrayOutputStream();
						int len=0;
						//传完了就跳出循环继续执行
						while((len=is.read(buf))>0){
							baos.write(buf, 0, len);
						}
						byte[]bt=baos.toByteArray();
						request.setAttribute("bt", bt);
						params.put(fs.getFieldName(), new String[]{fs.getName()});
					}
				}
			}else{
				params=super.getParameterMap();
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void setForm(String fieldName, InputStream is) throws IOException {
		if(params.containsKey(fieldName)){
			String []s=params.get(fieldName);
			s=Arrays.copyOf(s, s.length+1);
			s[s.length-1]=Streams.asString(is);
			params.put(fieldName, s);
		}else{
			params.put(fieldName, new String[]{Streams.asString(is)});
		}
	}
	@Override
	public Map<String, String[]> getParameterMap() {
		return params;
	}
	@Override
	public String[] getParameterValues(String name) {
		String[]vs=params.get(name);
		if(vs!=null){
			return vs;
		}
		return null;
	}
	@Override
	public String getParameter(String name) {
		String[]vs=params.get(name);
		if(vs!=null){
			return vs[0];
		}
		return null;
	}
}
