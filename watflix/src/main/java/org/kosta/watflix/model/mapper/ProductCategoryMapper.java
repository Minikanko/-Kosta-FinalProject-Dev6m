package org.kosta.watflix.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.watflix.model.service.PagingBean;
import org.kosta.watflix.model.vo.ProductCategoryVO;

@Mapper
public interface ProductCategoryMapper {

	void mProductCategoryRegister(ProductCategoryVO productCategoryVO);

	int mProductCategoryGetTotalCount();

	List<ProductCategoryVO> mGetProductCategoryList(PagingBean pagingBean); 
	
}
