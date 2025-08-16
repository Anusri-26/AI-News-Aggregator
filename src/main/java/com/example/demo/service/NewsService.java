package com.example.demo.service;

import com.example.demo.model.News;
import com.example.demo.repository.NewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    // Get all news
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    // Get news by ID
    public Optional<News> getNewsById(Long id) {
        return newsRepository.findById(id);
    }

    // Add or update news
    public News saveNews(News news) {
        return newsRepository.save(news);
    }

    // Delete news by ID
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    // ================================
    // Pagination and Search
    // ================================
    public Page<News> getNewsByKeyword(String keyword, Pageable pageable) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return newsRepository.findAll(pageable);
        } else {
            return newsRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                    keyword, keyword, pageable);
        }
    }
    
 // Add or update news with AI category
    public News saveNews1(News news) {
        // If category is empty, generate using AI
        if (news.getCategory() == null || news.getCategory().trim().isEmpty()) {
            String aiCategory = generateAICategory(news.getTitle(), news.getDescription());
            news.setCategory(aiCategory);
        }
        return newsRepository.save(news);
    }

    // ================================
    // AI Integration: Category Generation
    // ================================
    public String generateAICategory(String title, String description) {
        // Simple example: categorize based on keywords
        String combined = (title + " " + description).toLowerCase();
        if (combined.contains("sports")) return "Sports";
        if (combined.contains("politics")) return "Politics";
        if (combined.contains("technology") || combined.contains("ai")) return "Technology";
        if (combined.contains("health")) return "Health";
        if (combined.contains("entertainment") || combined.contains("movie") || combined.contains("music")) return "Entertainment";
        return "General";
    }


}
