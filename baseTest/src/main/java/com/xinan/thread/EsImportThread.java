package com.xinan.thread;

import com.xinan.distributeCore.service.IBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.util.List;
import java.util.Map;
@Slf4j
public class EsImportThread extends Thread {
    private TransportClient transportClient;
    private IBaseService baseService;

    private  int pageSize;
    private  String tableName;
    public EsImportThread(int pageSize,
                          TransportClient transportClient,
                          IBaseService baseService,
                          String tableName ){
        this.pageSize=pageSize;
        this.transportClient=transportClient;
        this.baseService=baseService;
        this.tableName=tableName;
        log.info(tableName+"初始化");
    }

    @Override
    public void run() {
        try {
            log.info(tableName+"开始运行");
            //查询对应表的数据量
            int tableCount  = (Integer) baseService.selectOne("base.selectGsmDataCount", ArrayUtils.toMap( new Object[][]{
                    {"tableName",tableName}
            }));
            log.info(tableName+"表数量"+tableCount);
            long startTimeAll = System.currentTimeMillis();
            int count =0;
            if (tableCount%pageSize==0){
                //可以整除
                count = tableCount/pageSize;
            }else{
                count= tableCount/pageSize+1;
            }
            for (int i=0;i<count;i++){
                long startTime = System.currentTimeMillis();
                List<Map> list = baseService.select("base.selectGsmData", ArrayUtils.toMap( new Object[][]{
                        {"curPage",i+1},
                        {"pageSize",pageSize},
                        {"tableName",tableName}
                }));
                long endTime2 = System.currentTimeMillis();
                log.info(String.format("表%s,第%d次查询数据库用时%d毫秒,数量：%d",tableName,i,(endTime2-startTime),list.size()));
                BulkRequestBuilder bulkRequestBuilder = transportClient.prepareBulk();
                for(Map tmap:list){
                    bulkRequestBuilder.add(
                            transportClient.prepareIndex("gsmdata","data")
                                    .setSource(XContentFactory.jsonBuilder().startObject()
                                            .field("imsi", MapUtils.getString(tmap,"IMSI",""))
                                            .field("imei", MapUtils.getString(tmap,"IMEI",""))
                                            .field("tme", MapUtils.getString(tmap,"TME",""))
                                            .field("ip", MapUtils.getString(tmap,"IP",""))
                                            .field("location", MapUtils.getString(tmap,"LOCATION",""))
                                            .field("sequence", MapUtils.getString(tmap,"SEQUENCE",""))
                                            .field("attribution", MapUtils.getString(tmap,"ATTRIBUTION",""))
                                            .field("phonenum", MapUtils.getString(tmap,"PHONENUM",""))
                                            .field("id", MapUtils.getString(tmap,"ID",""))
                                            .field("uploadtime", MapUtils.getString(tmap,"UPLOADTIME",""))
                                            .endObject())
                    );
                }
                //批量提交到服务器
                BulkResponse bulkResponse=bulkRequestBuilder.execute().actionGet();
                long endTime = System.currentTimeMillis();
                 log.info(String.format("表%s,第%d次提交用时%d毫秒,数量：%d",tableName,i,(endTime-startTime),list.size()));

                //提交过程是否产生错误
                if(bulkResponse.hasFailures()){
                    log.info(String.format("第%d次提交有错%s",i,
                            bulkResponse.buildFailureMessage()));
                }
            }
            long endTimeAll = System.currentTimeMillis();
            log.info(String.format("表%s,导入完毕，用时：%d秒",tableName,(endTimeAll-startTimeAll)/1000));


        }catch (Exception e){
            log.error(e.getMessage());
        }


    }
}
