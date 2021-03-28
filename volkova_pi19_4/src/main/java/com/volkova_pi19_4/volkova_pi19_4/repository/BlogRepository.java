package com.volkova_pi19_4.volkova_pi19_4.repository;

import com.volkova_pi19_4.volkova_pi19_4.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
