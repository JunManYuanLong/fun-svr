package com.okay.family.common;

import com.okay.family.common.basedata.ServerHost;
import com.okay.family.mapper.CommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StartRun implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(StartRun.class);

    CommonMapper commonMapper;

    @Autowired
    public StartRun(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }

    @Override
    public void run(String... args) {
        List<HashMap<String, String>> hosts = commonMapper.findAllHost();
        Map<Integer, String> collect = hosts.stream().collect(Collectors.toMap((x -> {
            Object service_id = x.get("service_id");
            return Integer.parseInt(service_id.toString());
        }), (x -> "https://" + x.get("domain"))));
        ServerHost.init(collect);

        logger.info(ServerHost.getHost(32));
        logger.info("程序初始化运行方法执行完毕……");
    }


}
