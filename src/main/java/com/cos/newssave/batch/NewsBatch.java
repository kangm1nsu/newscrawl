package com.cos.newssave.batch;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cos.newssave.domain.News;
import com.cos.newssave.domain.NewsRepository;
import com.cos.newssave.util.NaverCraw;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NewsBatch {
	private final NewsRepository newsRepository;
	private final NaverCraw naverCraw;
	
	@Scheduled(fixedDelay = 1000*60*2)
	public void newsCrawAndSave() {
		List<News> newsList = naverCraw.collect10();
		newsRepository.saveAll(newsList);
	}
}
