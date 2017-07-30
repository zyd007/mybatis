package qhd.zy.shop.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import qhd.zy.shop.Util.RequestUtil;
import qhd.zy.shop.model.SystemContext;

public class SystemContextFilter implements Filter {
	
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		try {
			int offset=0;
			int pageRecord=15;
			String sort=req.getParameter("sort");
			String order=req.getParameter("order");
			//这里要抛出异常是因为刚进去pager.offest没值 继续运行然后获取offset=0的值
			try {
				offset=Integer.parseInt(req.getParameter("pager.offset"));
			} catch (NumberFormatException e) {
			}
			SystemContext.setOffset(offset);
			SystemContext.setPageRecord(pageRecord);
			SystemContext.setSort(sort);
			SystemContext.setOrder(order);
			SystemContext.setRealPath(RequestUtil.PATH);
			//这行必须要
			chain.doFilter(req, resp);
		} finally{
			//这一次执行完就删除保存信息，优化系统
			SystemContext.removeOffset();
			SystemContext.removePageRecord();
			SystemContext.removeSort();
			SystemContext.removeOrder();
			SystemContext.removeRealPath();
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	
	}

}
