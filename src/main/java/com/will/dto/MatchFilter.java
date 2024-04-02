package com.will.dto;

public record MatchFilter(String name,
                          Integer limit,
                          Integer offset) {
}
