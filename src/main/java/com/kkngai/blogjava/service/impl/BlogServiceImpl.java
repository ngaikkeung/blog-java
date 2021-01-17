package com.kkngai.blogjava.service.impl;

import cn.hutool.db.Page;
import com.kkngai.blogjava.model.Blog;
import com.kkngai.blogjava.respository.BlogRepository;
import com.kkngai.blogjava.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : kkngai
 * @created : 17/1/2021, 星期日
 **/
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Override
    public List<Blog> getPagedBlogs(int currentPage) {
        Pageable paging = PageRequest.of(currentPage, 5);

        return blogRepository.findAll(paging).getContent();
    }

    @Override
    public Optional<Blog> findById(long id) {
        return blogRepository.findById(id);
    }

}
