package com.xinan.controller;

import com.xinan.distributeCore.result.BaseResult;
import com.xinan.distributeCore.service.IBaseService;
import com.xinan.thread.EsImportThread;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.Map;

@RestController
@RequestMapping(value="/es")
@Slf4j
public class EsController {
    @Autowired
    private TransportClient transportClient;
    @Autowired
    private IBaseService baseService;


    @RequestMapping(value={"/insert"}, method={RequestMethod.POST})
    public BaseResult insert(){
        BaseResult ret = new BaseResult();
        try {
            XContentBuilder xContentBuilder= XContentFactory.jsonBuilder().startObject()
                    .field("name","张三")
                    .field("age",10)
                    .endObject();

           IndexResponse indexResponse= transportClient.prepareIndex("testindex","testtype")
                    .setSource(xContentBuilder).get();
           log.info(indexResponse.getId());
            ret.data=indexResponse;
        }catch ( Exception e){
            log.error(e.getMessage());
            ret.code=-1;
            ret.msg=e.getMessage();
        }
        return ret ;
    }

    @RequestMapping(value={"/insertPL"}, method={RequestMethod.POST})
    public BaseResult insertPL(@RequestBody Map map){
        BaseResult ret = new BaseResult();


        try {
            Format f1 = new DecimalFormat("000");
             //启动导入线程
            int m =MapUtils.getIntValue(map,"count");
            for (int i=(m-1)*20+1;i<(m)*20+1;i++){
                EsImportThread esImportThread =
                        new EsImportThread(
                                MapUtils.getIntValue(map,"pageSize"),
                                transportClient,baseService,"PART_"+f1.format(i));
                esImportThread.start();
            }

        }catch ( Exception e){
            log.error(e.getMessage());
            ret.code=-1;
            ret.msg=e.getMessage();
        }
        return ret ;
    }


    public static void main(String[] args) {
        boolean flag =true;
        for(int i=0;i<5;i++){
            if(!flag){
                break;
            }
            System.out.println(i);
             for(int k=0;k<5;k++){
                 System.out.println(i+"======="+k);
                 if(k==2){
                     flag=false;
                     break;
                 }
             }

        }
    }
}
