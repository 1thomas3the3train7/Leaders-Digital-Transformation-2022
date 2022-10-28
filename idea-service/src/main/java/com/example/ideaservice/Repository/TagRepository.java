package com.example.ideaservice.Repository;

import com.example.ideaservice.Model.Tag.TagBase;
import com.example.ideaservice.Model.Tag.TagShort;

public interface TagRepository {
    void save(TagBase tagBase);
    void delete(TagBase tagBase);
    TagShort getTagByTagName(String tagnName);
    void appendProjectAndTag(Long project_id,Long tag_id);
}
