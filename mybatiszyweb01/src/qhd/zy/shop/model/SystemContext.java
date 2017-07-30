package qhd.zy.shop.model;

public class SystemContext {
	private static ThreadLocal<Integer>IndexPage=new ThreadLocal<Integer>();
	private static ThreadLocal<Integer>pageRecord=new ThreadLocal<Integer>();
	private static ThreadLocal<Integer>offset=new ThreadLocal<Integer>();
	/*
	 * 升序还是降序
	 */
	private static ThreadLocal<String>order=new ThreadLocal<String>();
	/*
	 * 用什么排序
	 */
	private static ThreadLocal<Object>sort=new ThreadLocal<Object>();
	/*
	 * 获得路径
	 */
	private static ThreadLocal<String>realPath=new ThreadLocal<String>();
	public static void setRealPath(String _realPath){
		realPath.set(_realPath);
	}
	public static String getRealPath(){
		return realPath.get();
	}
	public static void removeRealPath(){
		realPath.remove();
	}
	public static void setSort(Object _sotr){
		sort.set(_sotr);
	}
	public static Object getSort(){
		return sort.get();
	}
	public static void removeSort(){
		sort.remove();
	}
	public static void setOrder(String _order){
		order.set(_order);
	}
	public static String getOrder(){
		return order.get();
	}
	public static void removeOrder(){
		order.remove();
	}
	public static void setIndexPage(int _IndexPage){
		IndexPage.set(_IndexPage);
	}
	public static int getIndexPage(){
		return IndexPage.get();
	}
	public static void removeIndexPage(){
		IndexPage.remove();
	}
	public static void setPageRecord(int _PageRecord){
		pageRecord.set(_PageRecord);
	}
	public static int getPageRecord(){
		return pageRecord.get();
	}
	public static void removePageRecord(){
		pageRecord.remove();
	}
	public static void setOffset(int _offset){
		offset.set(_offset);
	}
	public static int getOffset(){
		return offset.get();
	}
	public static void removeOffset(){
		offset.remove();
	}
}
