package com.songzhen.howcool.job;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import com.songzhen.howcool.model.enums.JobRetCodeEnum;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import javazoom.jl.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 任务Handler示例（Bean模式）
 * <p>
 * 开发步骤：
 * 1、继承"IJobHandler"：“com.xxl.job.core.handler.IJobHandler”；
 * 2、注册到Spring容器：添加“@Component”注解，被Spring容器扫描为Bean实例；
 * 3、注册到执行器工厂：添加“@JobHandler(value="自定义jobhandler名称")”注解，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 4、执行日志：需要通过 "LoggerFactory.logger" 打印执行日志；
 *
 * @author xuxueli 2015-12-19 19:43:36
 */
@JobHandler(value = "alarmClockJobHandler")
@Component
public class AlarmClockJobHandler extends IJobHandler {

    private static final Logger logger = LoggerFactory.getLogger(AlarmClockJobHandler.class);

    @Value("${video.path}")
    private String videoPath;

    @Override
    public ReturnT<String> execute(String param) throws Exception {

        long now = System.currentTimeMillis();

        logger.debug(">>>>>>>>>>> {}", DateUtil.now() + " alarmClockJobHandler start ...");

        if (FileUtil.exist(videoPath)) {
            new Player(new FileReader(videoPath).getInputStream()).play();
        }

        logger.debug(">>>>>>>>>>> alarmClockJobHandler consume {} ms", System.currentTimeMillis() - now);

        return new ReturnT<>(JobRetCodeEnum.SUCCESS_CODE.getValue(), JobRetCodeEnum.SUCCESS_CODE.getTitle());
    }

}
