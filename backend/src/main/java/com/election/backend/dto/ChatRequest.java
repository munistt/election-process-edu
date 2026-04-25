package com.election.backend.dto;

import java.util.List;
import java.util.Map;

public record ChatRequest(List<Map<String, String>> messages) {}
