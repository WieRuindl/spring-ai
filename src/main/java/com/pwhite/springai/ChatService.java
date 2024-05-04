package com.pwhite.springai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

	private final ChatClient chatClient;

	public String queryAi(String prompt) {
		return chatClient.call(prompt);
	}

	public ChatResponse queryAi(Prompt prompt) {
		return chatClient.call(prompt);
	}
}
