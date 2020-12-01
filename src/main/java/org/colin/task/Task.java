package org.colin.task;

import lombok.extern.slf4j.Slf4j;
import org.colin.utils.DateUtils;
import org.colin.utils.DbBackup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description: 定时任务
 * @ClassName: Task
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/29 10:55
 * @Version: 1.1.0
 */
@Component
@EnableScheduling
@Slf4j
public class Task {

    @Value("${system.restore_db}")
    private String restore_db;

    @Value("${system.backup_db}")
    private String backup_db;

    @Value("${system.sql_path}")
    private String sql_path;

    @Value("${system.mysql_db_restore}")
    private String mysql_db_restore;

    @Value("${system.mysql_db_backup}")
    private String mysql_db_backup;

    @Value("${system.mysql_db_backup_file}")
    private String mysql_db_backup_file;

    // 第一次延迟5秒后执行，之后按fixedRate的规则每x分钟执行一次(fixedRate = 1000 * 60 * 60 * 2 每2小时执行一次)
    @Scheduled(initialDelay = 5000, fixedRate = 1000 * 60 * 60)
    public void taskTest1() {
//        log.info("--------【测试】定时任务--------每60分钟执行一次，时间:{}", DateUtils.getCurrentTimeByPattern(DateUtils.YYYY_MM_DD_HH_MM_SS));
    }

    // 数据库恢复（每天晚上11点半触发）
//    @Scheduled(cron = "0 30 23 ? * *")
//    public void taskTest2() {
//        if ("1".equalsIgnoreCase(restore_db)) {
//            log.info("--------【数据库恢复】定时任务--------每天晚上11点半触发----开始");
//            try {
//                DbBackup.restoreMysqlDB(sql_path, mysql_db_restore);
//            } catch (Exception e) {
//                log.info("数据库恢复-定时任务执行时，发生异常：" + e.getLocalizedMessage());
//            }
//            log.info("--------【数据库恢复】定时任务--------每天晚上11点半触发----结束");
//        }else{
//            log.info("不进行数据库恢复");
//        }
//    }

    // 数据库备份
//    @Scheduled(cron = "0 40 23 ? * *")//每天晚上11点半触发
    @Scheduled(initialDelay = 2000, fixedRate = 1000 * 60 * 5)//每隔5分钟触发一次
    public void taskTest3() {
        if ("1".equalsIgnoreCase(backup_db)) {
            log.info("--------【数据库备份】定时任务--------每隔30分钟触发一次-----开始，时间：{}", DateUtils.getCurrentTimeByPattern(DateUtils.YYYY_MM_DD_HH_MM_SS));
            try {
                DbBackup.backupMysqlDB(mysql_db_backup, mysql_db_backup_file);
            } catch (Exception e) {
                log.info("数据库备份-定时任务执行时，发生异常：" + e.getLocalizedMessage());
            }
            log.info("--------【数据库备份】定时任务--------每隔30分钟触发一次-----结束");
        }else{
            log.info("不进行数据库备份");
        }
    }

    // 数据库恢复
    // 第一次延迟2秒后执行，之后按fixedRate的规则每x分钟执行一次(fixedRate = 1000 * 60 * 10 每10分钟执行一次)
    @Scheduled(initialDelay = 2000, fixedRate = 1000 * 60 * 10)
    public void taskTest4() {
        if ("1".equalsIgnoreCase(restore_db)) {
            log.info("--------【数据库恢复】定时任务--------每10分钟执行一次----开始时间:{}", DateUtils.getCurrentTimeByPattern(DateUtils.YYYY_MM_DD_HH_MM_SS));
            try {
                DbBackup.restoreMysqlDB(sql_path, mysql_db_restore);
            } catch (Exception e) {
                log.info("数据库恢复-定时任务执行时，发生异常：" + e.getLocalizedMessage());
            }
            log.info("--------【数据库恢复】定时任务--------每10分钟执行一次----开始时间:{}", DateUtils.getCurrentTimeByPattern(DateUtils.YYYY_MM_DD_HH_MM_SS));
        }else{
            log.info("【数据库恢复】定时任务-配置不执行");
        }
    }

}
