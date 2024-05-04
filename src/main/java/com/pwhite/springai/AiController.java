package com.pwhite.springai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AiController {

	private final ChatService chatService;

	@GetMapping("ask-ai")
	public String askAi(@RequestParam("prompt") String prompt) {
		return chatService.queryAi(prompt);
	}

	@GetMapping("city-guide/{city}/{interest}")
	public String getCityGuide(
			@PathVariable("city") String city,
			@PathVariable("interest") String interest
	) {
		var template = """
				I am a tourist visiting the city of {city}.
				I am mostly interested in {interest}.
				Tell me tips on what to do there.""";

		PromptTemplate promptTemplate = new PromptTemplate(template);

		Map<String, Object> params = Map.of("city", city, "interest", interest);
		Prompt prompt = promptTemplate.create(params);

		return chatService.queryAi(prompt).getResult().getOutput().getContent();
	}

}
