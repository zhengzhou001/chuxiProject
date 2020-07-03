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
public class EsImportCaseThread extends Thread {
    private TransportClient transportClient;
    private IBaseService baseService;

    private  int pageSize;
    public EsImportCaseThread(int pageSize,
                              TransportClient transportClient,
                              IBaseService baseService
                               ){
        this.pageSize=pageSize;
        this.transportClient=transportClient;
        this.baseService=baseService;

    }

    @Override
    public void run() {
        try {
            log.info("开始运行");
            //查询对应表的数据量
            int tableCount  = (Integer) baseService.selectOne("base.selectCaseInfosCount", ArrayUtils.toMap( new Object[][]{
                    {"tableName",""}
            }));
            log.info("表数量"+tableCount);
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
                List<Map> list = baseService.select("base.selectCaseInfos", ArrayUtils.toMap( new Object[][]{
                        {"curPage",i+1},
                        {"pageSize",pageSize}
                }));
                long endTime2 = System.currentTimeMillis();
                log.info(String.format("第%d次查询数据库用时%d毫秒,数量：%d",i,(endTime2-startTime),list.size()));
                BulkRequestBuilder bulkRequestBuilder = transportClient.prepareBulk();
                for(Map tmap:list){
                    bulkRequestBuilder.add(
                            transportClient.prepareIndex("person_info","data")
                                    .setSource(XContentFactory.jsonBuilder().startObject()
                                            .field("attrvalue", MapUtils.getString(tmap,"ATTRVALUE",""))
                                            .field("attrname", MapUtils.getString(tmap,"ATTRNAME",""))
                                            .endObject())
                    );
                }
                //批量提交到服务器
                BulkResponse bulkResponse=bulkRequestBuilder.execute().actionGet();
                long endTime = System.currentTimeMillis();
                 log.info(String.format("第%d次提交用时%d毫秒,数量：%d",i,(endTime-startTime),list.size()));

                //提交过程是否产生错误
                if(bulkResponse.hasFailures()){
                    log.info(String.format("第%d次提交有错%s",i,
                            bulkResponse.buildFailureMessage()));
                }
            }
            long endTimeAll = System.currentTimeMillis();
            log.info(String.format("导入完毕，用时：%d秒",(endTimeAll-startTimeAll)/1000));


        }catch (Exception e){
            log.error(e.getMessage());
        }


    }
}
