package cn.edu.cuit.domain;

import java.util.List;

public class pageVO<T> {
	    // 1 当前页码
		private int page;
		// 2 每一页记录数量
		private int recordOfPage;
		// 3 总记录数量
		private int recordCount;
		// 4 总页数
		private int pageCount;
		// 5 当前页的数据集
		private List<T> list;

		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public int getRecordOfPage() {
			return recordOfPage;
		}
		public void setRecordOfPage(int recordOfPage) {
			this.recordOfPage = recordOfPage;
		}
		public int getRecordCount() {
			return recordCount;
		}
		public void setRecordCount(int recordCount) {
			this.recordCount = recordCount;
		}
		public int getPageCount() {
			return pageCount;
		}
		public void setPageCount(int pageCount) {
			this.pageCount = pageCount;
		}
		public List<T> getList() {
			return list;
		}
		public void setList(List<T> list) {
			this.list = list;
		}


}
