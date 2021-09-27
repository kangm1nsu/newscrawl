package com.cos.newssave.batch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

//https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=100&oid=003&aid=0000000001
public class NaverCrawTest {
	int aidNum = 1;
	//@Test
	public void test1() throws IOException {
		//httpUrlConnection(자바기본),okHttp(라이브러리),RestTemplate,Retrofit2
		//파이썬 requests
		RestTemplate rt = new RestTemplate();
		
		String url="https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=100&oid=003&aid=0000000001";
		
		String html = rt.getForObject(url, String.class);
		//System.out.println(html); // jsoup로 id: articleTitle를 파싱해야함.
		
		//1.jsoup 라이브러리 빌드 (mvn)

		//2.jsoup 기본 함수 약간 학습
		//Document doc = Jsoup.connect(url).get();
		Document doc = Jsoup.parse(html);
		Element titleElement = doc.selectFirst("#articleTitle");
		String title = titleElement.text();
		//3.콘솔에 제목 출력
		System.out.println(title);
		
	}
	

	//@Test
	public void test2()  {
		for (int i = 1; i < 11; i++) {
			
			String num = String.format("%010d", i);
			System.out.println(num);
		}
	}
	
	
	@Test
		public void test3() {
			RestTemplate rt = new RestTemplate();
			List<NewsTest> nts = new ArrayList<>();
			
			for (int i = 1; i<11; i++) {
				
				String num = String.format("%010d", aidNum);
				
				String url="https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=100&oid=003&aid=" + num;
				String html = rt.getForObject(url, String.class);
				
				Document doc = Jsoup.parse(html);
				
				Element titleElement = doc.selectFirst("#articleTitle");
				String title = titleElement.text();
				
				Element timeElement = doc.selectFirst(".t11");
				String time = timeElement.text();
				
				System.out.println(title);
				System.out.println(time);
				
				NewsTest nt = NewsTest.builder()
						.title(title)
						.time(time)
						.build();
				nts.add(nt);
				
				aidNum ++;
			}
			
			assertTrue(nts.size() == 10);
		}
}
