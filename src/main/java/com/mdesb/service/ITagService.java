package com.mdesb.service;

import java.util.List;

import com.mdesb.model.BlogTagCount;
import com.mdesb.util.PageQueryUtil;
import com.mdesb.util.PageResult;

public interface ITagService {

    /**
     * 查询标签的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    int getTotalTags();

    Boolean saveTag(String tagName);

    Boolean deleteBatch(Integer[] ids);

    List<BlogTagCount> getBlogTagCountForIndex();
}
