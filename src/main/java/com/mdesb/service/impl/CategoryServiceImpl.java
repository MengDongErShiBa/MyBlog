package com.mdesb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdesb.dao.BlogCategoryMapper;
import com.mdesb.dao.BlogMapper;
import com.mdesb.model.BlogCategory;
import com.mdesb.service.ICategoryService;
import com.mdesb.util.PageQueryUtil;
import com.mdesb.util.PageResult;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public PageResult getBlogCategoryPage(PageQueryUtil pageUtil) {
        List<BlogCategory> categoryList = blogCategoryMapper.findCategoryList(pageUtil);
        int total = blogCategoryMapper.getTotalCategories(pageUtil);
        PageResult pageResult = new PageResult(categoryList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalCategories() {
        return blogCategoryMapper.getTotalCategories(null);
    }

    @Override
    public Boolean saveCategory(String categoryName, String categoryIcon) {
        BlogCategory temp = blogCategoryMapper.selectByCategoryName(categoryName);
        if (temp == null) {
            BlogCategory blogCategory = new BlogCategory();
            blogCategory.setCategoryName(categoryName);
            blogCategory.setCategoryIcon(categoryIcon);
            return blogCategoryMapper.insertSelective(blogCategory) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
        BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(categoryId);
        if (blogCategory != null) {
            blogCategory.setCategoryIcon(categoryIcon);
            blogCategory.setCategoryName(categoryName);
            //修改分类实体
            blogMapper.updateBlogCategorys(categoryName, blogCategory.getCategoryId(), new Integer[]{categoryId});
            return blogCategoryMapper.updateByPrimaryKeySelective(blogCategory) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //修改tb_blog表
        blogMapper.updateBlogCategorys("默认分类", 0, ids);
        //删除分类数据
        return blogCategoryMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<BlogCategory> getAllCategories() {
        return blogCategoryMapper.findCategoryList(null);
    }

}
