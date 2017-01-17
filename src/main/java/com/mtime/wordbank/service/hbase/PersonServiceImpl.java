package com.mtime.wordbank.service.hbase;

import com.mtime.wordbank.commons.hbase.HbaseHandler;
import com.mtime.wordbank.domain.hbase.Person;
import com.mtime.wordbank.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl {
	private static Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

	private static String tableName = "mtime:PersonIntegrate";

	@Autowired
	private HbaseHandler hbaseHandler;

	public List<Person> getAllPerson(Integer startRow, long stopRow) {
		List<Person> personList = new ArrayList<Person>();
		List<Map<String, Object>> list = null;
		logger.info("  取影人数据参数   ->startRow："+startRow+"   stopRow："+stopRow+"条");
		try{
			list = hbaseHandler.find(tableName, String.valueOf(startRow), String.valueOf(stopRow));
		}catch(Exception e){
			logger.error("Table can not find .");
		}
		if (null==list || list.size()==0) {
			return personList;
		}
		logger.info(" 迭代分页取出的数据 ");
		for(Map<String, Object> map : list){
			Person p  = new Person();
			//String key = (String)map.get("Info");
			//p.setPersonId((Integer)map.get("PersonID"));
			p.setPersonId(Integer.parseInt((String)map.get("row")));
			p.setNameCn((String)map.get("info_NAME_CN"));
			String enName = StringUtil.getStandardString((String)map.get("info_NAME_EN"));
			p.setNameEn(enName);
			p.setNameOther((String)map.get("info_NAME_OTHER"));
			p.setSearchKey((String)map.get("info2_SearchKey"));
			String infoTagSet = (String)map.get("info2_TagSet");
			//p.setTagNames(infoTagSet);

			personList.add(p);
		}
		logger.info("Person count："+personList.size());
		return personList;
	}
}
