package com.mtime.wordbank.service.db;

import com.github.pagehelper.PageInfo;
import com.mtime.wordbank.domain.db.Suggestion;
import com.mtime.wordbank.mapper.SuggestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mtime on 2016/2/23.
 */
@Service
public class SuggestionService {
    private Logger log = LoggerFactory.getLogger(SuggestionService.class);

    @Autowired
    private SuggestionMapper suggestionMapper;

    public PageInfo<Suggestion> getSuggestionByPage(){

        //suggestionMapper.selectByRowBounds()
        return null;
    }



}
