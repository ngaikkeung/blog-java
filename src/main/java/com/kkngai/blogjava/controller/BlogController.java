package com.kkngai.blogjava.controller;

import cn.hutool.db.Page;
import com.kkngai.blogjava.common.lang.Result;
import com.kkngai.blogjava.model.Blog;
import com.kkngai.blogjava.service.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author : kkngai
 * @created : 17/1/2021, 星期日
 **/
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<Result> getBlogs(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }


        List<Blog> result = blogService.getPagedBlogs(currentPage);

        return ResponseEntity.ok(Result.success(result));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Result> getDetail(@PathVariable("id") long id) {
        Optional<Blog> optionalBlog = blogService.findById(id);

        if (optionalBlog.isEmpty()) {

        }
        return ResponseEntity.ok(Result.success(optionalBlog.get()));

    }
}
