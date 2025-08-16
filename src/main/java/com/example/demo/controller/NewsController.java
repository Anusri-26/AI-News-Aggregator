package com.example.demo.controller;

import com.example.demo.model.News;
import com.example.demo.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    // ========================
    // Thymeleaf Frontend Views
    // ========================

    @GetMapping
    public String viewAllNews(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "") String keyword) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<News> newsPage = newsService.getNewsByKeyword(keyword, pageable);

        model.addAttribute("newsPage", newsPage);
        model.addAttribute("keyword", keyword);
        return "news-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("news", new News());
        return "news-add";
    }

    @PostMapping("/add")
    public String addNews(@ModelAttribute News news) {
        newsService.saveNews1(news); // AI category generation
        return "redirect:/news";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        newsService.getNewsById(id).ifPresent(news -> model.addAttribute("news", news));
        return "news-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateNews(@PathVariable Long id, @ModelAttribute News news) {
        newsService.saveNews1(news); // AI category updated if empty
        return "redirect:/news";
    }

    @GetMapping("/delete/{id}")
    public String deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return "redirect:/news";
    }

    // ========================
    // REST API Endpoints
    // ========================

    @GetMapping("/api")
    @ResponseBody
    public Page<News> getAllNewsApi(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "") String keyword) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);
        return newsService.getNewsByKeyword(keyword, pageable);
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<News> getNewsByIdApi(@PathVariable Long id) {
        Optional<News> news = newsService.getNewsById(id);
        return news.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api")
    @ResponseBody
    public News createNewsApi(@RequestBody News news) {
        return newsService.saveNews1(news); // AI category handled
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<News> updateNewsApi(@PathVariable Long id, @RequestBody News newsDetails) {
        Optional<News> optionalNews = newsService.getNewsById(id);
        if (!optionalNews.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        News news = optionalNews.get();
        news.setTitle(newsDetails.getTitle());
        news.setDescription(newsDetails.getDescription());
        news.setUrl(newsDetails.getUrl());
        news.setSource(newsDetails.getSource());
        // Keep existing category unless manually updated
        if (newsDetails.getCategory() != null && !newsDetails.getCategory().trim().isEmpty()) {
            news.setCategory(newsDetails.getCategory());
        } else if (news.getCategory() == null || news.getCategory().trim().isEmpty()) {
            // Generate AI category if empty
            news.setCategory(newsService.generateAICategory(news.getTitle(), news.getDescription()));
        }
        return ResponseEntity.ok(newsService.saveNews(news));
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteNewsApi(@PathVariable Long id) {
        if (!newsService.getNewsById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }
}
