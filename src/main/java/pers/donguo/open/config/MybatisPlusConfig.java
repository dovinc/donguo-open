package pers.donguo.open.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * <p>Title: MybatisPlusConfig.java </p>
 * <p>
 * Description: MybatisPlus分页配置类
 * 1.mybatis实现得分页时逻辑分页或者叫做内存不是物理分页
 * 2.他是把符合条件的数据全部查询出来放到内存中，然后返回你需要的那部分
 * 3.表中数据不多时,可以使用,速度慢一些;当数据量大时，建议使用物理分页
 * </p>
 * @author Penguin
 * @date 2019年11月9日
 * @version 1.0
 */
@Configuration
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MybatisPlusConfig {
     @Bean
     public PaginationInterceptor paginationInterceptor() {
         PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
         return paginationInterceptor;
    }
}
