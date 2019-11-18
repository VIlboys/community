package com.bjq.community.mapper;

import com.bjq.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}