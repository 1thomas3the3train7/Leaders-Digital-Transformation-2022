package com.example.ideaservice.Repository;

import com.example.ideaservice.Model.Comment.CommentBase;

public interface CommentRepository {
    void save(CommentBase commentBase);
    void delete(CommentBase commentBase);
}
