package com.zhora.ai.v1.business.impl;

import com.zhora.enums.ai.Actions;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class RagService {

  @Autowired
  private EmbeddingModel qwenEmbeddingModel;

  @Autowired
  @Qualifier("qwenEmbeddingStore")
  private EmbeddingStore<TextSegment> qwenEmbeddingStore;

//  @Autowired
//  @Qualifier("qwenPuLibraryEmbeddingStore")
//  private EmbeddingStore<TextSegment> qwenPuLibraryEmbeddingStore;


  public Map<String, String> searchAction(String message) {
    Map<String, String> result = new HashMap<>();
    EmbeddingSearchRequest embeddingSearchRequest =
            EmbeddingSearchRequest.builder()
                    .queryEmbedding(qwenEmbeddingModel.embed(message).content())
                    .minScore(0.89)
                    .build();
    EmbeddingSearchResult<TextSegment> embeddingSearchResult = qwenEmbeddingStore.search(embeddingSearchRequest);
    if (!embeddingSearchResult.matches().isEmpty()) {
      Metadata metadata = embeddingSearchResult.matches().getFirst().embedded().metadata();
      result.put(Actions.INDEX_KEY, metadata.getString(Actions.INDEX_KEY));
    }
    return result;
  }
}
