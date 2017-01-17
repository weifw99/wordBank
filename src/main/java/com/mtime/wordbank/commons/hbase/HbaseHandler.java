package com.mtime.wordbank.commons.hbase;

import com.mtime.wordbank.commons.dao.IDaoHbase;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HbaseHandler implements IDaoHbase {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private HbaseTemplate hbaseTemplate;

	@Override
	public Map<String, Object> get(String tableName, String rowName) {
		 return hbaseTemplate.get(tableName, rowName,new RowMapper<Map<String,Object>>(){
             public Map<String,Object> mapRow(Result result, int rowNum) throws Exception {
                 List<Cell> ceList =   result.listCells();
                 String row = "";
                 Map<String,Object> map = new HashMap<String, Object>();
                      if(ceList!=null && ceList.size()>0){
                          for(Cell cell:ceList){  
                        	  row =Bytes.toString( cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());  
                              map.put(Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength())+  
                                 "_"+Bytes.toString( cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength()),   
                                 Bytes.toString( cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));  
                          }  
                          map.put("row",row );
                          System.out.println("*************Row " + row );
                       }  
                 return  map;  
             }  
           });  
	}

	@Override
	public String get(String tableName, String rowName, String familyName, String qualifier) {
		 return hbaseTemplate.get(tableName, rowName,familyName,qualifier ,new RowMapper<String>(){
             public String mapRow(Result result, int rowNum) throws Exception {
                 List<Cell> ceList =   result.listCells();
                 String res = "";
                 if(ceList!=null && ceList.size()>0){  
                     for(Cell cell:ceList){  
                         res = Bytes.toString( cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());  
                     }  
                 }  
               return res;  
             }  
      });  
	}
	
	@Override
	public List<Map<String, Object>> findCurrentData(String tableName, String prefix) {
		//info:MODIFY_TIME
        Scan scan = new Scan();
        FilterList filterList = new FilterList();
        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes("info"),
				Bytes.toBytes("MODIFY_TIME"), CompareOp.EQUAL,
				new BinaryPrefixComparator(Bytes.toBytes(prefix))));
        
        scan.setFilter(filterList);
        return hbaseTemplate.find(tableName, scan,new RowMapper<Map<String,Object>>(){
              public Map<String,Object> mapRow(Result result, int rowNum) throws Exception {
                   
                    List<Cell> ceList =   result.listCells();
                    Map<String,Object> map = new HashMap<String,Object>();
                    String row = "";
                    if(ceList!=null && ceList.size()>0){  
                          for(Cell cell:ceList){  
                           row =Bytes.toString( cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());  
                           String value =Bytes.toString( cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                           String family =Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength());
                           String quali = Bytes.toString( cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength());
                           map.put(family+"_"+quali, value);  
                          }  
                          map.put("row",row );
                          System.out.println("Row " + row );
                      }  
                      return  map;  
                  }  
                });  
   }
	
	@Override
	public List<Map<String, Object>> find(String tableName, String startRow, String stopRow) {
		
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(startRow));
        scan.setStopRow(Bytes.toBytes(stopRow));
        return     hbaseTemplate.find(tableName, scan,new RowMapper<Map<String,Object>>(){
              public Map<String,Object> mapRow(Result result, int rowNum) throws Exception {
                   
                    List<Cell> ceList =   result.listCells();
                    Map<String,Object> map = new HashMap<String,Object>();
                    Map<String,Map<String,Object>> returnMap = new HashMap<String,Map<String,Object>>();
                    String row = "";
                    if(ceList!=null&&ceList.size()>0){  
                          for(Cell cell:ceList){  
                           row =Bytes.toString( cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());  
                           String value =Bytes.toString( cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                           String family =  Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength());
                           String quali = Bytes.toString( cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength());
                           map.put(family+"_"+quali, value);  
                          }  
                          map.put("row",row );
                      }  
                      return  map;  
                  }  
                });  
   }

	
	
	 public List<Map<String,Object>> find(String tableName , String rowName, String familyName, String qualifier) {
	    	
    	 return hbaseTemplate.find(tableName,familyName,qualifier,new RowMapper<Map<String,Object>>(){
             public Map<String,Object> mapRow(Result result, int rowNum) throws Exception {
                   List<Cell> ceList =   result.listCells();
                   Map<String,Object> map = new HashMap<String,Object>();
                   //Map<String,Map<String,Object>> returnMap = new HashMap<String,Map<String,Object>>();  
                   String row = "";
                   if(ceList!=null && ceList.size()>0){  
                         for(Cell cell:ceList){  
                          row =Bytes.toString( cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());  
                          String value =Bytes.toString( cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                          String family =  Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength());
                          String quali = Bytes.toString( cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength());
                          map.put(family+"_"+quali, value);  
                         }  
                         map.put("row",row );  
                     }  
                     return  map;  
                 }
             
               });  
    }
}
