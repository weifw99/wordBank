package com.mtime.wordbank.service.bus;

import com.mtime.wordbank.domain.db.MovieInfo;
import com.mtime.wordbank.domain.db.PersonInfo;
import com.mtime.wordbank.domain.hbase.Movie;
import com.mtime.wordbank.domain.hbase.Person;
import com.mtime.wordbank.service.db.MovieInfoService;
import com.mtime.wordbank.service.db.PersonInfoService;
import com.mtime.wordbank.service.hbase.PersonServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mtime on 2016/2/29.
 */
@Service
public class PersonService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PersonServiceImpl personServiceImpl;
    @Autowired
    private PersonInfoService personInfoService;

    public void createAllPersonInfo(){
        logger.info("Begin Person index!");
        //for(int k =100 ;k<= 999;k++){//125
        for(int k =100 ;k<= 999;k++){//125
            List<Person> list = personServiceImpl.getAllPerson(k, k+1);
            savePerson(list);
        }
        for(int k =9990 ;k<= 9999;k++){
            List<Person> list = personServiceImpl.getAllPerson(k, k+1);
            savePerson(list);
        }
        for(int k =99990 ;k<= 99999;k++){
            List<Person> list = personServiceImpl.getAllPerson(k, k+1);
            savePerson(list);
        }
        for(int k =999990 ;k<= 999999;k++){
            List<Person> list = personServiceImpl.getAllPerson(k, k+1);
            savePerson(list);
        }

    }

    private void savePerson(List<Person> list){
        if (list!=null && list.size()>0){
            List<PersonInfo> pList = new ArrayList<PersonInfo>();
            for (Person p : list){
                if (StringUtils.isNotBlank(p.getNameCn()) || StringUtils.isNotBlank(p.getNameEn()) ||
                        StringUtils.isNotBlank(p.getNameOther()) || StringUtils.isNotBlank(p.getTagNames()) ){
                    PersonInfo pinfo = new PersonInfo();
                    pinfo.setId("person_" + p.getPersonId());
                    pinfo.setNameCn(p.getNameCn());
                    pinfo.setNameEn(p.getNameEn());
                    pinfo.setNameOther(p.getNameOther());
                    pinfo.setTagNames(p.getTagNames());
                    pinfo.setCreateDate(new Date());
                    pinfo.setUpdateDate(new Date());
                    pList.add(pinfo);
                }
            }
            if (pList.size()>0){
                int i = pList.size() / 4000;
                if (pList.size()%4000 != 0){
                    i++;
                }
                for (int j = 0; j < i; j++) {
                    if (j==i-1){
                        personInfoService.saveList(pList.subList(j*4000, pList.size()));
                    }else{
                        personInfoService.saveList(pList.subList(j*4000, (j+1)*4000));
                    }
                }
            }
        }

    }
}
