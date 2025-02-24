package com.backend.com.dto.request;

import lombok.Data;

@Data
public class BoardPageDto {
  private Long BoardId;
  private Long pageNo;
  private Long articleId;
  
}
