package com.kkngai.blogjava.service;

import cn.hutool.db.Page;
import com.kkngai.blogjava.model.Blog;

import java.util.List;
import java.util.Optional;

/**
 * @author : kkngai
 * @created : 17/1/2021, 星期日
 **/
public interface BlogService {

    List<Blog> getPagedBlogs(int currentPage);

    Optional<Blog> findById(long id);
}
