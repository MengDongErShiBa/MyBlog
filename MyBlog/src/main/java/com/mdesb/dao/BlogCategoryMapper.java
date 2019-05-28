package com.mdesb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.mdesb.model.BlogCategory;
import com.mdesb.util.PageQueryUtil;

@Component
public interface BlogCategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(BlogCategory record);

    int insertSelective(BlogCategory record);

    BlogCategory selectByPrimaryKey(Integer categoryId);

    BlogCategory selectByCategoryName(String categoryName);

    int updateByPrimaryKeySelective(BlogCategory record);

    int updateByPrimaryKey(BlogCategory record);

    List<BlogCategory> findCategoryList(PageQueryUtil pageUtil);

    List<BlogCategory> selectByCategoryIds(@Param("categoryIds") List<Integer> categoryIds);

    int getTotalCategories(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);
}