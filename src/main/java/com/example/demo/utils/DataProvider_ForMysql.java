package com.example.demo.utils;


import com.example.demo.dataobject.CaseDO;

import java.util.Iterator;
import java.util.List;

public class DataProvider_ForMysql implements Iterator<Object[]> {

        /** 查询结果集 */
        List<CaseDO> caseList;

        /** 总行数 */
        private int rowNum=0;
        /** 当前行数 */
        private int curRowNo=0;

        public DataProvider_ForMysql(List<CaseDO> cases){
            this.caseList = cases;
            this.rowNum = caseList.size();
        }

        @Override
        public boolean hasNext() {
            if(rowNum==0||curRowNo>=rowNum){
                return false;
            }else{
                return true;
            }
        }

        @Override
        public Object[] next() {
            CaseDO caseDO = caseList.get(curRowNo);
            Object[] o=new Object[1];
            o[0]=caseDO;
            this.curRowNo++;
            return o;
        }
    }

