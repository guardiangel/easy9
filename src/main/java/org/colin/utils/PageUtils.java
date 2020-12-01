package org.colin.utils;

import com.github.pagehelper.Page;
import org.colin.model.vo.resp.PageVO;

import java.util.List;
/**
 * @Description: 分页工具类
 * @ClassName: PageUtils
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 21:15
 * @Version: 1.1.0
 */
public class PageUtils {

    private PageUtils() {
    }

    public static <T> PageVO<T> getPageVO(List<T> list) {
        PageVO<T> result = new PageVO<>();
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            result.setTotalRows(page.getTotal());
            result.setTotalPages(page.getPages());
            result.setPageNum(page.getPageNum());
            result.setPageSize(page.getPageSize());
            result.setCurPageSize(page.size());
            result.setList(page.getResult());
        }
        return result;
    }
}
