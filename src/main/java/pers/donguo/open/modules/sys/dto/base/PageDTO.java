package pers.donguo.open.modules.sys.dto.base;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

import lombok.Data;

/**
 * <p>Title: PageDTO.java </p>
 * <p>Description: 分页DTO 用于前后端分页数据传输</p>
 * @author Penguin
 * @date 2019年11月9日
 * @version 1.0
 */
@Data
public class PageDTO {
	
	private int totalCount;
	private int pageSize;
	private int totalPage;
	private int currentPage;
	
	private List<?> list;
	

	public PageDTO() {
	}

	public PageDTO (IPage<?> iPage) {
		this.totalCount = (int)iPage.getTotal();
		this.pageSize = (int)iPage.getSize();
		this.currentPage = (int)iPage.getCurrent();
		this.totalPage = (int)iPage.getPages();
		this.list = iPage.getRecords();
		
	}

	
}
