package com.kkngai.blogjava.respository;

import com.kkngai.blogjava.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : kkngai
 * @created : 17/1/2021, 星期日
 **/
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
